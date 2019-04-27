package model;

import java.util.Date;

public class GymTask extends Task {
    private String workoutType;

    public GymTask(String workoutType, String name, String adress, String taskId, int minutesDuration, Date taskDate) {
        super(name, adress, taskId, minutesDuration, taskDate);
        this.workoutType = workoutType;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }
}
