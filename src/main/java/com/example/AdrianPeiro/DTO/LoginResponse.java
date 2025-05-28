package com.example.AdrianPeiro.DTO;

import com.example.AdrianPeiro.Modelo.Usuario;

public class LoginResponse {
    private boolean success;
    private String message;
    private Usuario user;

    public LoginResponse(boolean success, String message, Usuario user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    // Getters y setters
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getUser() {
        return user;
    }
    public void setUser(Usuario user) {
        this.user = user;
    }
}
