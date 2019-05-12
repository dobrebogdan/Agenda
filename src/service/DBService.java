package service;

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    private static volatile String connectionStr;
    private static volatile Connection connection;
    public static volatile Statement statement;
    public static volatile ArrayList<String> statementList = new ArrayList<String> ();
    public static volatile Boolean dbthreadContinue;
    public static String datePattern = "dd-MM-yyyy";

    public static void statementDaemonFnc()
    {
        while(true)
        {
            if(!statementList.isEmpty() && statement!=null)
            {
                String strStatemt = statementList.get(0);
                statementList.remove(0);
                try {
                    statement.execute(strStatemt);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                if(statementList.isEmpty() && !dbthreadContinue)
                {
                    break;
                }
            }
        }
    }
    public static void addStatement(String strStatament)
    {
        statementList.add(strStatament);
    }

    public static void init() {
        statement = null;
        dbthreadContinue = true;
        Thread dbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(DBService.statement == null) {
                    try {
                        DBService.connectionStr = "jdbc:mysql://localhost:3306/agenda?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
                        DBService.connection = DriverManager.getConnection(connectionStr, "root", "1234");

                        DBService.statement = DBService.connection.createStatement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        dbThread.start();
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                statementDaemonFnc();
            }
        });
        daemonThread.start();
    }

    public static void createTable(String tableName, String fields)
    {
        String strStatement = "create table " + tableName + fields + ";";
        addStatement(strStatement);
    }
    public static void dropTable(String tableName)
    {
        String strStatement = "drop table " + tableName + ";";
        addStatement(strStatement);
    }
    public static void deleteRow(String tableName, String task_id)
    {
        String strStatement = "delete from " + tableName + " where task_id = '" + task_id + "';";
        addStatement(strStatement);
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
                addStatement(strStatement);
    }
    public static ArrayList<Task> readTasks(String tableName, String taskType)
    {
        while(!statementList.isEmpty() || statement == null)
        {

        }
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
    public static void resetTable(String tableName) {
        addStatement("Delete from " + tableName);
    }
    public static void rewriteDB()
    {
        String [] tables = {"Appointment_tasks", "Course_tasks", "Exam_tasks", "Meeting_tasks"};
        for(String table:tables)
        {
            resetTable(table);
        }
        List<Task> newTasks = AgendaService.getAgenda().getTasks();
        for(Task task:newTasks)
        {
            if(task instanceof AppointmentTask)
            {
                DBService.addTaskToTable("Appointment_tasks", task, "AppointmentTask");
            }
            if(task instanceof CourseTask)
            {
                DBService.addTaskToTable("Course_tasks", task, "CourseTask");
            }
            if(task instanceof ExamTask)
            {
                DBService.addTaskToTable("Exam_tasks", task, "ExamTask");
            }
            if(task instanceof MeetingTask)
            {
                DBService.addTaskToTable("Meeting_tasks", task, "MeetingTask");
            }
        }
    }

}
