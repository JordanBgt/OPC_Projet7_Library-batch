package com.openclassrooms.librarybatch.service;

import com.openclassrooms.librarybatch.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Service to manage emails
 *
 * @see JavaMailSender
 */
@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * Method to send an email to users who have not returned loans by the end date
     *
     * @param loan ended loan
     * @throws MessagingException exception thrown by java mail
     */
    public void sendReminderMail(Loan loan) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String htmlMessage = "<h5>Bonjour " + loan.getUser().getUsername() + ",</h5> <p>Nous avons le regret de vous informer que votre " +
                "prêt pour le document " + loan.getExemplar().getDocumentTitle() + " (" +
                loan.getExemplar().getReference() + ") emprunté dans une de nos bibliothèque (" +
                loan.getExemplar().getLibrary().getName() + ") est arrivé à échéance depuis le " +
                loan.getEndDate() + ".</p> <p>Nous vous prions de bien vouloir rendre ce document dans les plus brefs délais " +
                "afin de ne pas avoir à vous infliger des pénalités de retard ainsi qu'une interdiction " +
                "temporaire d'emprunt de document dans toutes nos bibliothèques. </p> <p>Nous vous remercions et vous " +
                "souhaitons une agrèable journée.</p> <br /><h5>Les bibliothèques de Lyon</h5>";
        message.setContent(htmlMessage, "text/html");
        helper.setTo(loan.getUser().getEmail());
        helper.setSubject("Les bibliothèques de Lyon : prêt échu");
        emailSender.send(message);
    }

}
