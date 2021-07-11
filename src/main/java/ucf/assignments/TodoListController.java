package ucf.assignments;

import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.util.Callback;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DateStringConverter;

import javax.management.Descriptor;
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
        todoList.clearList();
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
    void editItemClicked(ActionEvent event) {
        TodoItem item = table.getSelectionModel().getSelectedItem();

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
        todoList.addItem("description", "2000-11-21", "true");
        filteredList = new FilteredList<TodoItem>(todoList.getList());
        table.setItems(filteredList);

        table.setEditable(true);
        //description.setCellValueFactory(TextFieldTableCell.forTableColumn());
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TodoItem, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TodoItem, String> t) {
                ((TodoItem) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDescription(t.getNewValue());
            }
        });

        dueDate.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        dueDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TodoItem, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TodoItem, Date> t) {
                ((TodoItem) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDueDate(t.getNewValue());
            }
        });

        isComplete.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        //isComplete.setCellFactory( tc -> new CheckBoxTableCell<>());
        isComplete.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TodoItem, Boolean>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TodoItem, Boolean> t) {
                ((TodoItem) t.getTableView().getItems().get(t.getTablePosition().getRow())).setIsComplete(t.getNewValue());
            }
        });

        showIncompleteItems.setSelected(true);
        showCompletedItems.setSelected(true);
        dueDate.setCellValueFactory(new PropertyValueFactory<TodoItem, Date>("dueDate"));
        isComplete.setCellValueFactory(new PropertyValueFactory<TodoItem, Boolean>("isComplete"));
        description.setCellValueFactory(new PropertyValueFactory<TodoItem, String>("description"));



    }

    public void editDescription(TableColumn.CellEditEvent<TodoItem, String> todoItemStringCellEditEvent) {
        TodoItem item = table.getSelectionModel().getSelectedItem();
        item.setDescription(todoItemStringCellEditEvent.getNewValue());
    }


    public void editDueDate(TableColumn.CellEditEvent<TodoItem, Date> todoItemDateCellEditEvent) {
        TodoItem item = table.getSelectionModel().getSelectedItem();
        item.setDueDate((todoItemDateCellEditEvent.getNewValue()));
    }

    public void editIsComplete(TableColumn.CellEditEvent<TodoItem, Boolean> todoItemBooleanCellEditEvent) {
        TodoItem item = table.getSelectionModel().getSelectedItem();
        //int index = todoList.getList().indexOf(item);
        //todoList.getList().get(index).setIsComplete((todoItemBooleanCellEditEvent.getNewValue()));
        item.setIsComplete((todoItemBooleanCellEditEvent.getNewValue()));
    }
}
