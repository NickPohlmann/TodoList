/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TodoList {
    ObservableList<TodoItem> list;

    public TodoList() {
        list = FXCollections.observableArrayList();
    }

    public ObservableList<TodoItem> getList () {
        return list;
    }

    //Add item to list. Create new item and then add to list
    public void addItem(String description, Date dueDate) {
        TodoItem item = new TodoItem(description, dueDate, false);
        list.add(item);
    }

    public void addItem(String description, String dueDateStr, String isCompleteStr) {
        Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("YYYY-MM-DD").parse(dueDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Boolean isComplete = Boolean.parseBoolean(isCompleteStr);
        TodoItem item = new TodoItem(description, dueDate, isComplete);
        list.add(item);
    }

    //Remove item from list. Find item and then remove from list
    public void removeItem(TodoItem item){
        list.remove(item);
    }

    //Clear all items. Empty the list
    public void clearList() {
        list.clear();
    }

    public void generateExportFile(File file) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);

            for (TodoItem item : list) {
                fw.write(item.getDescription() + "," + item.getDueDate() + "," + item.getIsComplete()+ "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importItemsFromFile(File file) {
        Scanner in = null;
        try {
            in = new Scanner(file);
            while (in.hasNextLine()) {
                String data = in.nextLine();
                String[] splitData = data.split(",");
                addItem(splitData[0], splitData[1], splitData[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
