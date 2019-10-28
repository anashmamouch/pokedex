package com.anas.pokedex.model;

public class Pokemon {

    private int number;
    private String name;
    private String url;

    public int getNumber() {

        String[] urlPastes = url.split("/");
        return Integer.parseInt( urlPastes[urlPastes.length - 1]) ;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
