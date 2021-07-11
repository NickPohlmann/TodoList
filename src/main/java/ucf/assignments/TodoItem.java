/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */

package ucf.assignments;

import java.util.Date;

public class TodoItem extends TodoList{
    private String description;
    private Date dueDate;
    private boolean isComplete;

    public TodoItem (String description, Date dueDate, boolean isComplete){
        this.description = description;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
    }
}
