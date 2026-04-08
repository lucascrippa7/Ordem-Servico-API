package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.UserRole;



public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private boolean active;

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";


    private String validateName(String name){
        if(name == null||name.trim().isEmpty()){
            throw new IllegalArgumentException("The name can't be empty.");
        } return name.trim();
    }

    private String validateEmail(String email){
        if(email == null||email.trim().isEmpty()|| !email.contains("@")){
            throw new IllegalArgumentException("Invalid or null email address.");
        }
        return email.trim();
    }

    public User(Long id, String name, String email, String password, UserRole role, boolean active) {
        this.id = id;

        this.name = validateName(name);


        this.email = validateEmail(email);


       if(password == null|| password.trim().isEmpty()||!password.matches(PASSWORD_REGEX)){
           throw new IllegalArgumentException("Please enter a valid password.");
       }
        this.password = password.trim();

        if (role == null) {
            throw new IllegalArgumentException("User role is required.");
        }
        this.role = role;

        this.active = active;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public UserRole getRole() {
        return role;
    }

    public boolean isActive(){
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void changeName(String newName){
        this.name = validateName(newName);
    }

    public void changeEmail(String newEmail){

        this.email = validateEmail(newEmail);
    }

    public void changePassword(String newPassword){

        if(newPassword == null|| newPassword.trim().isEmpty()||!newPassword.matches(PASSWORD_REGEX)){
            throw new IllegalArgumentException("Please enter a valid password.");
        }
        this.password = newPassword.trim();
    }

    public void changeRole(UserRole newRole){
        if (newRole == null) {
            throw new IllegalArgumentException("User role is required.");
        }
        this.role = newRole;
    }

    public void activate() {
        if (this.active) {
            throw new IllegalStateException("User is already active.");
        }
        this.active = true;
    }

    public void deactivate() {
        if (!this.active) {
            throw new IllegalStateException("User is already inactive.");
        }
        this.active = false;
    }


}
