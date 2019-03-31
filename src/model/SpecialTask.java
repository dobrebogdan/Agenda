package model;

import java.util.Date;

public class SpecialTask extends Task{
    private String taskDescription;
    public SpecialTask(String name, String taskDescription, String adress, String taskId, int minutesDuration, Date taskDate)
    {
        super(name, adress, taskId, minutesDuration, taskDate);
        this.taskDescription = taskDescription;

    }
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
