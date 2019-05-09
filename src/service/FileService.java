package service;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileService {
    public static String datePattern = "dd-MM-yyyy";
    public static String prettifyStr(String ... strings)
    {
        String prettyStr = "";
        for(int i = 0; i < strings.length; i++)
        {
            prettyStr = prettyStr + strings[i];
            prettyStr += "";
            if(i != strings.length - 1)
            {
                prettyStr = prettyStr + ", ";
            }
        }
        prettyStr += "\n";
        return prettyStr;
    }
    public static String prettifyStrDB(String ... strings)
    {
        String prettyStr = "'";
        for(int i = 0; i < strings.length; i++) {
            prettyStr = prettyStr + strings[i];
            prettyStr += "'";
            if (i != strings.length - 1) {
                prettyStr = prettyStr + ", '";
            }
        }
        return prettyStr;
    }

    public static ArrayList<String []> readLinesFromFile(String csvFile)
    {
        BufferedReader br = null;
        String line = "";
        String csvSplitSeq = ", ";
        ArrayList<String[]> lines = new ArrayList<String[]>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] fields = line.split(csvSplitSeq);
                lines.add(fields);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }
    public static void writeStringToLogs(String csvFile, String text, boolean appendToFile)
    {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, appendToFile);
            fileWriter.write(text + "\n");
            fileWriter.flush();
            fileWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void writeStringToLogs(String csvFile, String text)
    {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, true);
            fileWriter.write(text + "\n");
            fileWriter.flush();
            fileWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static ArrayList<Task> readAppointmentTasks(String csvFile) throws ParseException
    {
        ArrayList<String[]> lines = readLinesFromFile(csvFile);
        ArrayList<Task> appointmentTasks = new ArrayList<Task>();
        SimpleDateFormat SimpleDateFormater = new SimpleDateFormat(datePattern);
        for(int i = 0; i < lines.size(); i++)
        {
            AppointmentTask appointmentTask = new AppointmentTask
                    (lines.get(i)[0], lines.get(i)[1], lines.get(i)[2],
                    lines.get(i)[3], lines.get(i)[4], lines.get(i)[5], Integer.parseInt(lines.get(i)[6]),
                    SimpleDateFormater.parse(lines.get(i)[7]));
            appointmentTasks.add(appointmentTask);
        }
        return appointmentTasks;
    }

    public static void writeTask(FileWriter fileWriter, Task currTask, String type)
    {
        try {
            switch (type) {
                case "CourseTask":
                    CourseTask courseTask = (CourseTask) currTask;
                    fileWriter.write(courseTask.getStringObject());
                    break;
                case "ExamTask":
                    ExamTask examTask = (ExamTask) currTask;
                    fileWriter.write(examTask.getStringObject());
                    break;
                case "AppointmentTask":
                    AppointmentTask appointmentTask = (AppointmentTask) currTask;
                    fileWriter.write(appointmentTask.getStringObject());
                    break;
                case "MeetingTask":
                    MeetingTask meetingTask = (MeetingTask) currTask;
                    fileWriter.write(meetingTask.getStringObject());
                    break;

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void recordTasks(String csvFile, ArrayList<Task> tasks, String type)
    {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, false);
            for (Task currTask : tasks) {
                writeTask(fileWriter, currTask, type);

            }
            fileWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> readExamTasks(String csvFile) throws ParseException
    {
        ArrayList<String[]> lines = readLinesFromFile(csvFile);
        ArrayList<Task> examTasks = new ArrayList<Task>();
        SimpleDateFormat SimpleDateFormater = new SimpleDateFormat(datePattern);
        for(int i = 0; i < lines.size(); i++)
        {
            ExamTask examTask = new ExamTask(lines.get(i)[0], lines.get(i)[1], lines.get(i)[2],
                    lines.get(i)[3], lines.get(i)[4], Integer.parseInt(lines.get(i)[5]),
                    SimpleDateFormater.parse(lines.get(i)[6]));
            examTasks.add(examTask);
        }
        return examTasks;
    }
    public static ArrayList<Task> readCourseTasks(String csvFile) throws ParseException
    {
        ArrayList<String[]> lines = readLinesFromFile(csvFile);
        ArrayList<Task> courseTasks = new ArrayList<Task>();
        SimpleDateFormat SimpleDateFormater = new SimpleDateFormat(datePattern);
        for(int i = 0; i < lines.size(); i++)
        {
            CourseTask courseTask = new CourseTask(lines.get(i)[0], lines.get(i)[1], lines.get(i)[2],
                    lines.get(i)[3], lines.get(i)[4], Integer.parseInt(lines.get(i)[5]),
                    SimpleDateFormater.parse(lines.get(i)[6]));
            courseTasks.add(courseTask);
        }
        return courseTasks;
    }
    public static ArrayList<Task> readMeetingTasks(String csvFile) throws ParseException
    {
        ArrayList<String[]> lines = readLinesFromFile(csvFile);
        ArrayList<Task> meetingTasks = new ArrayList<Task>();
        SimpleDateFormat SimpleDateFormater = new SimpleDateFormat(datePattern);
        for(int i = 0; i < lines.size(); i++)
        {
            MeetingTask meetingTask = new MeetingTask(lines.get(i)[0], lines.get(i)[1], lines.get(i)[2],
                    lines.get(i)[3], Integer.parseInt(lines.get(i)[4]),
                    SimpleDateFormater.parse(lines.get(i)[5]));
            meetingTasks.add(meetingTask);
        }
        return meetingTasks;
    }

}
