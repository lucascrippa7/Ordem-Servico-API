package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.ImageType;
import br.com.lucascrippa.ordemservico.domain.enums.OrderStatus;
import br.com.lucascrippa.ordemservico.domain.enums.Priority;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrder {

    private Long id;
    private String title;
    private Long clientId;
    private Long technicianId;
    private String problemDescription;
    private String solutionDescription;
    private OrderStatus status;
    private Priority priority;
    private LocalDateTime openingDate;
    private LocalDateTime completionDate;
    private String reasonCancellation;
    private Long userRequestedCancellationId;
    private Long userApprovedCancellationId;
    private Budget budget;
    private List<ServiceOrderImage> images;

    public ServiceOrder(
            String title,
            Long clientId,
            String problemDescription,
            Priority priority
    ) {

        this.title = validateText(title);
        this.clientId = validateLong(clientId);
        this.problemDescription = validateText(problemDescription);

        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }

        this.priority = priority;
        this.status = OrderStatus.CREATED;
        this.openingDate = LocalDateTime.now();
        this.images = new ArrayList<>();
    }

    // ================= TECHNICIAN =================

    public void assignTechnician(Long technicianId) {

        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException(
                    "Technician can only be assigned when order is CREATED"
            );
        }

        this.technicianId = validateLong(technicianId);
    }

    // ================= SERVICE FLOW =================

    public void startService() {

        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException(
                    "Service must be CREATED to start"
            );
        }

        if (this.technicianId == null) {
            throw new IllegalStateException(
                    "Technician must be assigned"
            );
        }

        this.status = OrderStatus.IN_PROGRESS;
    }

    public void completeService(String solutionDescription) {

        if (this.status != OrderStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Service must be IN_PROGRESS to complete"
            );
        }

        this.solutionDescription = validateText(solutionDescription);
        this.completionDate = LocalDateTime.now();
        this.status = OrderStatus.COMPLETED;
    }

    // ================= BUDGET =================

    public void generateBudget(
            String description,
            BigDecimal estimatedValue
    ) {

        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException(
                    "Budget can only be generated when order is CREATED"
            );
        }

        if (this.budget != null) {
            throw new IllegalStateException(
                    "Budget already exists"
            );
        }

        this.budget = new Budget(description, estimatedValue);
    }

    // ================= CANCELLATION =================

    public void requestCancellation(String reason, Long userId) {

        if (this.status == OrderStatus.COMPLETED
                || this.status == OrderStatus.CANCELLED) {

            throw new IllegalStateException(
                    "Cannot cancel completed or cancelled order"
            );
        }

        this.reasonCancellation = validateText(reason);
        this.userRequestedCancellationId = validateLong(userId);
        this.status = OrderStatus.AWAITING_CANCELLATION;
    }

    public void approveCancellation(Long userId) {

        if (this.status != OrderStatus.AWAITING_CANCELLATION) {
            throw new IllegalStateException(
                    "Cancellation must be requested first"
            );
        }

        this.userApprovedCancellationId = validateLong(userId);
        this.status = OrderStatus.CANCELLED;
    }

    // ================= IMAGES =================

    public void addImage(ServiceOrderImage image) {

        if (image == null) {
            throw new IllegalArgumentException(
                    "Image cannot be null"
            );
        }

        if (image.getType() == null) {
            throw new IllegalArgumentException(
                    "Image type is required"
            );
        }

        if (this.status == OrderStatus.CANCELLED
                || this.status == OrderStatus.COMPLETED) {

            throw new IllegalStateException(
                    "Cannot add images in this state"
            );
        }

        boolean alreadyHasInitial = this.images.stream()
                .anyMatch(img -> img.getType() == ImageType.INITIAL);

        if (image.getType() == ImageType.INITIAL
                && alreadyHasInitial) {

            throw new IllegalStateException(
                    "Initial image already exists"
            );
        }

        this.images.add(image);
    }

    public void replaceImage(ServiceOrderImage newImage) {

        if (newImage == null || newImage.getType() == null) {
            throw new IllegalArgumentException(
                    "Invalid image"
            );
        }

        if (this.status == OrderStatus.COMPLETED
                || this.status == OrderStatus.CANCELLED) {

            throw new IllegalStateException(
                    "Cannot replace images in this state"
            );
        }

        boolean imageExists = this.images.stream()
                .anyMatch(img -> img.getType() == newImage.getType());

        if (!imageExists) {
            throw new IllegalStateException(
                    "Image type does not exist to replace"
            );
        }

        this.images.removeIf(img ->
                img.getType() == newImage.getType()
        );

        this.images.add(newImage);
    }

    // ================= VALIDATIONS =================

    private String validateText(String text) {

        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Field cannot be null or empty"
            );
        }

        return text.trim();
    }

    private Long validateLong(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid ID"
            );
        }

        return id;
    }

    // ================= GETTERS =================

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public String getReasonCancellation() {
        return reasonCancellation;
    }

    public Long getUserRequestedCancellationId() {
        return userRequestedCancellationId;
    }

    public Long getUserApprovedCancellationId() {
        return userApprovedCancellationId;
    }

    public Budget getBudget() {
        return budget;
    }

    public List<ServiceOrderImage> getImages() {
        return images;
    }

    // ================= PERSISTENCE =================

    protected void setId(Long id) {
        this.id = id;
    }
}