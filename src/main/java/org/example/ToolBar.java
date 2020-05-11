package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ToolBar extends javafx.scene.control.ToolBar {
    private MainView mainView;

    public ToolBar(MainView mainView) {

        this.mainView = mainView;
        Button step = new Button("Step");
        step.setOnAction(this::handleStep);
        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);
        Button reset = new Button("Reset");
        reset.setOnAction(this::handleReset);
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);
        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStop);
        
        this.getItems().addAll(draw, erase, reset, start, stop, step);

    }

    private void handleStop(ActionEvent actionEvent) {
        this.mainView.getSimulator().stop();
    }

    private void handleStart(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.SIMULATING);
        this.mainView.getSimulator().start();
    }

    private void handleReset(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.mainView.draw();
    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("Step Pressed");
        this.mainView.setApplicationState(MainView.SIMULATING);

        this.mainView.getSimulation().step();
        this.mainView.draw();
    }

    private void handleDraw(ActionEvent actionEvent) {
        System.out.println("Draw pressed");
        this.mainView.setDrawMode(Simulation.ALIVE);
    }

    private void handleErase(ActionEvent actionEvent) {
        System.out.println("Erased pressed");
        this.mainView.setDrawMode(Simulation.DEAD);
    }

}
