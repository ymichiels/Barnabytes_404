package sample.util;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Clock {

    private int elapsedMillis = 0;
    private int lastClockTime = 0;
    private Timeline time = new Timeline();

    private int seconds;
    private int minutes;
    private JFXButton button;
    private Label label;

    public Clock(JFXButton button, Label label) {
        configureTimeline(button, label);
    }

    private void configureTimeline(JFXButton button, Label label) {
        time.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(47), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                calculate(button, label);
            }
        });
        time.getKeyFrames().add(keyFrame);
    }

    public void calculate(JFXButton button, Label label) {
        if (lastClockTime == 0) {
            lastClockTime = (int) System.currentTimeMillis();
        }

        int now = (int) System.currentTimeMillis();
        int delta = 60; // pour voir le passage à 60 minutes

        elapsedMillis += delta;

        seconds = (elapsedMillis / 1000) % 60;
        minutes = (elapsedMillis / 60000);
        refreshTimerString(seconds, minutes, button, label);
        lastClockTime = now;
    }

    public void refreshTimerString(int seconds, int minutes, JFXButton button, Label label) {
        if (minutes == 60) {
            button.setText("Arrêter le match");
        }
        label.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
    }

    public void startStop() {
        if (time.getStatus() != Animation.Status.STOPPED) {
            time.stop();
            lastClockTime = 0;
        } else {
            time.play();
        }
    }

    public void stopReset() {
        if (time.getStatus() != Animation.Status.STOPPED) {
            time.stop();
            lastClockTime = 0;
        } else {
            lastClockTime = 0;
            elapsedMillis = 0;
        }
    }

    public boolean isStopped() {
        return time.getStatus() == Animation.Status.STOPPED;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}


