package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.BudgetStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    private Budget createValidBudget() {
        return new Budget(
                "update Windows",
                new BigDecimal("10.50")
        );
    }

    // ================= CREATION TESTS =================

    @Test
    void shouldCreateBudgetWithPendingStatus() {
        Budget budget = createValidBudget();

        assertEquals(BudgetStatus.PENDING, budget.getStatus());
        assertNotNull(budget.getCreatedAt());
        assertNull(budget.getApprovedAt());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Budget("", new BigDecimal("100.0")));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> new Budget("   ", new BigDecimal("100.0")));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Budget(null, new BigDecimal("100")));
    }

    @Test
    void shouldThrowExceptionWhenEstimatedValueIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Budget("Memory", null));
    }

    @Test
    void shouldThrowExceptionWhenEstimatedValueIsZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new Budget("Memory", new BigDecimal("0")));
    }

    @Test
    void shouldThrowExceptionWhenEstimatedValueIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Budget("Memory", new BigDecimal("-2")));
    }

    // ================= APPROVAL TESTS =================

    @Test
    void shouldApprovePendingBudget() {
        Budget budget = createValidBudget();

        budget.approve();

        assertEquals(BudgetStatus.APPROVED, budget.getStatus());
        assertNotNull(budget.getApprovedAt());
    }

    @Test
    void shouldThrowExceptionWhenApprovingNonPendingBudget() {
        Budget budget = createValidBudget();
        budget.reject();

        assertThrows(IllegalStateException.class, () -> budget.approve());
    }

    // ================= REJECTION TESTS =================

    @Test
    void shouldRejectPendingBudget() {
        Budget budget = createValidBudget();

        budget.reject();

        assertEquals(BudgetStatus.REJECTED, budget.getStatus());
        assertNull(budget.getApprovedAt());
    }

    @Test
    void shouldThrowExceptionWhenRejectingNonPendingBudget() {
        Budget budget = createValidBudget();
        budget.approve();

        assertThrows(IllegalStateException.class, () -> budget.reject());
    }
}