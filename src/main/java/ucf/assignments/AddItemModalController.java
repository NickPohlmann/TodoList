/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */
package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AddItemModalController {

    private TodoList list;

    @FXML
    private TextField newDescription;

    @FXML
    private DatePicker newDate;

    @FXML
    private Button addNewItem;

    @FXML
    void addNewItemClicked(ActionEvent event) {
        list.addItem(newDescription.getText(), java.sql.Date.valueOf(newDate.getValue()));
        Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        stage.close();
    }

    public void setTodoList(TodoList list) {
        this.list = list;
    }


}
