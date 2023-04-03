package br.com.ellyofreitas.learning.domain.entities;

import br.com.ellyofreitas.learning.domain.errors.ValidationError;

public class User {
    String id;
    String name;
    String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.validate();
    }

    private void validate() throws ValidationError {
        if (this.id == null || this.id.isEmpty()) {
            throw new ValidationError("id is required");
        }
        if (this.name == null || this.name.isEmpty()) {
            throw new ValidationError("name is required");
        }
        if (this.email == null || this.email.isEmpty()) {
            throw new ValidationError("email is required");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
