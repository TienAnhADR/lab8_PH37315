package com.example.lab8_ph37315.models;

public class ResponeGHN<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponeGHN(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
