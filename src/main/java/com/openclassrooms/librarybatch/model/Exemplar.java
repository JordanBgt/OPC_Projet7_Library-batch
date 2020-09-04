package com.openclassrooms.librarybatch.model;

public class Exemplar {
    private Long id;
    private String reference;
    private Library library;
    private String documentTitle;

    public Exemplar() {
    }

    public Exemplar(Long id, String reference, Library library, String documentTitle) {
        this.id = id;
        this.reference = reference;
        this.library = library;
        this.documentTitle = documentTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }
}
