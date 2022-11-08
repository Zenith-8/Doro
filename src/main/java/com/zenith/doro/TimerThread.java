package com.zenith.doro;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class TimerThread extends Thread{

    private final Label clockLabel;
    private final Circle progressBar;
    private boolean isRunning;
    private final TimerElement timerElement;
    private final long seconds;
    private final double circumference;

    private final double stepSize = 0.05;

    public TimerThread(Label clockLabel, Circle progressBar, TimerElement timerElement, long seconds, double basis){
        this.clockLabel = clockLabel;
        this.progressBar = progressBar;
        this.timerElement = timerElement;
        this.seconds = seconds;
        this.circumference = basis;
        this.isRunning = false;
    }

    private void timerLoop(long seconds, double basis){
        long elapsedTime, startTime = System.currentTimeMillis();

        // Changing the incrementer to a small value will increase visual performance but has higher computational requirements O(n)
        /* A value of 0.05 is the smallest necessary for clean lines */

        try {
            for (double i = 0; i <= seconds && !Thread.currentThread().isInterrupted(); i += stepSize) {
                double value = (basis) * (i / seconds);
                elapsedTime = seconds - (System.currentTimeMillis() - startTime) / 1000;
                String label = ((int) elapsedTime / 60) + ":" + String.format("%02d", (int) (elapsedTime % 60));

                Platform.runLater(() -> {
                    clockLabel.setText(label);
                    progressBar.setStrokeDashOffset(value);
                });
                Thread.sleep((long) (stepSize * 1000));
            }
            if(seconds == 300){

            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        timerElement.playSound();

    }

    @Override
    public void run() {

        isRunning = true;

        try {
            while(!currentThread().isInterrupted()){
                System.out.println(Thread.activeCount());
                timerLoop(seconds, circumference);
                timerLoop(300, circumference);
            }
        } catch (Exception e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }

    public boolean isRunning(){
        return this.isRunning;
    }




}
