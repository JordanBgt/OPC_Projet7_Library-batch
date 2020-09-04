package com.openclassrooms.librarybatch.task;

import com.openclassrooms.librarybatch.model.Loan;
import com.openclassrooms.librarybatch.proxy.LoanProxy;
import com.openclassrooms.librarybatch.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;

@Component
public class SendMailTask {

    @Autowired
    private LoanProxy loanProxy;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 ? * * ", zone = "Europe/Paris")
    public void sendMail() {
        List<Loan> loans = loanProxy.getAllEndedLoans();
        for (Loan loan : loans) {
            try {
                emailService.sendReminderMail(loan);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
