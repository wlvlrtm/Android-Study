package com.example.h201914140;

import java.io.Serializable;

public class Note implements Serializable {
    String header;
    String body;

    // Default Constructor
    public Note() {
    }

    // Constructor
    public Note(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
