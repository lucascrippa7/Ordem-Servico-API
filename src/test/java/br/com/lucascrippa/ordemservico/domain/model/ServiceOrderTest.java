package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.OrderStatus;
import br.com.lucascrippa.ordemservico.domain.enums.Priority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceOrderTest {

    private ServiceOrder createValidServiceOrder(){
        return new ServiceOrder("Update Windows",
                1L,
                "Outdated windows",
                Priority.LOW);
    }

    @Test
    void shouldThrowExceptionWhenPriorityIsNull(){
        assertThrows(IllegalArgumentException.class, ()-> new ServiceOrder("Update", 1L,
                "Outdated windows", null ));
    }

    @Test
    void shouldCreateServiceOrderWithCreatedStatus(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        assertEquals(OrderStatus.CREATED, serviceOrder.getStatus());
    }

    @Test
    void shouldAssignTechnicianSuccessfully(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        assertEquals(1L, serviceOrder.getTechnicianId());
    }

    @Test
    void shouldThrowExceptionWhenAssigningTechnicianWithNullId(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.assignTechnician(null) );
    }

    @Test
    void shouldThrowExceptionWhenAssigningTechnicianInInvalidStatus(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertThrows(IllegalStateException.class, ()-> serviceOrder.assignTechnician(2L));
    }

    // ================= Start Service =================

    @Test
    void shouldStartServiceSuccessfully(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertEquals(OrderStatus.IN_PROGRESS, serviceOrder.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenStartingServiceWithoutTechnician(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        assertThrows(IllegalStateException.class, ()->serviceOrder.startService());
    }

    @Test
    void shouldThrowExceptionWhenStartingServiceInInvalidStatus(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertThrows(IllegalStateException.class, ()->serviceOrder.startService());
    }

    // ================= Complete Service =================

    @Test
    void shouldCompleteServiceSuccessfully(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.completeService("Update");
        assertEquals(OrderStatus.COMPLETED, serviceOrder.getStatus());
        assertNotNull(serviceOrder.getCompletionDate());
        assertNotNull(serviceOrder.getSolutionDescription());

    }

    @Test
    void shouldThrowExceptionWhenCompletingServiceInInvalidStatus(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);

        assertThrows(IllegalStateException.class, ()-> serviceOrder.completeService("Update"));
    }

    @Test
    void shouldThrowExceptionWhenSolutionDescriptionIsNull(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.completeService(null));
    }

    @Test
    void shouldThrowExceptionWhenSolutionDescriptionIsEmpty(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.completeService(""));
    }

}