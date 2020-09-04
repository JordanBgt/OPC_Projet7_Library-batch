package com.openclassrooms.librarybatch.proxy;

import com.openclassrooms.librarybatch.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api/loans", name = "loan-api")
public interface LoanProxy {

    @GetMapping("/ended")
    List<Loan> getAllEndedLoans(); // TODO : ajouter token
}
