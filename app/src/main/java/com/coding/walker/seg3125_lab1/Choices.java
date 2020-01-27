package com.coding.walker.seg3125_lab1;

public class Choices {
    private String id;
    private String body;

    public Choices(String id, String body, String questionId) {
        this.id = id;
        this.body = body;
        this.questionId = questionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    private String questionId;
}
