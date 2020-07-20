package com.shashank.vmware.numbergenerator.controller;

import com.shashank.vmware.numbergenerator.model.Goal;
import com.shashank.vmware.numbergenerator.response.Response;
import com.shashank.vmware.numbergenerator.services.NumberGeneratorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Junit NumberGeneratorControllerTest class
 */
@RunWith(SpringRunner.class)
public class NumberGeneratorControllerTest {

    @Mock
    private NumberGeneratorService numberGeneratorService;

    @InjectMocks
    private NumberGeneratorController numberGeneratorController;

    @Test
    public void generateNumber() {
        Goal goal = new Goal();
        goal.setGoal("10");
        goal.setStep("2");
        Response response = new Response(String.valueOf(1001), null);
        Mockito.when(numberGeneratorService.processGoal(goal)).thenReturn(ResponseEntity.status(202).body(response));
        ResponseEntity responseEntity = numberGeneratorController.generateNumber(goal);
        Assert.assertEquals(responseEntity.getStatusCode().value(), 202);
        Assert.assertEquals(responseEntity.getBody(), response);
    }

    @Test
    public void getStatusOfGoal_1() {
        Response response = new Response(null, "SUCCESS");
        Mockito.when(numberGeneratorService.getGoalStatus(1)).thenReturn(response);
        Response output = numberGeneratorController.getStatusOfGoal(1);
        Assert.assertEquals(response, output);
    }

    @Test
    public void getStatusOfGoal_2() {
        Response response = new Response(null, "10,8,6,4,2,0");
        Mockito.when(numberGeneratorService.getGoalResponse(1, "get_numlist")).thenReturn(response);
        Response output = numberGeneratorController.getGoalResponse(1, "get_numlist");
        Assert.assertEquals(response, output);
    }
}
