package com.shashank.vmware.numbergenerator.services;

import com.shashank.vmware.numbergenerator.exception.NumberGeneratorException;
import com.shashank.vmware.numbergenerator.model.Goal;
import com.shashank.vmware.numbergenerator.response.Response;
import com.shashank.vmware.numbergenerator.util.NumberGeneratorHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Junit NumberGeneratorServiceTest class
 */
@RunWith(SpringRunner.class)
public class NumberGeneratorServiceTest {

    @Mock
    private NumberGeneratorHelper numberGeneratorHelper;

    @InjectMocks
    private NumberGeneratorService numberGeneratorService;

    @Test
    public void processGoal() {
        Goal goal = new Goal();
        goal.setGoal("10");
        goal.setStep("2");
        Response response = new Response(String.valueOf(1001), null);
        Mockito.when(numberGeneratorHelper.processGoal(goal)).thenReturn(ResponseEntity.status(202).body(response));
        ResponseEntity responseEntity = numberGeneratorService.processGoal(goal);
        Assert.assertEquals(responseEntity.getStatusCode().value(), 202);
        Assert.assertEquals(responseEntity.getBody(), response);
    }

    @Test
    public void getGoalStatus() {
        Integer uuid = 1;
        Response response = new Response(null, "SUCCESS");
        Mockito.when(numberGeneratorHelper.getGoalStatus(uuid)).thenReturn(response);
        Response output = numberGeneratorService.getGoalStatus(uuid);
        Assert.assertEquals(response, output);
    }

    @Test
    public void getGoalResponse_1() {
        Integer uuid = 1;
        Response response = new Response(null, "10,8,6,4,2,0");
        Mockito.when(numberGeneratorHelper.getNumList(uuid)).thenReturn(response);
        Response output = numberGeneratorService.getGoalResponse(uuid, "get_numlist");
        Assert.assertEquals(response, output);
    }

    @Test(expected = NumberGeneratorException.class)
    public void getGoalResponse_2() {
        Integer uuid = 1;
        Response output = numberGeneratorService.getGoalResponse(uuid, "AnotherAction");
        Assert.assertEquals(null, output);
    }
}