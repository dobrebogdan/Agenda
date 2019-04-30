package service;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class AgendaService {
    private static Agenda agenda = new Agenda();

    public static Agenda getAgenda() {
        return agenda;
    }

    public static void setAgenda(Agenda agenda) {
        AgendaService.agenda = agenda;
    }

    public static List<Task> getTasksBeforeDate(Date date)
    {
        LoggerService.writeLog("Function getTasksBeforeDate");
        List<Task> tasksBeforeDate = new ArrayList<Task>();
        List<Task> tasks = agenda.getTasks();
        for(int k = 0; k < tasks.size(); k++)
        {
            if(tasks.get(k).getTaskDate().before(date))
            {
                tasksBeforeDate.add(tasks.get(k));
            }
        }

        return tasksBeforeDate;
    }


    public static List<Task> getTasksAfterDate(Date date)
    {
        LoggerService.writeLog("Function getTasksAfterDate");
        List<Task> tasksAfterDate = new ArrayList<Task>();
        List<Task> tasks = agenda.getTasks();
        for(int k = 0; k < tasks.size(); k++)
        {
            if(tasks.get(k).getTaskDate().after(date))
            {
                tasksAfterDate.add(tasks.get(k));
            }
        }

        return tasksAfterDate;
    }

    public static List<Task> getTasksOnDate(Date date)
    {
        LoggerService.writeLog("Function getTasksOnDate");
        List<Task> tasksOnDate = new ArrayList<Task>();
        List<Task> tasks = agenda.getTasks();
        for(int k = 0; k < tasks.size(); k++)
        {
            if(tasks.get(k).getTaskDate().compareTo(date) == 0)
            {
                        tasksOnDate.add(tasks.get(k));
            }
        }

        return tasksOnDate;
    }

    public static void removeTask(Task task)
    {
        LoggerService.writeLog("Function removeTask");
        List<Task> tasks = agenda.getTasks();
        for (int k = 0; k < tasks.size(); k++) {
            if (tasks.get(k).getTaskId().equals(task.getTaskId())) {
                tasks.remove(k);
            }
        }
    }
    public static void removeTasks(List<Task> tasks)
    {
        LoggerService.writeLog("Function removeTasks");
        for(int i = 0; i < tasks.size(); i++)
        {
            removeTask(tasks.get(i));
        }
    }

    public static void removeAllTasks()
    {
        LoggerService.writeLog("Function removeAllTask");
        agenda = new Agenda();
    }

    public static void addTask(Task task)
    {
        LoggerService.writeLog("Function addTask");
        List<Task> tasks = agenda.getTasks();
        tasks.add(task);

    }


    public static void addTasks(List<Task> tasks)
    {
        LoggerService.writeLog("Function addTasks");
        for(int i = 0; i < tasks.size(); i++)
        {
            addTask(tasks.get(i));
        }
    }

    public static Task getTaskById(String searchedId)
    {
        LoggerService.writeLog("Function getTaskById");
        List<Task> tasks = agenda.getTasks();
        Task searchedTask = null;
        for(int i = 0; i < tasks.size(); i++)
        {
            if(tasks.get(i).getTaskId().equals(searchedId))
            {
                searchedTask = tasks.get(i);
            }

        }
        return searchedTask;
    }

    public static void removeTaskById(String searchedId)
    {
        LoggerService.writeLog("Function removeTaskById");
        Task searchedTask = getTaskById(searchedId);
        removeTask(searchedTask);
    }


}
