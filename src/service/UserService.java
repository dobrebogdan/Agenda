package service;

import model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserService {
    private static Agenda agenda = new Agenda();

    public static Agenda getAgenda() {
        return agenda;
    }

    public static void setAgenda(Agenda agenda) {
        UserService.agenda = agenda;
    }

    public static List<Task> getTasksBeforeDate(Date date)
    {
        List<Task> tasksBeforeDate = new ArrayList<Task>();

        List<AgendaMonth> agendaMonths = agenda.getAgendaMonths();
        for(int i = 0; i < agendaMonths.size(); i++)
        {
            List<AgendaDay> agendaDays = agendaMonths.get(i).getAgendaDays();
            for(int j = 0; j < agendaDays.size(); j++)
            {
                List<Task> tasks = agendaDays.get(j).getTasks();
                for(int k = 0; k < tasks.size(); k++)
                {
                    if(tasks.get(k).getTaskDate().before(date))
                    {
                        tasksBeforeDate.add(tasks.get(k));
                    }
                }

            }

        }
        return tasksBeforeDate;
    }


    public static List<Task> getTasksAfterDate(Date date)
    {
        List<Task> tasksAfterDate = new ArrayList<Task>();

        List<AgendaMonth> agendaMonths = agenda.getAgendaMonths();
        for(int i = 0; i < agendaMonths.size(); i++)
        {
            List<AgendaDay> agendaDays = agendaMonths.get(i).getAgendaDays();
            for(int j = 0; j < agendaDays.size(); j++)
            {
                List<Task> tasks = agendaDays.get(j).getTasks();
                for(int k = 0; k < tasks.size(); k++)
                {

                    if(tasks.get(k).getTaskDate().after(date))
                    {
                        tasksAfterDate.add(tasks.get(k));
                    }
                }

            }

        }
        return tasksAfterDate;
    }

    public static List<Task> getTasksOnDate(Date date)
    {
        List<Task> tasksOnDate = new ArrayList<Task>();

        List<AgendaMonth> agendaMonths = agenda.getAgendaMonths();
        for(int i = 0; i < agendaMonths.size(); i++)
        {
            List<AgendaDay> agendaDays = agendaMonths.get(i).getAgendaDays();
            for(int j = 0; j < agendaDays.size(); j++)
            {
                List<Task> tasks = agendaDays.get(j).getTasks();
                for(int k = 0; k < tasks.size(); k++)
                {
                    if(tasks.get(k).getTaskDate().compareTo(date) == 0)
                    {
                        tasksOnDate.add(tasks.get(k));
                    }
                }

            }

        }
        return tasksOnDate;
    }

    public static void removeTask(Task task)
    {

        List<AgendaMonth> agendaMonths = agenda.getAgendaMonths();
        for (int i = 0; i < agendaMonths.size(); i++) {
            List<AgendaDay> agendaDays = agendaMonths.get(i).getAgendaDays();
            for (int j = 0; j < agendaDays.size(); j++) {
                List<Task> tasks = agendaDays.get(j).getTasks();
                for (int k = 0; k < tasks.size(); k++) {
                    if (tasks.get(k).getTaskId().equals(task.getTaskId())) {
                        tasks.remove(k);
                    }
                }
                agendaDays.get(j).setTasks(tasks);

            }
            agendaMonths.get(i).setAgendaDays(agendaDays);
        }
        agenda.setAgendaMonths(agendaMonths);
    }
    public static void removeTasks(List<Task> tasks)
    {
        for(int i = 0; i < tasks.size(); i++)
        {
            removeTask(tasks.get(i));
        }
    }

    public static void removeAllTasks()
    {
        agenda = new Agenda();
    }

    public static void addTask(Task task)
    {
        List<AgendaMonth> agendaMonths = agenda.getAgendaMonths();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(task.getTaskDate());
        boolean foundMounth = false, foundDay = false;
        for (int i = 0; i < agendaMonths.size(); i++) {
            if(agendaMonths.get(i).getMonthNumber() == calendar.get(Calendar.MONTH)) {
                foundMounth = true;
                List<AgendaDay> agendaDays = agendaMonths.get(i).getAgendaDays();
                for (int j = 0; j < agendaDays.size(); j++) {


                    if (agendaDays.get(j).getDay() == calendar.get(Calendar.DAY_OF_MONTH)) {
                        foundDay = true;
                        List<Task> tasks = agendaDays.get(j).getTasks();
                        tasks.add(task);
                        agendaDays.get(j).setTasks(tasks);
                    }

                }
                if(!foundDay)
                {
                    AgendaDay currDay = new AgendaDay(calendar.get(Calendar.DAY_OF_MONTH), task);
                    agendaDays.add(currDay);
                }
                agendaMonths.get(i).setAgendaDays(agendaDays);
            }
        }
        if(!foundMounth)
        {
            AgendaMonth currMonth = new AgendaMonth(calendar.get(Calendar.MONTH));
            AgendaDay currDay = new AgendaDay(calendar.get(Calendar.DAY_OF_MONTH), task);
            currMonth.getAgendaDays().add(currDay);
            agendaMonths.add(currMonth);
        }
        agenda.setAgendaMonths(agendaMonths);
    }


    public static void addTasks(List<Task> tasks)
    {
        for(int i = 0; i < tasks.size(); i++)
        {
            addTask(tasks.get(i));
        }
    }

    public static Task getTaskById(String searchedId)
    {
        List<AgendaMonth> agendaMonths = agenda.getAgendaMonths();
        Task searchedTask = null;
        for(int i = 0; i < agendaMonths.size(); i++)
        {
            List<AgendaDay> agendaDays = agendaMonths.get(i).getAgendaDays();
            for(int j = 0; j < agendaDays.size(); j++)
            {
                List<Task> tasks = agendaDays.get(j).getTasks();
                for(int k = 0; k < tasks.size(); k++)
                {
                    if(tasks.get(k).getTaskId().equals(searchedId))
                    {
                        searchedTask = tasks.get(k);
                    }
                }

            }

        }
        return searchedTask;
    }

    public static void removeTaskById(String searchedId)
    {
        Task searchedTask = getTaskById(searchedId);
        removeTask(searchedTask);
    }





}
