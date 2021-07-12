/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */
package ucf.assignments;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListControllerTest {

    //Test clearing a small list
    @Test
    void clearSmallList() {
        TodoList todoList = new TodoList();
        todoList.addItem("Description 1", new Date(1999-02-24));
        todoList.addItem("Description 2", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.clearList();
        int actual = todoList.getList().size();
        int expected = 0;
        assertEquals(expected, actual);
    }

    //Tests clearing a large list
    @Test
    void clearLargeList() {
        TodoList todoList = new TodoList();
        todoList.addItem("Description 1", new Date(1999-02-24));
        todoList.addItem("Description 2", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.addItem("Description 3", new Date(1999-02-24));
        todoList.clearList();
        int actual = todoList.getList().size();
        int expected = 0;
        assertEquals(expected, actual);
    }

    //Tests clearing a list with nothing in it
    @Test
    void clearNullList() {
        TodoList todoList = new TodoList();

        todoList.clearList();
        int actual = todoList.getList().size();
        int expected = 0;
        assertEquals(expected, actual);
    }
}
