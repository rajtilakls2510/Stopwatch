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

    StopwatchState notRunningState;
    StopwatchState runningState;
    StopwatchState pausedState;
    StopwatchState stopPressedState;

    StopwatchState currentState;

    StopwatchSwing()
    {
        initializeJFrame();
        setUpPanels();
        addButtonListeners();
        initializeStates();
        frame.setVisible(true);
    }

    public void initializeJFrame()
    {
        // Initializing the JFrame
        frame = new JFrame("Stopwatch");
        frame.setSize(300,300);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setLayout(new GridLayout(2,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setUpPanels()
    {
        // Setting Up Panels
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

        timerDisplay = new JLabel();
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
    }
    public void addButtonListeners()
    {
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

    public void initializeStates()
    {
        // Initializing states
        notRunningState = new NotRunningStopwatchState(this);
        runningState = new RunningStopwatchState(this);
        pausedState = new PausedStopwatchState(this);
        stopPressedState = new StopPressedStopwatchState(this);
        currentState=stopPressedState;
        currentState.execute();
    }

    private void handleStartPress() {
        currentState.execute();

    }
    private void handleStopPress(){
        currentState = stopPressedState;
        currentState.execute();
    }

    public void setState(StopwatchState stopwatchState)
    {
        currentState = stopwatchState;
    }
    public void setStopwatch(Stopwatch sw){
        this.sw = sw;
    }
    public StopwatchState getNotRunningState() {
        return notRunningState;
    }

    public StopwatchState getRunningState() {
        return runningState;
    }

    public StopwatchState getPausedState() {
        return pausedState;
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
