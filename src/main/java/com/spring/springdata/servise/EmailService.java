package com.spring.springdata.servise;

public interface EmailService {
    void send(String to, String title, String body);
}
