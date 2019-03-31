package model;

import java.util.Date;

public class ExamTask extends Task{
    private String teacherName, courseName;
    public ExamTask(String name, String teacherName, String courseName, String adress, String taskId, int minutesDuration, Date taskDate) {
        super(name, adress, taskId, minutesDuration, taskDate);
        this.teacherName = teacherName;
        this.courseName = courseName;
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
