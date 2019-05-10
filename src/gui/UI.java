package gui;
import model.*;
import service.AgendaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UI {
    public static String datePattern = "dd-MM-yyyy";
    public static JFrame mainFrame = new JFrame("Personal Agenda");
    public static String buildTasksText(List<Task> tasks)
    {
        String tasksText = "";
        for(Task task : tasks)
        {
            if(task instanceof  CourseTask) {
                CourseTask task2 = (CourseTask) task;
                tasksText += "COURSE: " + task2.getStringObject();
            }
        }
        for(Task task : tasks)
        {
            if(task instanceof ExamTask)
            {
                ExamTask task2 = (ExamTask) task;
                tasksText += "EXAM: " + task2.getStringObject();
            }
        }
        for(Task task : tasks)
        {
            if(task instanceof MeetingTask)
            {
                MeetingTask task2 = (MeetingTask) task;
                tasksText += "MEETING: " + task2.getStringObject();
            }
        }
        for(Task task : tasks)
        {
            if(task instanceof AppointmentTask)
            {
                AppointmentTask task2 = (AppointmentTask) task;
                tasksText += "APPOINTMENT: " + task2.getStringObject();
            }
        }
        return tasksText;
    }
    public static void startMainWindow(List<Task> tasks)
    {
        String tasksText = buildTasksText(tasks);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int)screenSize.getWidth() - 50;
                int height = (int)screenSize.getHeight() - 50;

                JTextArea textArea = new JTextArea("TASKS\n\n" + tasksText);
                textArea.setColumns(50);
                textArea.setSize(width,height);
                textArea.setLineWrap(true);
                textArea.setRows(5);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);

                JTextField filter = new JTextField(15);
                filter.setSize(1000, 100);

                JButton fil1 = new JButton("Show Tasks Before Date");
                fil1.setBounds(100, 100, 140,40);
                fil1.setSize(0,height);


                ActionListener listener1 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date date;
                        try {
                            String strDate = filter.getText();
                            date = new SimpleDateFormat(datePattern).parse(strDate);
                            List<Task> currTasks = AgendaService.getTasksBeforeDate(date);
                            String currTasksText = buildTasksText(currTasks);
                            textArea.setText("TASKS\n\n" + currTasksText);
                        }
                        catch(Exception exceptie)
                        {
                            exceptie.printStackTrace();
                        }
                    }
                };
                fil1.addActionListener(listener1);

                JButton fil2 = new JButton("Show Tasks On Date");
                fil2.setBounds(100, 100, 140,40);
                fil2.setSize(0,height);

                ActionListener listener2 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date date;
                        try {
                            String strDate = filter.getText();
                            date = new SimpleDateFormat(datePattern).parse(strDate);
                            List<Task> currTasks = AgendaService.getTasksOnDate(date);
                            String currTasksText = buildTasksText(currTasks);
                            textArea.setText("TASKS\n\n" + currTasksText);
                        }
                        catch(Exception exceptie)
                        {
                            exceptie.printStackTrace();
                        }
                    }
                };
                fil2.addActionListener(listener2);

                JButton fil3 = new JButton("Show Tasks After Date");
                fil3.setBounds(100, 100, 140,40);
                fil3.setSize(0,height);

                ActionListener listener3 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date date;
                        try {
                            String strDate = filter.getText();
                            date = new SimpleDateFormat(datePattern).parse(strDate);
                            List<Task> currTasks = AgendaService.getTasksAfterDate(date);
                            String currTasksText = buildTasksText(currTasks);
                            textArea.setText("TASKS\n\n" + currTasksText);
                        }
                        catch(Exception exceptie)
                        {
                            exceptie.printStackTrace();
                        }
                    }
                };
                fil3.addActionListener(listener3);

                JButton fil4 = new JButton("Show All Tasks");
                fil4.setBounds(100, 100, 140,40);
                fil4.setSize(0,height);

                ActionListener listener4 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date date;
                        try {
                            String strDate = filter.getText();
                            date = new SimpleDateFormat(datePattern).parse(strDate);
                            List<Task> currTasks = AgendaService.getAgenda().getTasks();
                            String currTasksText = buildTasksText(currTasks);
                            textArea.setText("TASKS\n\n" + currTasksText);
                        }
                        catch(Exception exceptie)
                        {
                            exceptie.printStackTrace();
                        }
                    }
                };
                fil4.addActionListener(listener4);

                //JFrame mainFrame = new JFrame("Personal Agenda");
                mainFrame.setSize(width + 50, height + 50);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setLayout(new FlowLayout());


                JButton btn1 = new JButton("Pagina 1");
                btn1.setBounds(100, 100, 140,40);
                btn1.setSize(width,height);

                ActionListener comuteTo1 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        mainFrame.getContentPane().removeAll();
                        startMainWindow(tasks);
                    }
                };
                btn1.addActionListener(comuteTo1);

                JButton btn2 = new JButton("Pagina 2");
                btn2.setBounds(100, 100, 140,40);
                btn2.setSize(width,height);
                ActionListener comuteTo2 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("*");
                        mainFrame.getContentPane().removeAll();
                        mainFrame.repaint();
                        System.out.println("#");
                        startSecondaryWindow(tasks);
                    }
                };
                btn2.addActionListener(comuteTo2);

                //textArea.setText("DA");
                mainFrame.add(filter);
                mainFrame.add(fil1);
                mainFrame.add(fil2);
                mainFrame.add(fil3);
                mainFrame.add(fil4);
                mainFrame.add(textArea);
                mainFrame.add(btn1);
                mainFrame.add(btn2);
                mainFrame.setVisible(true);

            }

        });
    }
    static void startSecondaryWindow(List<Task> tasks)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth() - 50;
        int height = (int)screenSize.getHeight() - 50;

        JTextField idField = new JTextField(15);
        idField.setSize(1000, 100);




        ActionListener idFieldListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String currId = idField.getText();
                    System.out.println(AgendaService.getAgenda().getTasks().size());
                    System.out.println(currId);
                    AgendaService.removeTaskById(currId);
                    System.out.println(AgendaService.getAgenda().getTasks().size());
                }
                catch(Exception exceptie)
                {
                    exceptie.printStackTrace();
                }
            }
        };


        JButton remBtn = new JButton("Remove task by id");
        remBtn.setBounds(100, 100, 140,40);
        remBtn.setSize(0,height);

        remBtn.addActionListener(idFieldListener);

        JTextArea addTaskText = new JTextArea("Add the task in csv format in the first box and the task type in the second");
        addTaskText.setSize(width, 100);

        JTextField csvField = new JTextField(15);
        csvField.setSize(1000, 100);
        csvField.setColumns(20);

        JTextField typeField = new JTextField(15);
        typeField.setSize(1000, 100);
        typeField.setColumns(20);

        JButton submitTaskBtn = new JButton("Submit Task");
        submitTaskBtn.setBounds(100, 100, 140,40);
        submitTaskBtn.setSize(width,height);

        ActionListener sunbmitTaskLsn = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String csvtext = csvField.getText();
                String tasktype = typeField.getText();
                String csvsep = ", ";
                String [] line = csvtext.split(csvsep);
                SimpleDateFormat SimpleDateFormater = new SimpleDateFormat(datePattern);
                try {
                    switch (tasktype) {
                        case "AppointmentTask": {
                            AppointmentTask appointmentTask = new AppointmentTask
                                    (line[0], line[1], line[2], line[3], line[4], line[5], Integer.parseInt(line[6]),
                                            SimpleDateFormater.parse(line[7]));
                            AgendaService.addTask(appointmentTask);
                            break;
                        }
                        case "CourseTask":{
                            CourseTask courseTask = new CourseTask(line[0], line[1], line[2],
                                    line[3], line[4], Integer.parseInt(line[5]),
                                    SimpleDateFormater.parse(line[6]));
                            AgendaService.addTask(courseTask);
                            break;
                        }
                        case "ExamTask":{
                            ExamTask examTask = new ExamTask(line[0], line[1], line[2],
                                    line[3], line[4], Integer.parseInt(line[5]),
                                    SimpleDateFormater.parse(line[6]));
                            AgendaService.addTask(examTask);
                            break;
                        }
                        case "MeetingTask": {
                            MeetingTask meetingTask = new MeetingTask(line[0], line[1], line[2],
                                    line[3], Integer.parseInt(line[4]),
                                    SimpleDateFormater.parse(line[5]));
                            AgendaService.addTask(meetingTask);
                        }
                    }
                }
                catch(Exception exceptie)
                {
                    exceptie.printStackTrace();
                }

            }
        };

        submitTaskBtn.addActionListener(sunbmitTaskLsn);

        JButton btn1 = new JButton("Pagina 1");
        btn1.setBounds(100, 100, 140,40);
        btn1.setSize(width,height);

        ActionListener comuteTo1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainFrame.getContentPane().removeAll();
                startMainWindow(AgendaService.getAgenda().getTasks());
            }
        };
        btn1.addActionListener(comuteTo1);

        JButton btn2 = new JButton("Pagina 2");
        btn2.setBounds(100, 100, 140,40);
        btn2.setSize(width,height);
        ActionListener comuteTo2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                startSecondaryWindow(tasks);
            }
        };
        btn2.addActionListener(comuteTo2);

        mainFrame.setSize(width + 50, height + 50);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());

        mainFrame.add(addTaskText);
        mainFrame.add(csvField);
        mainFrame.add(typeField);
        mainFrame.add(submitTaskBtn);
        mainFrame.add(idField);
        mainFrame.add(remBtn);
        mainFrame.add(btn1);
        mainFrame.add(btn2);
        mainFrame.setVisible(true);
    }
    public static void startUI()
    {
        Agenda agenda = AgendaService.getAgenda();
        List<Task> tasks = agenda.getTasks();

        startMainWindow(tasks);
        //System.out.println("**");
    }
    static void SimpleJButton(){
        JFrame f=new JFrame("Button Example");
        JButton b=new JButton("Play");
        b.setBounds(100,100,140, 40);
        f.add(b);
        f.setSize(300,400);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        SimpleJButton();
    }
}
