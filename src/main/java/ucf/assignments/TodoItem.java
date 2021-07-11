/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */

package ucf.assignments;

import java.util.Date;

public class TodoItem {
    private String description;
    private Date dueDate;
    private Boolean isComplete;

    public TodoItem() {
        this.description = "";
        this.isComplete = null;
        this.isComplete = null;
    }

    public TodoItem (String description, Date dueDate, Boolean isComplete){
        this.description = description;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }
}
