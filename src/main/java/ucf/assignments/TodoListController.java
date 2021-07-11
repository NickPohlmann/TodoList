package ucf.assignments;

import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TablePosition;
import javafx.scene.control.MenuItem;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.util.Date;
import java.util.ResourceBundle;

public class TodoListController implements Initializable {
    TodoList todoList;
    FilteredList<TodoItem> filteredList;

    @FXML
    private TableView<TodoItem> table;

    @FXML
    private TableColumn<TodoItem, Boolean> isComplete;

    @FXML
    private TableColumn<TodoItem, Date> dueDate;

    @FXML
    private TableColumn<TodoItem, String> description;

    @FXML
    private CheckBox showCompletedItems;

    @FXML
    private CheckBox showIncompleteItems;

    @FXML
    private Button addItemButton;

    @FXML
    private Button removeItem;

    @FXML
    private MenuItem clearList;

    @FXML
    private MenuItem importList;

    @FXML
    private MenuItem exportList;

    private Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
    }

    @FXML
    void clearListClicked(ActionEvent event) {

    }

    @FXML
    void exportListClicked(ActionEvent event) {
        //Export
        FileChooser exportfileChooser = new FileChooser();
        exportfileChooser.setTitle("Export File");
        File file = exportfileChooser.showSaveDialog(stage);
        if (file != null) {
            todoList.generateExportFile(file);
        }
    }

    @FXML
    void importListClicked(ActionEvent event) {
        FileChooser importfileChooser = new FileChooser();
        importfileChooser.setTitle("Select File");
        File file = importfileChooser.showOpenDialog(stage);
        if (file != null) {
            todoList.importItemsFromFile(file);
            filteredList = new FilteredList<TodoItem>(todoList.getList());
        }
    }

    @FXML
    void removeItemClicked(ActionEvent event) {
        TodoItem item = table.getSelectionModel().getSelectedItem();
        todoList.removeItem(item);
    }

    @FXML
    void showCompletedItemsClicked(ActionEvent event) {
        filteredList.setPredicate(todoItem -> {
            if (!showCompletedItems.isSelected() && todoItem.getIsComplete()) {
                return false;
            } else {
                return true;
            }
        });
    }

    @FXML
    void showIncompleteItemsClicked(ActionEvent event) {
        filteredList.setPredicate(todoItem -> {
            if (!showIncompleteItems.isSelected() && !todoItem.getIsComplete()) {
                return false;
            } else {
                return true;
            }
        });
    }



    @FXML
    public void addItemClicked(ActionEvent actionEvent) {
        System.out.println("Adding Item");
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ucf.assignments/AddItemModal.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        AddItemModalController addItemModalController = loader.getController();
        addItemModalController.setTodoList(todoList);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        todoList = new TodoList();
        todoList.addItem("Description 2", new Date (2000, 11, 21));
        todoList.addItem("Description 2", new Date (2000, 11, 21));
        todoList.addItem("Description 2", new Date (2000, 11, 21));
        filteredList = new FilteredList<TodoItem>(todoList.getList());
        table.setItems(filteredList);

        showIncompleteItems.setSelected(true);
        showCompletedItems.setSelected(true);
        dueDate.setCellValueFactory(new PropertyValueFactory<TodoItem, Date>("dueDate"));
        isComplete.setCellValueFactory(new PropertyValueFactory<TodoItem, Boolean>("isComplete"));
        description.setCellValueFactory(new PropertyValueFactory<TodoItem, String>("description"));


    }
}
