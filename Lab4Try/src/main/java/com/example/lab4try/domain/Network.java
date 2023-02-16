package com.example.lab4try.domain;

public class Network {

    private static Network INSTANCE = null;

    private Network() {
    }

    public static Network getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Network();
        }
        return INSTANCE;
    }
}