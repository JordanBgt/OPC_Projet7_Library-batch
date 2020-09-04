package com.openclassrooms.librarybatch.model;

import java.time.LocalDate;

public class Loan {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean renewed;
    private User user;
    private Exemplar exemplar;

    public Loan() {
    }

    public Loan(Long id, LocalDate startDate, LocalDate endDate, boolean renewed, User user, Exemplar exemplar) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.renewed = renewed;
        this.user = user;
        this.exemplar = exemplar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isRenewed() {
        return renewed;
    }

    public void setRenewed(boolean renewed) {
        this.renewed = renewed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", renewed=" + renewed +
                ", user=" + user +
                ", exemplar=" + exemplar +
                '}';
    }
}
