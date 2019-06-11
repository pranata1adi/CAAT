package com.example.aseps.caat.Temp.observasi;

public class ItemObservasi {
    private int id;
    private String goal;
    private String deadline;
    private int id_pertanyaan;
    private String question;
    private String answer;
    private String file;

    public ItemObservasi() {
    }

    public ItemObservasi(String goal, String deadline, int id_pertanyaan, String question, String answer, String file) {
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
    }

    public ItemObservasi(int id, String goal, String deadline, int id_pertanyaan, String question, String answer, String file) {
        this.id = id;
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getId_pertanyaan() {
        return id_pertanyaan;
    }

    public void setId_pertanyaan(int id_pertanyaan) {
        this.id_pertanyaan = id_pertanyaan;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}