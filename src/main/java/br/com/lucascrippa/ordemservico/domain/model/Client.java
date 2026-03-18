package br.com.lucascrippa.ordemservico.domain.model;

public class Client {

    private Long id;
    private String name;
    private String phone;
    private String company;
    private String email;

    public Client(Long id, String name, String phone, String company, String email) {
        this.id = id;
        if(name == null||name.trim().isEmpty()){
            throw new IllegalArgumentException("The name can't be empty.");
        }
        this.name = name.trim();

        if(phone == null||phone.length() != 11|| !phone.matches("\\d{11}")){
            throw  new IllegalArgumentException("Phone must contain 11 digits.");
        }
        this.phone = phone;

        if(company == null|| company.trim().isEmpty()){
            throw new IllegalArgumentException("The company can't be empty.");
        }
        this.company = company.trim();

        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid or null email address.");
        }
        this.email = email.trim();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void changeEmail(String newEmail) {
        if (newEmail == null || newEmail.trim().isEmpty() || !newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid or null email address.");
        }

        this.email = newEmail.trim();
    }

    public void changeName(String newName){
        if(newName == null|| newName.trim().isEmpty()){
            throw new IllegalArgumentException("The name can't be empty.");
        }
        this.name = newName.trim();
    }

    public void changePhone(String newPhone){
        if(newPhone == null||newPhone.length() != 11|| !newPhone.matches("\\d{11}")){
            throw  new IllegalArgumentException("Phone must contain 11 digits.");
        }
        this.phone = newPhone.trim();
    }

    public void changeCompany(String newCompany){
        if(newCompany == null|| newCompany.trim().isEmpty()){
            throw new IllegalArgumentException("The company can't be empty.");
        }
        this.company = newCompany.trim();
    }
}
