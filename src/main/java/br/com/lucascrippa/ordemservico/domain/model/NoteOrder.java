package br.com.lucascrippa.ordemservico.domain.model;

import java.time.LocalDateTime;

public class NoteOrder {

    private Long id;
    private Long orderId;
    private Long authorId;
    private String text;
    private LocalDateTime creationDate;


    public NoteOrder() {
    }

    public NoteOrder(Long id, Long orderId, Long authorId, String text, LocalDateTime creationDate) {
        this.id = id;
        this.orderId = orderId;
        this.authorId = authorId;
        this.text = text;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

