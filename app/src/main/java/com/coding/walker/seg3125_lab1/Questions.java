package com.coding.walker.seg3125_lab1;

public class Questions {
    private String id;
    private String title;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private String answer;

    public Questions(String id, String title, String answer) {
        this.id = id;
        this.title = title;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
