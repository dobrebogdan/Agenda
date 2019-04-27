import model.MeetingTask;
import model.SpecialTask;
import model.Task;
import service.AgendaService;

import java.util.*;

public class Main {
    public static void main(String [] args)
    {
        Calendar myCalendar = new GregorianCalendar(2019,5,28,1,1,1);
        Date date1 = myCalendar.getTime();
        SpecialTask specialTask1 = new SpecialTask("Plimba cainele",
                "Plimba cainele","Parcul Cismigiu","#4456", 30, date1);
        Calendar myCalendar2 = new GregorianCalendar(2019,6,28,1,1,1);
        Date date2 = myCalendar2.getTime();
        MeetingTask meetingTask1 = new MeetingTask("Intalnire de afaceri", "Bitdefender",
                "Lipscani nr 6","#4457", 90, date2);
        AgendaService.addTask(specialTask1);
        AgendaService.removeTask(specialTask1);
        AgendaService.addTask(specialTask1);
        AgendaService.addTask(meetingTask1);
        System.out.println("1 "+AgendaService.getTasksOnDate(date1).get(0).getTaskId());
        System.out.println("2 "+AgendaService.getTasksAfterDate(date1).get(0).getTaskId());
        System.out.println("3 "+AgendaService.getTasksBeforeDate(date2).get(0).getTaskId());
        System.out.println("4 "+AgendaService.getTaskById("#4456").getTaskId());
        AgendaService.removeTaskById("#4456");
        AgendaService.removeAllTasks();
        List<Task> tasks = new ArrayList<Task>();
        AgendaService.addTasks(tasks);
        tasks.add(specialTask1);
        tasks.add(meetingTask1);
        AgendaService.removeTasks(tasks);
    }
}
