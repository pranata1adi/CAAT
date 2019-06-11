package com.example.aseps.caat.Temp.kuisioner;

public class ItemKuisioner {
    private int id;
    private String goal;
    private String deadline;
    private int id_pertanyaan;
    private String question;
    private String answer;
    private String file;
    private int maksimal;
    private int nilaiSkg;
    private int jawaban1;
    private int jawaban2;
    private int jawaban3;
    private int jawaban4;
    private int jawaban5;
    private String jalurnya;

    public ItemKuisioner(String goal, String deadline, int id_pertanyaan, String question,
                         String answer, String file, int maksimal, int jawaban1, int jawaban2,
                         int jawaban3, int jawaban4, int jawaban5) {
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
        this.maksimal = maksimal;
        this.jawaban1 = jawaban1;
        this.jawaban2 = jawaban2;
        this.jawaban3 = jawaban3;
        this.jawaban4 = jawaban4;
        this.jawaban5 = jawaban5;
    }

    public ItemKuisioner(int id, String goal, String deadline, int id_pertanyaan, String question,
                         String answer, String file, int maksimal, int jawaban1, int jawaban2,
                         int jawaban3, int jawaban4, int jawaban5) {
        this.id = id;
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
        this.maksimal = maksimal;
        this.jawaban1 = jawaban1;
        this.jawaban2 = jawaban2;
        this.jawaban3 = jawaban3;
        this.jawaban4 = jawaban4;
        this.jawaban5 = jawaban5;
    }

    public ItemKuisioner(String goal, String deadline, int id_pertanyaan, String question,
                         String answer, String file, int maksimal, int jawaban1, int jawaban2,
                         int jawaban3, int jawaban4, int jawaban5,String jalurnya) {
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
        this.maksimal = maksimal;
        this.jawaban1 = jawaban1;
        this.jawaban2 = jawaban2;
        this.jawaban3 = jawaban3;
        this.jawaban4 = jawaban4;
        this.jawaban5 = jawaban5;
        this.jalurnya = jalurnya;
    }

    public ItemKuisioner(int id, String goal, String deadline, int id_pertanyaan, String question,
                         String answer, String file, int maksimal, int jawaban1, int jawaban2,
                         int jawaban3, int jawaban4, int jawaban5,String jalurnya) {
        this.id = id;
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
        this.maksimal = maksimal;
        this.jawaban1 = jawaban1;
        this.jawaban2 = jawaban2;
        this.jawaban3 = jawaban3;
        this.jawaban4 = jawaban4;
        this.jawaban5 = jawaban5;
        this.jalurnya = jalurnya;
    }

    public ItemKuisioner(String goal, String deadline, int id_pertanyaan, String question,
                         String answer, String file, int maksimal) {
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
        this.maksimal = maksimal;
    }

    public ItemKuisioner(int id, String goal, String deadline, int id_pertanyaan, String question,
                         String answer, String file, int maksimal) {
        this.id = id;
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
        this.maksimal = maksimal;
    }

    public ItemKuisioner(String goal, String deadline, int id_pertanyaan, String question,
                         String answer, String file, int maksimal, int nilaiSkg) {
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
        this.maksimal = maksimal;
        this.nilaiSkg = nilaiSkg;
    }

    public ItemKuisioner(int id, String goal, String deadline, int id_pertanyaan, String question,
                         String answer, String file, int maksimal, int nilaiSkg) {
        this.id = id;
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
        this.maksimal = maksimal;
        this.nilaiSkg = nilaiSkg;
    }

    public ItemKuisioner(String goal, String deadline, int id_pertanyaan, String question, String answer, String file) {
        this.goal = goal;
        this.deadline = deadline;
        this.id_pertanyaan = id_pertanyaan;
        this.question = question;
        this.answer = answer;
        this.file = file;
    }

    public ItemKuisioner(int id, String goal, String deadline, int id_pertanyaan, String question, String answer, String file) {
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

    public int getMaksimal() {
        return maksimal;
    }

    public void setMaksimal(int maksimal) {
        this.maksimal = maksimal;
    }

    public int getNilaiSkg() {
        return nilaiSkg;
    }

    public void setNilaiSkg(int nilaiSkg) {
        this.nilaiSkg = nilaiSkg;
    }

    public int getJawaban1() {
        return jawaban1;
    }

    public void setJawaban1(int jawaban1) {
        this.jawaban1 = jawaban1;
    }

    public int getJawaban2() {
        return jawaban2;
    }

    public void setJawaban2(int jawaban2) {
        this.jawaban2 = jawaban2;
    }

    public int getJawaban3() {
        return jawaban3;
    }

    public void setJawaban3(int jawaban3) {
        this.jawaban3 = jawaban3;
    }

    public int getJawaban4() {
        return jawaban4;
    }

    public void setJawaban4(int jawaban4) {
        this.jawaban4 = jawaban4;
    }

    public int getJawaban5() {
        return jawaban5;
    }

    public void setJawaban5(int jawaban5) {
        this.jawaban5 = jawaban5;
    }

    public String getJalurnya() {
        return jalurnya;
    }

    public void setJalurnya(String jalurnya) {
        this.jalurnya = jalurnya;
    }
}