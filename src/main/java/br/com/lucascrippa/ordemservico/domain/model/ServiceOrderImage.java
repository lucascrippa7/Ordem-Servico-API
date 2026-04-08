package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.ImageType;

import java.time.LocalDateTime;

public class ServiceOrderImage {

    private Long id;
    private Long orderId;
    private String imageURL;
    private ImageType type;
    private LocalDateTime uploadedAt;
    private Long uploadedByUserId;


    public void validateType(){
        if(type == null){
            throw new IllegalStateException("Type can't be empty");
        } this.type = type;
    }

    public ServiceOrderImage(Long id, Long orderId, String imageURL,
                             ImageType type, LocalDateTime uploadedAt, Long uploadedByUserId) {
        this.id = id;
        this.orderId = orderId;
        this.imageURL = imageURL;
        this.type = type;
        this.uploadedAt = uploadedAt;
        this.uploadedByUserId = uploadedByUserId;
    }


    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ImageType getType() {
        return type;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public Long getUploadedByUserId() {
        return uploadedByUserId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
