/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("here - START");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ucf.assignments/TodoList.fxml"));
            Parent root = loader.load();
            TodoListController controller = loader.getController();
            controller.setStage(primaryStage);


            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Todo List");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

