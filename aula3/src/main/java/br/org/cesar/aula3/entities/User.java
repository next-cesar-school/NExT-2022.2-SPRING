package br.org.cesar.aula3.entities;

public class User {

    private long id;
    private String name;
    private String cpf;
    private String password;

    public User(long id, String name, String cpf, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
