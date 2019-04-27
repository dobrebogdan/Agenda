//not usefull anymore
package model;

import java.util.ArrayList;
import java.util.List;

public class AgendaMonth {
    private List<AgendaDay> agendaDays;
    private int monthNumber;

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public AgendaMonth(int monthNumber)
    {
        this.monthNumber = monthNumber;
        this.agendaDays = new ArrayList<AgendaDay>();
    }

    public AgendaMonth(int monthNumber, List<AgendaDay> agendaDays)
    {
        this.monthNumber = monthNumber;
        this.agendaDays = agendaDays;
    }
    public List<AgendaDay> getAgendaDays() {
        return agendaDays;
    }

    public void setAgendaDays(List<AgendaDay> agendaDays) {
        this.agendaDays = agendaDays;
    }
}
