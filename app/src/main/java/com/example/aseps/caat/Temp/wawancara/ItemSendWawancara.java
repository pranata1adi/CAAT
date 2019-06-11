package com.example.aseps.caat.Temp.wawancara;

public class ItemSendWawancara {
    private int id;
    private String answer;

    public ItemSendWawancara(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public ItemSendWawancara(String answer) {
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