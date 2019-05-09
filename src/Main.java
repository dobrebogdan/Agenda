import model.*;
import service.*;

import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String [] args) {
        DBService.init();
        ArrayList<Task> appointmentTasks = DBService.readTasks("Appointment_tasks", "AppointmentTask");
        ArrayList<Task> meetingTasks = DBService.readTasks("Meeting_Tasks", "MeetingTask");
        ArrayList<Task> examTasks = DBService.readTasks("Exam_tasks", "ExamTask");
        ArrayList<Task> courseTasks = DBService.readTasks("Course_tasks", "CourseTask");

        AgendaService.addTask(meetingTasks.get(0));
        AgendaService.removeTask(meetingTasks.get(0));
        AgendaService.addTask(courseTasks.get(0));
        AgendaService.addTask(courseTasks.get(1));
        AgendaService.addTask(meetingTasks.get(0));
        System.out.println(courseTasks.get(0).getTaskDate());
        System.out.println("1 " + AgendaService.getTasksOnDate(courseTasks.get(0).getTaskDate()).get(0).getTaskId());
        System.out.println("2 " + AgendaService.getTasksAfterDate(courseTasks.get(0).getTaskDate()).get(0).getTaskId());
        System.out.println("3 " + AgendaService.getTasksBeforeDate(courseTasks.get(1).getTaskDate()).get(0).getTaskId());
        System.out.println("4 " + AgendaService.getTaskById(courseTasks.get(1).getTaskId()).getTaskId());

        AgendaService.removeTaskById(courseTasks.get(0).getTaskId());
        AgendaService.removeAllTasks();
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(courseTasks.get(0));
        tasks.add(courseTasks.get(1));
        AgendaService.addTasks(tasks);
        AgendaService.removeTasks(tasks);

        try
        {


            /*String strStatement = "select * from students";
            ResultSet resultSet= statement.executeQuery(strStatement);
            while(resultSet.next())
            {
                System.out.println(resultSet.getString("nAmE"));
            }*/

            //DBService.deleteRow("Exam_tasks","000h");
            ArrayList<Task> tasksdb = DBService.readTasks("Appointment_tasks", "AppointmentTask");
            System.out.println(tasksdb.get(1).getName());
            //DBService.createAppointmentTable("Appointment_Tasks");
            DBService.addTaskToTable("Exam_Tasks", (Task) examTasks.get(1), "ExamTask");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
