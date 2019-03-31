package model;

import java.util.ArrayList;
import java.util.List;

public class AgendaDay {
    private int day;
    private List<Task>  tasks;

    public AgendaDay(int day)
    {
        this.day = day;
        this.tasks = null;
    }

    public AgendaDay(int day, List<Task> tasks)
    {
        this.day = day;
        this.tasks = tasks;
    }

    public AgendaDay(int day, Task task)
    {
        this.day = day;
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task);
        this.tasks = tasks;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
