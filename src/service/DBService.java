package service;

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DBService {
    private  static String connectionStr;
    private static Connection connection;
    private static Statement statement;
    public static String datePattern = "dd-MM-yyyy";

    public static void init() {
        try {
            DBService.connectionStr = "jdbc:mysql://localhost:3306/agenda?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
            DBService.connection = DriverManager.getConnection(connectionStr, "root", "1234");

            DBService.statement = DBService.connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createTable(String tableName, String fields)
    {
        try {
            String strStatement = "create table " + tableName + fields + ";";
            statement.execute(strStatement);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void dropTable(String tableName)
    {
        try {
            String strStatement = "drop table " + tableName + ";";
            statement.execute(strStatement);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void deleteRow(String tableName, String task_id)
    {
        try {
            String strStatement = "delete from " + tableName +
                    " where task_id = '" + task_id + "';";
            statement.execute(strStatement);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void createExamTable(String tableName)
    {
          String fields = "(Name varchar(30), Teacher_Name varchar(30), Course_Name varchar(30), Address varchar(30)," +
                  "task_id varchar(30), minutes_duration varchar(20), date varchar(20))";
          createTable(tableName, fields);
    }
    public static void createCourseTable(String tableName)
    {
        String fields = "(Name varchar(30), Teacher_Name varchar(30), Course_Name varchar(30), Address varchar(30)," +
                "task_id varchar(30), minutes_duration varchar(20), date varchar(20))";
        createTable(tableName, fields);
    }
    public static void createMeetingTable(String tableName)
    {
        String fields = "(Name varchar(30), Company_name varchar(30), Address varchar(30)," +
                "task_id varchar(30), minutes_duration varchar(20), date varchar(20))";
        createTable(tableName, fields);
    }
    public static void createAppointmentTable(String tableName)
    {
        String fields = "(Name varchar(30), Hospital_name varchar(30), Doctor_name varchar(30), Description varchar(30)," +
                "Address varchar(30), task_id varchar(30), minutes_duration varchar(20), date varchar(20))";
        createTable(tableName, fields);
    }
    public static void addTaskToTable(String tableName, Task task, String taskType)
    {
        String fields = "";
        switch(taskType)
        {
            case "CourseTask": {
                CourseTask courseTask = (CourseTask) task;
                fields = "(Name, Teacher_Name, Course_Name, Address, task_id, minutes_duration,date) " +
                        "Values (" + courseTask.getStringObjectDB() + ")";
                break;
            }
            case "ExamTask": {
                ExamTask examTask = (ExamTask) task;
                fields = "(Name, Teacher_Name, Course_Name, Address, task_id, minutes_duration,date) " +
                        "Values (" + examTask.getStringObjectDB() + ")";
                break;
            }

            case "MeetingTask":
            {
                MeetingTask meetingTask = (MeetingTask) task;
                fields = "(Name, Company_name, Address, task_id, minutes_duration,date) " +
                        "Values (" + meetingTask.getStringObjectDB() + ")";
                break;
            }
            case "AppointmentTask":
            {
                AppointmentTask appointmentTask = (AppointmentTask) task;
                fields = "(Name, Hospital_name, Doctor_name, Description, Address, task_id, minutes_duration,date) " +
                        "Values (" + appointmentTask.getStringObjectDB() + ")";
                break;
            }


        }
        String strStatement = "Insert into " + tableName + " " + fields;
        try {
            statement.execute(strStatement);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static ArrayList<Task> readTasks(String tableName, String taskType)
    {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            String strStatement = "select * from " + tableName;
            ResultSet rs = statement.executeQuery(strStatement);

            switch (taskType) {
                case "ExamTask": {
                    while (rs.next()) {
                        ExamTask examTask = new ExamTask(rs.getString("name"),
                                rs.getString("teacher_name"), rs.getString("course_name"),
                                rs.getString("address"), rs.getString("task_id"),
                                Integer.parseInt(rs.getString("minutes_duration")),
                                new SimpleDateFormat(datePattern).parse(rs.getString("date")));


                        tasks.add(examTask);
                    }
                    break;
                }
                case "CourseTask": {
                    while (rs.next()) {
                        CourseTask courseTask = new CourseTask(rs.getString("name"),
                                rs.getString("teacher_name"), rs.getString("course_name"),
                                rs.getString("address"), rs.getString("task_id"),
                                Integer.parseInt(rs.getString("minutes_duration")),
                                new SimpleDateFormat(datePattern).parse(rs.getString("date")));


                        tasks.add(courseTask);
                    }
                    break;
                }
                case "MeetingTask": {
                    while (rs.next()) {
                        MeetingTask meetingTask = new MeetingTask(rs.getString("name"),
                                rs.getString("company_name"),
                                rs.getString("address"), rs.getString("task_id"),
                                Integer.parseInt(rs.getString("minutes_duration")),
                                new SimpleDateFormat(datePattern).parse(rs.getString("date")));


                        tasks.add(meetingTask);
                    }
                    break;
                }
                case "AppointmentTask": {
                    while (rs.next()) {
                        AppointmentTask appointmentTask = new AppointmentTask(rs.getString("name"),
                                rs.getString("hospital_name"), rs.getString("doctor_name"),
                                rs.getString("description"),
                                rs.getString("address"), rs.getString("task_id"),
                                Integer.parseInt(rs.getString("minutes_duration")),
                                new SimpleDateFormat(datePattern).parse(rs.getString("date")));


                        tasks.add(appointmentTask);
                    }
                    break;
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return tasks;
    }

}
