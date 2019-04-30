package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LoggerService {
    private static String csvFile = "C:\\Users\\Bogdan\\Desktop\\Personal_Agenda\\data\\Logs.csv";
    private static Boolean firstLog = true;
    public static void writeLog(String text)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String logText = dateFormat.format(date) + ": " + text;
        if(firstLog == true) {
            FileService.writeStringToLogs(csvFile, logText, false);
            firstLog = false;
        }
        else
        {
            FileService.writeStringToLogs(csvFile, logText);
        }
    }
    public static void writeLog(String text, boolean appendToFile)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        FileService.writeStringToLogs(csvFile,dateFormat.format(date) + ": " + text, appendToFile);
    }
}
