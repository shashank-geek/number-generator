package com.shashank.vmware.numbergenerator.controller;

import com.shashank.vmware.numbergenerator.model.Goal;
import com.shashank.vmware.numbergenerator.response.Response;
import com.shashank.vmware.numbergenerator.services.NumberGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to expose REST API to the outside world
 */
@RestController
@RequestMapping("/api")
public class NumberGeneratorController {
    /**
     * Logger instance to used from SL4J to log the info, debug and error
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NumberGeneratorController.class);

    /**
     * Service class instance instantiation
     */
    @Autowired
    private NumberGeneratorService numberGeneratorService;

    /**
     * API exposed to generate and process the goal
     *
     * @param request Goal
     * @return ResponseEntity
     */
    @PostMapping(value = "/generate", consumes = "application/json", produces = "application/json")
    public ResponseEntity generateNumber(@RequestBody Goal request) {
        LOGGER.info("Request: {}", request.toString());
        return numberGeneratorService.processGoal(request);
    }

    /**
     * API exposed to get the status of the goal
     *
     * @param uuid UUID
     * @return Response
     */
    @GetMapping(value = "/tasks/{uuid}/status", consumes = "application/json", produces = "application/json")
    public Response getStatusOfGoal(@PathVariable Integer uuid) {
        LOGGER.info("UUID: {}", uuid);
        return numberGeneratorService.getGoalStatus(uuid);
    }

    /**
     * API exposed to get the steps which is generated using the above API: "/generate"
     *
     * @param uuid   UUID
     * @param action Ex: get_numlist
     * @return Response
     */
    @GetMapping(value = "/tasks/{uuid}", consumes = "application/json", produces = "application/json")
    public Response getGoalResponse(@PathVariable Integer uuid, @RequestParam String action) {
        LOGGER.info("UUID: {} and Action: {}", uuid, action);
        return numberGeneratorService.getGoalResponse(uuid, action);
    }
}
