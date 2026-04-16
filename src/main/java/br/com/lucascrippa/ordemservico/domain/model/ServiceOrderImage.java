package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.ImageType;

import java.time.LocalDateTime;

public class ServiceOrderImage {

    private Long id;
    private String imageUrl;
    private ImageType type;
    private LocalDateTime uploadedAt;
    private Long uploadedByUserId;

    public ServiceOrderImage(String imageUrl, ImageType type, Long uploadedByUserId) {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("Image URL is required");
        }

        if (type == null) {
            throw new IllegalArgumentException("Image type is required");
        }

        if (uploadedByUserId == null || uploadedByUserId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        this.imageUrl = imageUrl;
        this.type = type;
        this.uploadedByUserId = uploadedByUserId;
        this.uploadedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
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

    protected void setId(Long id) {
        this.id = id;
    }
}