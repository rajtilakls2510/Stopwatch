import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchSwing implements Observer {

    Stopwatch sw;
    JFrame frame;
    JPanel panel1, panel2;
    JLabel timerDisplay;
    JButton start, stop;
    final int NOT_RUNNING=0, RUNNING=1, PAUSED=2;
    int currentState;

    StopwatchSwing()
    {
        currentState=NOT_RUNNING;
        sw = new Stopwatch();
        sw.registerObserver(this);

        // Initializing the JFrame
        frame = new JFrame("Stopwatch");
        frame.setSize(300,300);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setLayout(new GridLayout(2,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting Up Panels
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

        timerDisplay = new JLabel("Time: "+formatTime(0L));
        Font font = timerDisplay.getFont();
        timerDisplay.setFont(new Font(font.getFontName(), font.getStyle(), 18));

        panel1.add(timerDisplay);


        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        start = new JButton("Start");
        stop = new JButton("Stop");
        stop.setVisible(false);
        panel2.add(start);
        panel2.add(stop);

        frame.add(panel1);
        frame.add(panel2);

        frame.setVisible(true);


        // Adding Listeners to the Buttons

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStartPress();
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStopPress();
            }
        });


    }

    private void handleStartPress() {
        if(currentState==NOT_RUNNING)
        {
            sw =  new Stopwatch();
            sw.registerObserver(this);
            sw.start();
            start.setText("Pause");
            stop.setVisible(true);
            currentState=RUNNING;
        }
        else if(currentState==RUNNING)
        {
            sw.stop();
            start.setText("Resume");
            currentState=PAUSED;
        }
        else if(currentState==PAUSED)
        {
            sw.start();
            start.setText("Pause");
            currentState=RUNNING;
        }

    }
    private void handleStopPress(){
        if(sw!=null)
            sw.stop();
        sw = null;
        timerDisplay.setText("Time: "+formatTime(0L));
        start.setText("Start");
        stop.setVisible(false);
        currentState=NOT_RUNNING;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StopwatchSwing();
            }
        });
    }

    String formatTime(long time)
    {
        int time1 = (int)time;
        int milis=0, secs = 0, minutes = 0, hours = 0;
        String formattedTime="";

        milis = time1%1000;
        time1 = time1 / 1000;
        secs = time1 % 60;
        time1 = time1 / 60;
        minutes = time1 % 60;
        time1 = time1 / 60;
        hours = time1 % 60;

        formattedTime = String.format("%02d", secs) + ":" +String.format("%03d", milis);
        if(minutes>0)
            formattedTime = String.format("%02d", minutes) + ":" + formattedTime;
        if(hours>0)
            formattedTime = String.format("%02d", hours) + ":" + formattedTime;
        return formattedTime;

    }
    @Override
    public void update(long time) {
        timerDisplay.setText("Time: "+formatTime(time));
    }
}
