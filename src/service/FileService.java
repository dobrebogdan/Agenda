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
    public static ArrayList<AppointmentTask> readAppointmentTasks(String csvFile) throws ParseException
    {
        ArrayList<String[]> lines = readLinesFromFile(csvFile);
        ArrayList<AppointmentTask> appointmentTasks = new ArrayList<AppointmentTask>();
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

    public static void writeAppointmentTask(FileWriter fileWriter, AppointmentTask currTask)
    {
        try {
            Date date = currTask.getTaskDate();
            String strDate = new SimpleDateFormat(datePattern).format(date);
            fileWriter.write(prettifyStr(currTask.getName(), currTask.getHospitalName(), currTask.getDecription(),
                             currTask.getAdress(), currTask.getTaskId(), Integer.toString(currTask.getMinutesDuration()),
                             strDate));
            fileWriter.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void recordAppointmentTasks(String csvFile, ArrayList<AppointmentTask> appointmentTasks)
    {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, false);
            for (AppointmentTask appointmentTask : appointmentTasks) {
                writeAppointmentTask(fileWriter, appointmentTask);
            }
            fileWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void writeCourseTask(FileWriter fileWriter, CourseTask currTask)
    {
        try {
            Date date = currTask.getTaskDate();
            String strDate = new SimpleDateFormat(datePattern).format(date);
            fileWriter.write(prettifyStr(currTask.getName(), currTask.getTeacherName(), currTask.getCourseName(),
                    currTask.getAdress(), currTask.getTaskId(), Integer.toString(currTask.getMinutesDuration()),
                    strDate));
            fileWriter.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void recordCourseTasks(String csvFile, ArrayList<CourseTask> courseTasks)
    {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, false);
            for (CourseTask courseTask : courseTasks) {
                writeCourseTask(fileWriter, courseTask);

            }
            fileWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void writeExamTask(FileWriter fileWriter, ExamTask currTask)
    {
        try {
            Date date = currTask.getTaskDate();
            String strDate = new SimpleDateFormat(datePattern).format(date);
            fileWriter.write(prettifyStr(currTask.getName(), currTask.getTeacherName(), currTask.getCourseName(),
                    currTask.getAdress(), currTask.getTaskId(), Integer.toString(currTask.getMinutesDuration()),
                    strDate));
            fileWriter.flush();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void recordExamTasks(String csvFile, ArrayList<ExamTask> examTasks)
    {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, false);
            for (ExamTask examTask : examTasks) {
                writeExamTask(fileWriter, examTask);
            }
            fileWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void writeMeetingTask(FileWriter fileWriter, MeetingTask currTask)
    {
        try {
            Date date = currTask.getTaskDate();
            String strDate = new SimpleDateFormat(datePattern).format(date);
            fileWriter.write(prettifyStr(currTask.getName(), currTask.getCompanyName(),
                    currTask.getAdress(), currTask.getTaskId(), Integer.toString(currTask.getMinutesDuration()),
                    strDate));
            fileWriter.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void recordMeetingTasks(String csvFile, ArrayList<MeetingTask> meetingTasks)
    {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, false);
            for (MeetingTask meetingTask : meetingTasks) {
                writeMeetingTask(fileWriter, meetingTask);
            }
            //fileWriter.flush();
            fileWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static ArrayList<ExamTask> readExamTasks(String csvFile) throws ParseException
    {
        ArrayList<String[]> lines = readLinesFromFile(csvFile);
        ArrayList<ExamTask> examTasks = new ArrayList<ExamTask>();
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
    public static ArrayList<CourseTask> readCourseTasks(String csvFile) throws ParseException
    {
        ArrayList<String[]> lines = readLinesFromFile(csvFile);
        ArrayList<CourseTask> courseTasks = new ArrayList<CourseTask>();
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
    public static ArrayList<MeetingTask> readMeetingTasks(String csvFile) throws ParseException
    {
        ArrayList<String[]> lines = readLinesFromFile(csvFile);
        ArrayList<MeetingTask> meetingTasks = new ArrayList<MeetingTask>();
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
