import gui.UI;
import model.*;
import service.*;

import java.util.*;

public class Main {
    public static void main(String [] args) {
        DBService.init();
        ArrayList<Task> appointmentTasks = DBService.readTasks("Appointment_tasks", "AppointmentTask");
        ArrayList<Task> meetingTasks = DBService.readTasks("Meeting_Tasks", "MeetingTask");
        ArrayList<Task> examTasks = DBService.readTasks("Exam_tasks", "ExamTask");
        ArrayList<Task> courseTasks = DBService.readTasks("Course_tasks", "CourseTask");

        AgendaService.addTasks(courseTasks);
        AgendaService.addTasks(examTasks);
        AgendaService.addTasks(meetingTasks);
        AgendaService.addTasks(appointmentTasks);
        try
        {
            UI.startUI();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
