package model;

import java.util.Date;

public class AppointmentTask extends Task {
    private String hospitalName, doctorName, decription;
    public AppointmentTask(String name, String hospitalName, String doctorName, String description, String adress, String taskId, int minutesDuration, Date taskDate) {
        super(name, adress, taskId, minutesDuration, taskDate);
        this.hospitalName = hospitalName;
        this.decription = description;
        this.doctorName = doctorName;
    }
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
