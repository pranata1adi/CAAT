package com.example.aseps.caat.Temp.metode;

public class ItemMetode {
    private int id, id_project, id_field_auditor, no_form, id_form;
    private String stakeholder, Metode;

    public ItemMetode(int id_project, int id_field_auditor, int no_form, int id_form,
                      String stakeholder, String metode) {
        this.id_project = id_project;
        this.id_field_auditor = id_field_auditor;
        this.no_form = no_form;
        this.id_form = id_form;
        this.stakeholder = stakeholder;
        Metode = metode;
    }

    public ItemMetode(int id, int id_project, int id_field_auditor, int no_form, int id_form,
                      String stakeholder, String metode) {
        this.id = id;
        this.id_project = id_project;
        this.id_field_auditor = id_field_auditor;
        this.no_form = no_form;
        this.id_form = id_form;
        this.stakeholder = stakeholder;
        Metode = metode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public int getId_field_auditor() {
        return id_field_auditor;
    }

    public void setId_field_auditor(int id_field_auditor) {
        this.id_field_auditor = id_field_auditor;
    }

    public int getNo_form() {
        return no_form;
    }

    public void setNo_form(int no_form) {
        this.no_form = no_form;
    }

    public int getId_form() {
        return id_form;
    }

    public void setId_form(int id_form) {
        this.id_form = id_form;
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder;
    }

    public String getMetode() {
        return Metode;
    }

    public void setMetode(String metode) {
        Metode = metode;
    }
}
