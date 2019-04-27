package model;

import java.util.Date;

public class ShoppingTask extends Task {
    private String [] items;
    public ShoppingTask(String [] items, String name, String adress, String taskId, int minutesDuration, Date taskDate) {
        super(name, adress, taskId, minutesDuration, taskDate);
        this.items = items;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}
