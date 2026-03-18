package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.Priority;
import br.com.lucascrippa.ordemservico.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderService {

    private Long id;
    private Long clientId;
    private Long responsibleTechnicianId;
    private Long createUserId;
    private String descriptionProblem;
    private String descriptionSolution;
    private OrderStatus status;
    private Priority priority;
    private LocalDateTime openingDate;
    private LocalDateTime forecastConclusion;
    private LocalDateTime completionDate;
    private String reasonCancellation;
    private User userRequestedCancellation;
    private User userApprovedCancellation;

}
