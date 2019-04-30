import model.*;
import service.*;

import java.util.*;

public class Main {
    public static void main(String [] args) {
        ArrayList<AppointmentTask> appointmentTasks = new ArrayList<AppointmentTask>();
        ArrayList<MeetingTask> meetingTasks = new ArrayList<MeetingTask>();
        ArrayList<ExamTask> examTasks = new ArrayList<ExamTask>();
        ArrayList<CourseTask> courseTasks = new ArrayList<CourseTask>();
        String path = "C:\\Users\\Bogdan\\Desktop\\Personal_Agenda\\data\\";
        try {

            appointmentTasks = FileService.readAppointmentTasks(path + "AppointmentTasks.csv");
            examTasks = FileService.readExamTasks(path + "ExamTasks.csv");
            courseTasks = FileService.readCourseTasks(path + "CourseTasks.csv");
            meetingTasks = FileService.readMeetingTasks(path + "MeetingTasks.csv");
        }
        catch(Exception e)
        {
            e.printStackTrace();;
        }
        AgendaService.addTask(meetingTasks.get(0));
        AgendaService.removeTask(meetingTasks.get(0));
        AgendaService.addTask(courseTasks.get(0));
        AgendaService.addTask(courseTasks.get(1));
        AgendaService.addTask(meetingTasks.get(0));
        System.out.println(courseTasks.get(0).getTaskDate());
        System.out.println("1 " + AgendaService.getTasksOnDate(courseTasks.get(0).getTaskDate()).get(0).getTaskId());
        //System.out.println("2 " + AgendaService.getTasksAfterDate(courseTasks.get(0).getTaskDate()).get(0).getTaskId());
        //System.out.println("3 " + AgendaService.getTasksBeforeDate(courseTasks.get(1).getTaskDate()).get(0).getTaskId());
        //System.out.println("4 " + AgendaService.getTaskById(courseTasks.get(1).getTaskId()).getTaskId());

        AgendaService.removeTaskById(courseTasks.get(0).getTaskId());
        AgendaService.removeAllTasks();
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(courseTasks.get(0));
        tasks.add(courseTasks.get(1));
        AgendaService.addTasks(tasks);
        AgendaService.removeTasks(tasks);
        try {
            System.out.println(meetingTasks.size());
            FileService.recordMeetingTasks(path + "MeetingTasks.csv", meetingTasks);
            FileService.recordExamTasks(path+"ExamTasks.csv", examTasks);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
