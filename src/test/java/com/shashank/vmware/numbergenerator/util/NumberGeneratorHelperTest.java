package com.shashank.vmware.numbergenerator.util;

import com.shashank.vmware.numbergenerator.enums.Status;
import com.shashank.vmware.numbergenerator.exception.NumberGeneratorException;
import com.shashank.vmware.numbergenerator.model.Goal;
import com.shashank.vmware.numbergenerator.repository.GoalRepository;
import com.shashank.vmware.numbergenerator.response.Response;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Junit NumberGeneratorHelperTest class
 */
@RunWith(SpringRunner.class)
public class NumberGeneratorHelperTest {
    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private NumberGeneratorHelper numberGeneratorHelper;

    private static Goal goal;

    @BeforeClass
    public static void setUp() {
        goal = new Goal();
        goal.setGoal("10");
        goal.setStep("2");
        goal.setStatus(Status.INPROGRESS);
        goal.setUuid(1001);
    }

    @Test
    public void processGoal() {
        Mockito.when(goalRepository.save(goal)).thenReturn(goal);
        Response response = new Response(String.valueOf(goal.getUuid()), null);
        ResponseEntity responseEntity = numberGeneratorHelper.processGoal(goal);
        Assert.assertEquals(202, responseEntity.getStatusCode().value());
        Assert.assertNotNull(responseEntity.getBody());
    }

    @Test
    public void getGoalStatus() {
        Response response = new Response(null, String.valueOf(goal.getStatus()));
        Mockito.when(goalRepository.findByUuid(goal.getUuid())).thenReturn(goal);
        Response output = numberGeneratorHelper.getGoalStatus(goal.getUuid());
        Assert.assertEquals(response.getResult(), output.getResult());
        Assert.assertNull(output.getTask());
    }

    @Test
    public void getNumList() {
        Response response = new Response("10,8,6,4,2,0", null);
        Response output = numberGeneratorHelper.getNumList(goal.getUuid());
        Assert.assertEquals(response.getTask(), output.getTask());
    }

    @Test(expected = NumberGeneratorException.class)
    public void readFile() {
        numberGeneratorHelper.readFile(999999);
    }

    @AfterClass
    public static void tearDown() {
        goal = null;
    }
}
