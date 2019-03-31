import model.MeetingTask;
import model.SpecialTask;
import model.Task;
import service.UserService;

import java.util.*;

public class Main {
    public static void main(String [] args)
    {
         //TODO: use all the classes. ktxbye
        Calendar myCalendar = new GregorianCalendar(2019,5,28,1,1,1);
        Date date1 = myCalendar.getTime();
        SpecialTask specialTask1 = new SpecialTask("Plimba cainele",
                "Plimba cainele","Parcul Cismigiu","#4456", 30, date1);
        Calendar myCalendar2 = new GregorianCalendar(2019,6,28,1,1,1);
        Date date2 = myCalendar2.getTime();
        MeetingTask meetingTask1 = new MeetingTask("Intalnire de afaceri", "Bitdefender",
                "Lipscani nr 6","#4457", 90, date2);
        UserService.addTask(specialTask1);
        UserService.removeTask(specialTask1);
        UserService.addTask(specialTask1);
        UserService.addTask(meetingTask1);
        System.out.println("1 "+UserService.getTasksOnDate(date1).get(0).getTaskId());
        System.out.println("2 "+UserService.getTasksAfterDate(date1).get(0).getTaskId());
        System.out.println("3 "+UserService.getTasksBeforeDate(date2).get(0).getTaskId());
        System.out.println("4 "+UserService.getTaskById("#4456").getTaskId());
        UserService.removeTaskById("#4456");
        UserService.removeAllTasks();
        List<Task> tasks = new ArrayList<Task>();
        UserService.addTasks(tasks);
        tasks.add(specialTask1);
        tasks.add(meetingTask1);
        UserService.removeTasks(tasks);
    }
}
