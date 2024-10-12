package com.example.radio_active_mushroom.services;

public interface MailService {
    public abstract void sendMail(String to, String subject, String body);
}
