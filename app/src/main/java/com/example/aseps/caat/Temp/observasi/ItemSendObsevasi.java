package com.example.aseps.caat.Temp.observasi;

public class ItemSendObsevasi {
    private int id;
    private String answer;

    public ItemSendObsevasi(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public ItemSendObsevasi(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}