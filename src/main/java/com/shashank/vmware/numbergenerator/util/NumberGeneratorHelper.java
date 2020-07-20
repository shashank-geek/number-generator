package com.shashank.vmware.numbergenerator.util;

import com.shashank.vmware.numbergenerator.enums.Status;
import com.shashank.vmware.numbergenerator.exception.ErrorCodes;
import com.shashank.vmware.numbergenerator.exception.NumberGeneratorException;
import com.shashank.vmware.numbergenerator.model.Goal;
import com.shashank.vmware.numbergenerator.repository.GoalRepository;
import com.shashank.vmware.numbergenerator.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * NumberGeneratorHelper class to hold all the helper method which can be reused
 */
@Component
public class NumberGeneratorHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(NumberGeneratorHelper.class);

    @Autowired
    private GoalRepository goalRepository;

    public ResponseEntity processGoal(Goal goal) {
        goal.setStatus(Status.INPROGRESS);
        Goal goalDb = goalRepository.save(goal);
        Status status = writeFile(goal, goalDb.getUuid());
        goal.setStatus(status);
        goalDb = goalRepository.save(goal);
        Response response = new Response(String.valueOf(goalDb.getUuid()), null);
        return ResponseEntity.status(202).body(response);
    }

    public Status writeFile(Goal goal, Integer taskId) {
        String fileName = "/tmp/" + taskId + "_output.txt";
        Status status;
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(fileName);
            Integer goalSet = Integer.parseInt(goal.getGoal());
            Integer steps = Integer.parseInt(goal.getStep());
            while (goalSet >= 0) {
                myWriter.write(String.valueOf(goalSet));
                if (goalSet != 0)
                    myWriter.write(",");
                goalSet = goalSet - steps;
            }
            status = Status.SUCCESS;
            LOGGER.info("Successfully wrote to the file. " + fileName);
        } catch (IOException e) {
            status = Status.ERROR;
            LOGGER.error("An error occurred while writing into the file");
            e.printStackTrace();
        } finally {
            try {
                myWriter.close();
            } catch (IOException e) {
                LOGGER.error("FileWriter is not able to close");
                e.printStackTrace();
            }
        }
        return status;
    }

    public Response getGoalStatus(Integer uuid) {
        Goal goal = goalRepository.findByUuid(uuid);
        if (goal == null) {
            String message = String.format("Couldn't find any matching record for the uuid=%d", uuid);
            LOGGER.error("Couldn't find any matching record for the uuid={}", uuid);
            throw new NumberGeneratorException(message, ErrorCodes.TRANSACTION_NOT_FOUND);
        }
        Response response = new Response(null, String.valueOf(goal.getStatus()));
        return response;
    }

    public Response getNumList(Integer uuid) {
        String result = readFile(uuid);
        Response response = new Response(result, null);
        return response;
    }

    public String readFile(Integer taskId) {
        String fileName = "/tmp/" + taskId + "_output.txt";
        FileReader fr = null;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader(fileName);
            int i;
            while ((i = fr.read()) != -1)
                sb.append((char) i);
        } catch (FileNotFoundException e) {
            LOGGER.error("File Not Found");
            e.printStackTrace();
            throw new NumberGeneratorException("UUID not found", ErrorCodes.UUID_NOT_FOUND);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                LOGGER.error("FileReader is not able to close");
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
