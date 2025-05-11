import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class StopwatchClockApp {
    private JLabel clockLabel;
    private JLabel stopwatchLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private long startTime;
    private long elapsedTime;
    private boolean running = false;

    private Timer stopwatchTimer;

    public StopwatchClockApp() {
        JFrame frame = new JFrame("🕒 Stopwatch & Clock");
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(clockLabel);
        stopwatchLabel = new JLabel("00:00:00");
        stopwatchLabel.setFont(new Font("Arial", Font.BOLD, 30));
        stopwatchLabel.setForeground(Color.BLUE);
        frame.add(stopwatchLabel);
        startButton = new JButton("▶ Start");
        startButton.addActionListener(e -> startStopwatch());
        frame.add(startButton);
        stopButton = new JButton("⏸ Stop");
        stopButton.addActionListener(e -> stopStopwatch());
        frame.add(stopButton);
        resetButton = new JButton("🔄 Reset");
        resetButton.addActionListener(e -> resetStopwatch());
        frame.add(resetButton);
        startClock();
        frame.setVisible(true);
    }
    private void startClock() {
        Timer clockTimer = new Timer();
        clockTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentTime = sdf.format(new Date());
                clockLabel.setText("🕓 Current Time: " + currentTime);
            }
        }, 0, 1000);
    }
    private void startStopwatch() {
        if (!running) {
            running = true;
            startTime = System.currentTimeMillis() - elapsedTime;

            stopwatchTimer = new Timer();
            stopwatchTimer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (running) {
                        elapsedTime = System.currentTimeMillis() - startTime;
                        updateStopwatchLabel();
                    }
                }
            }, 0, 100);
        }
    }
    private void stopStopwatch() {
        running = false;
        if (stopwatchTimer != null) {
            stopwatchTimer.cancel();
        }
    }
    private void resetStopwatch() {
        stopStopwatch();
        elapsedTime = 0;
        stopwatchLabel.setText("00:00:00");
    }
    private void updateStopwatchLabel() {
        long seconds = elapsedTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;

        stopwatchLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StopwatchClockApp());
    }
}
