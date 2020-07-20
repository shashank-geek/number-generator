package com.shashank.vmware.numbergenerator.model;

import com.shashank.vmware.numbergenerator.enums.Status;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Goal repository table
 */
@Entity
public class Goal {

    @Id
    @GeneratedValue
    private Integer uuid;
    private String goal;
    private String step;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getUuid() {
        return uuid;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "uuid=" + uuid +
                ", goal='" + goal + '\'' +
                ", step='" + step + '\'' +
                ", status=" + status +
                '}';
    }
}
