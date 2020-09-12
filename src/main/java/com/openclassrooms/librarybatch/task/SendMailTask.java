package com.openclassrooms.librarybatch.task;

import com.openclassrooms.librarybatch.model.JwtResponse;
import com.openclassrooms.librarybatch.model.Loan;
import com.openclassrooms.librarybatch.model.LoginRequest;
import com.openclassrooms.librarybatch.proxy.AuthProxy;
import com.openclassrooms.librarybatch.proxy.LoanProxy;
import com.openclassrooms.librarybatch.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Scheduled task to send an email to users who have not returned loans by the end date
 *
 * @see EmailService
 * @see LoanProxy
 */
@Component
public class SendMailTask {

    @Autowired
    private LoanProxy loanProxy;

    @Autowired
    private AuthProxy authProxy;

    @Autowired
    private EmailService emailService;

    /**
     * Each day, at 8am, this task get all ended loans and send a reminder email to users
     */
    @Scheduled(cron = "0 13 10 ? * * ", zone = "Europe/Paris")
    public void sendMail() {

        // TODO : améliorer sécurité : clé api par ex
        JwtResponse jwtResponse = authProxy.authenticateUser(new LoginRequest("admin", "admin")).getBody();

        List<Loan> loans = loanProxy.getAllEndedLoans("Bearer " + jwtResponse.getToken());
        for (Loan loan : loans) {
            try {
                emailService.sendReminderMail(loan);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
