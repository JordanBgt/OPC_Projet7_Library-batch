package com.openclassrooms.librarybatch.task;

import com.openclassrooms.librarybatch.model.Loan;
import com.openclassrooms.librarybatch.proxy.LoanProxy;
import com.openclassrooms.librarybatch.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendMailTask {

    Logger log = LoggerFactory.getLogger(SendMailTask.class);

    @Autowired
    private LoanProxy loanProxy;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 24 14 ? * * ", zone = "Europe/Paris")
    public void sendMail() {
        log.info("TACHE LANCEE");
        List<Loan> loans = loanProxy.getAllEndedLoans();
        log.info("PRET " + loans.get(0));
        for (Loan loan : loans) {
            emailService.sendReminderMail(loan);
        }
    }
}
