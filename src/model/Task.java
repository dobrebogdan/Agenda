package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import static service.FileService.datePattern;

public abstract class Task {
    protected String adress, taskId, name;
    protected int minutesDuration;
    protected Date taskDate;

    public String getStrDate(String dateFormat)
    {
        String strDate = new SimpleDateFormat(datePattern).format(taskDate);
        return strDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Task(String name, String adress, String taskId, int minutesDuration, Date taskDate)
    {
        this.name = name;
        this.adress = adress;
        this.taskId = taskId;

        this.minutesDuration = minutesDuration;
        this.taskDate = taskDate;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public int getMinutesDuration() {
        return minutesDuration;
    }

    public void setMinutesDuration(int minutesDuration) {
        this.minutesDuration = minutesDuration;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
