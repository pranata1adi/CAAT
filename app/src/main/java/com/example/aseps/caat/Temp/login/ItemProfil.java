package com.example.aseps.caat.Temp.login;

public class ItemProfil {
    private int id;
    private int id_auditor_lapangan;
    private String name;
    private String foto;
    private String email;
    private String hp;
    private String password;
    private String address;
    private String city;
    private String registered;

    public ItemProfil(int id_auditor_lapangan, String name, String foto, String email, String hp,
                      String password, String address, String city, String registered) {
        this.id_auditor_lapangan = id_auditor_lapangan;
        this.name = name;
        this.foto = foto;
        this.email = email;
        this.hp = hp;
        this.password = password;
        this.address = address;
        this.city = city;
        this.registered = registered;
    }

    public ItemProfil(int id, int id_auditor_lapangan, String name, String foto, String email,
                      String hp, String password, String address, String city, String registered) {
        this.id = id;
        this.id_auditor_lapangan = id_auditor_lapangan;
        this.name = name;
        this.foto = foto;
        this.email = email;
        this.hp = hp;
        this.password = password;
        this.address = address;
        this.city = city;
        this.registered = registered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_auditor_lapangan() {
        return id_auditor_lapangan;
    }

    public void setId_auditor_lapangan(int id_auditor_lapangan) {
        this.id_auditor_lapangan = id_auditor_lapangan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }
}