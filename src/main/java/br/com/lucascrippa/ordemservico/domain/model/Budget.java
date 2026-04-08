package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.BudgetStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Budget {

    private Long id;
    private String description;
    private BigDecimal estimatedValue;
    private LocalDateTime createdAt;
    private BudgetStatus status;
    private LocalDateTime approvedAt;

    public Budget(String description, BigDecimal estimatedValue) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        if (estimatedValue == null || estimatedValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Estimated value must be greater than zero");
        }

        this.description = description;
        this.estimatedValue = estimatedValue;
        this.createdAt = LocalDateTime.now();
        this.status = BudgetStatus.PENDING;
    }

    public void approve() {
        if (this.status != BudgetStatus.PENDING) {
            throw new IllegalStateException("Only pending budgets can be approved");
        }

        this.status = BudgetStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();
    }

    public void reject() {
        if (this.status != BudgetStatus.PENDING) {
            throw new IllegalStateException("Only pending budgets can be rejected");
        }

        this.status = BudgetStatus.REJECTED;
        this.approvedAt = null;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BudgetStatus getStatus() {
        return status;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}