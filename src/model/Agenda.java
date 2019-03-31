package model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<AgendaMonth> agendaMonths;
    public Agenda()
    {
        this.agendaMonths = new ArrayList<AgendaMonth>();
    }
    public Agenda(List<AgendaMonth> agendaMonths)
    {
        this.agendaMonths = agendaMonths;
    }
    public List<AgendaMonth> getAgendaMonths() {
        return agendaMonths;
    }

    public void setAgendaMonths(List<AgendaMonth> agendaMonths) {
        this.agendaMonths = agendaMonths;
    }
}
