package com.openclassrooms.librarybatch.service;

import com.openclassrooms.librarybatch.model.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendReminderMail(Loan loan) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(loan.getUser().getEmail());
        log.info("EMAIL : " + loan.getUser().getEmail());
        message.setSubject("Les bibliothèques de Lyon : prêt échu");
        message.setText("Bonjour " + loan.getUser().getUsername() + ", Nous avons le regret de vous informer que votre " +
                "prêt pour le document " + loan.getExemplar().getDocumentTitle() + " (" +
                loan.getExemplar().getReference() + ") emprunté dans une de nos bibliothèque (" +
                loan.getExemplar().getLibrary().getName() + ") est arrivé à échéance depuis le " +
                loan.getEndDate() + ". Nous vous prions de bien vouloir rendre ce document dans les plus brefs délais " +
                "afin de ne pas avoir à vous infliger des pénalités de retard ainsi qu'une interdiction " +
                "temporaire d'emprunt de document dans toutes nos bibliothèques. Nous vous remercions et vous " +
                "souhaitons une agrèable journée. Les bibliothèques de Lyon");
        emailSender.send(message);
    }

}
