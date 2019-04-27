package service;

import model.*;

import java.util.Date;

public class TaskService {
    private static Agenda agenda = new Agenda();

    public static Agenda getAgenda() {
        return agenda;
    }

    public static void setAgenda(Agenda agenda) {
        TaskService.agenda = agenda;
    }

    public static boolean isSameDate(Task task1, Task task2)
    {
        return task1.getTaskDate() == task2.getTaskDate();
    }
    public static boolean isSamePlace(Task task1, Task task2)
    {
        return task1.getAdress() == task2.getAdress();
    }
    public static String formatString(String unformattedString)
    {
        String formattedString = unformattedString.trim();
        return formattedString;
    }

    public static void formatAddress(Task task)
    {
        String address = task.getAdress();
        String formatedAdr = formatString(address);
        task.setAdress(formatedAdr);
    }

    public static void formatName(Task task)
    {
        String name = task.getName();
        String formatedName = formatString(name);
        task.setAdress(formatedName);
    }


}
