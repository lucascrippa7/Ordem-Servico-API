package br.com.lucascrippa.ordemservico.domain.enums;

public enum OrderStatus {

    CREATED,
    IN_PROGRESS,
    AWAITING_CANCELLATION,
    COMPLETED,
    CANCELLED,
    AWAITING_BUDGET_APPROVAL,
    APPROVED,
    REJECTED;

}