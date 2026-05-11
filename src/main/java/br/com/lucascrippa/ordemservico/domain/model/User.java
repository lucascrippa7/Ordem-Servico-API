package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.UserRole;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private boolean active;

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("The name can't be empty or contain numbers.");
        }
        return name.trim();
    }

    private String validateEmail(String email) {
        if (email == null || !email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email.");
        }
        return email.trim();
    }

    private String validatePassword(String password) {
        if (password == null || password.trim().isEmpty() || !password.matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException("Please enter a valid password.");
        }
        return password.trim();
    }

    public User(Long id, String name, String email, String password, UserRole role, boolean active) {

        this.id = id;
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.password = validatePassword(password);

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

    public boolean isActive() {
        return active;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public void changeName(String newName) {
        this.name = validateName(newName);
    }

    public void changeEmail(String newEmail) {
        this.email = validateEmail(newEmail);
    }

    public void changePassword(String newPassword) {
        this.password = validatePassword(newPassword);
    }

    public boolean matchesPassword(String password) {
        if (password == null) {
            return false;
        }
        return this.password.equals(password.trim());
    }

    public void changeRole(UserRole newRole) {
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