package model;

import service.FileService;

import java.util.Date;

public class MeetingTask extends Task {
    private String companyName;

    public MeetingTask(String name, String companyName, String adress, String taskId, int minutesDuration, Date taskDate) {
        super(name, adress, taskId, minutesDuration, taskDate);
        this.companyName = companyName;
    }

    public String getStringObject()
    {
        return FileService.prettifyStr(getName(), getCompanyName(), getAdress(), getTaskId(),
                Integer.toString(getMinutesDuration()),
                getStrDate(FileService.datePattern));
    }
    public String getStringObjectDB()
    {
        return FileService.prettifyStrDB(getName(), getCompanyName(), getAdress(), getTaskId(),
                Integer.toString(getMinutesDuration()),
                getStrDate(FileService.datePattern));
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
