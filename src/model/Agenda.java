package model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Task> tasks;
    public Agenda()
    {
        this.tasks = new ArrayList<Task>();
    }
    public Agenda(List<Task> tasks)
    {
        this.tasks = tasks;
    }
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
