package com.cmpe277.project.zeusrealty.model;

public class CustomNotification {
    String title;
    String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CustomNotification(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
