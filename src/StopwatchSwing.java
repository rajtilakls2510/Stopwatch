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

    StopwatchSwing()
    {
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

        timerDisplay = new JLabel("Time: 0000");
        Font font = timerDisplay.getFont();
        timerDisplay.setFont(new Font(font.getFontName(), font.getStyle(), 18));

        panel1.add(timerDisplay);


        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        start = new JButton("Start");
        stop = new JButton("Stop");
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
        sw.start();
    }
    private void handleStopPress(){
        sw.stop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StopwatchSwing();
            }
        });
    }

    @Override
    public void update(long time) {
        timerDisplay.setText("Time: "+time);
    }
}
