package com.quatrocentosquatro.storemanagement.controller;

public class LoginController {

    // OBS: so´para teste
    public boolean autenticar(String usuario, String senha) {
        return usuario.equals("admin") && senha.equals("1234");
    }
}