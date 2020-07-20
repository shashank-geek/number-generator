package com.shashank.vmware.numbergenerator.services;

import com.shashank.vmware.numbergenerator.exception.ErrorCodes;
import com.shashank.vmware.numbergenerator.exception.NumberGeneratorException;
import com.shashank.vmware.numbergenerator.model.Goal;
import com.shashank.vmware.numbergenerator.response.Response;
import com.shashank.vmware.numbergenerator.util.NumberGeneratorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * NumberGeneratorService class to hold all the business logic
 */
@Service
public class NumberGeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NumberGeneratorService.class);

    @Autowired
    private NumberGeneratorHelper numberGeneratorHelper;

    public ResponseEntity processGoal(Goal goal) {
        LOGGER.info("Goal: {}", goal.toString());
        return numberGeneratorHelper.processGoal(goal);
    }

    public Response getGoalStatus(Integer uuid) {
        LOGGER.info("UUID: {}", uuid);
        return numberGeneratorHelper.getGoalStatus(uuid);
    }

    public Response getGoalResponse(Integer uuid, String action) {
        LOGGER.info("UUID: {} and Action: {}", uuid, action);
        if (action.equals("get_numlist")) {
            return numberGeneratorHelper.getNumList(uuid);
        }
        throw new NumberGeneratorException("Invalid Action", ErrorCodes.INVALID_ACTION);
    }
}
