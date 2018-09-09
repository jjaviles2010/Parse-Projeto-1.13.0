package com.JLApps.photoshare.util;

import java.util.HashMap;

public class ParseErros {

    private HashMap<Integer, String> erros;

    public ParseErros() {
        this.erros = new HashMap<>();
        erros.put(201,"A Senha nao foi preenchida!");
        erros.put(202,"Usuario ja existe, escolha outro nome de usuario!");
    }

    public String getErro(int error){
        return this.erros.get(error);
    }
}
