package model;

import service.FileService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseTask extends Task{
    private String teacherName, courseName;

    public CourseTask(String name, String teacherName, String courseName, String adress, String taskId, int minutesDuration, Date taskDate) {
        super(name, adress, taskId, minutesDuration, taskDate);
        this.teacherName = teacherName;
        this.courseName = courseName;

    }

    public String getStringObject()
    {
        return FileService.prettifyStr(getName(), getTeacherName(), getCourseName(),
                getAdress(), getTaskId(), Integer.toString(getMinutesDuration()),
                getStrDate(FileService.datePattern));
    }

    public String getStringObjectDB()
    {
        return FileService.prettifyStrDB(getName(), getTeacherName(), getCourseName(),
                getAdress(), getTaskId(), Integer.toString(getMinutesDuration()),
                getStrDate(FileService.datePattern));
    }
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
