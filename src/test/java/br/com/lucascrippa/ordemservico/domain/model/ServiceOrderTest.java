package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.BudgetStatus;
import br.com.lucascrippa.ordemservico.domain.enums.ImageType;
import br.com.lucascrippa.ordemservico.domain.enums.OrderStatus;
import br.com.lucascrippa.ordemservico.domain.enums.Priority;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ServiceOrderTest {

    private ServiceOrder createValidServiceOrder(){
        return new ServiceOrder("Update Windows",
                1L,
                "Outdated windows",
                Priority.LOW);
    }

    private ServiceOrderImage createInitialImage() {
        return new ServiceOrderImage(
                "image-url",
                ImageType.INITIAL,
                1L
        );
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

    // ================= CANCELLATION TESTS =================


    @Test
    void shouldRequestCancellationSuccessfully(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.requestCancellation("Customer requested cancellation", 1L);
        assertEquals(OrderStatus.AWAITING_CANCELLATION, serviceOrder.getStatus());
        assertEquals("Customer requested cancellation", serviceOrder.getReasonCancellation());
        assertEquals(1L, serviceOrder.getUserRequestedCancellationId());
    }

    @Test
    void shouldThrowExceptionWhenCancellingCompletedOrder(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.completeService("Update");
        assertThrows(IllegalStateException.class, ()-> serviceOrder.requestCancellation("Nothing", 1L));
    }

    @Test
    void shouldThrowExceptionWhenCancellingCancelledOrder(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.requestCancellation("Nothing", 1L);
        serviceOrder.approveCancellation(1L);
        assertThrows(IllegalStateException.class, ()-> serviceOrder.requestCancellation("Nothing", 1L));
    }

    @Test
    void shouldThrowExceptionWhenCancellationReasonIsNull(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.requestCancellation(null, 1L));

    }

    @Test
    void shouldThrowExceptionWhenCancellationReasonIsEmpty(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.requestCancellation("", 1L));

    }

    // ================= APPROVE CANCELLATION TESTS =================

    @Test
    void shouldApproveCancellationSuccessfully(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.requestCancellation("Nothing", 1L);
        serviceOrder.approveCancellation(1L);
        assertEquals(OrderStatus.CANCELLED,serviceOrder.getStatus() );
        assertEquals(1L, serviceOrder.getUserApprovedCancellationId());
    }

    @Test
    void shouldThrowExceptionWhenApprovingCancellationWithoutRequest(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertThrows(IllegalStateException.class, ()-> serviceOrder.approveCancellation(1L));
    }

    @Test
    void shouldThrowExceptionWhenApprovedCancellationUserIdIsNull(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.requestCancellation("Nothing", 1L);
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.approveCancellation(null));
    }


    // ================= BUDGET TESTS =================


    @Test
    void shouldGenerateBudgetSuccessfully(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.generateBudget("Update", new BigDecimal("100.00"));
        assertNotNull(serviceOrder.getBudget());
        assertEquals(BudgetStatus.PENDING, serviceOrder.getBudget().getStatus());
    }

    @Test
    void shouldThrowExceptionWhenGeneratingBudgetTwice(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.generateBudget("Update", new BigDecimal("100.00"));
        assertThrows(IllegalStateException.class,()->serviceOrder.generateBudget("Update", new BigDecimal("100.00")) );
    }

    @Test
    void shouldThrowExceptionWhenGeneratingBudgetInInvalidStatus(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        assertThrows(IllegalStateException.class, ()-> serviceOrder.generateBudget("Update", new BigDecimal("100.00")));
    }

    @Test
    void shouldThrowExceptionWhenGeneratingBudgetWithInvalidDescription(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.generateBudget("", new BigDecimal("100.00")));
    }

    @Test
    void shouldThrowExceptionWhenGeneratingBudgetWithNullDescription(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.generateBudget(null, new BigDecimal("100.00")));
    }

    @Test
    void shouldThrowExceptionWhenGeneratingBudgetWithNullEstimatedValue(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.generateBudget("Update", null));
    }

    @Test
    void shouldThrowExceptionWhenGeneratingBudgetWithZeroEstimatedValue(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.generateBudget("Update", new BigDecimal("0")));
    }

    @Test
    void shouldThrowExceptionWhenGeneratingBudgetWithNegativeEstimatedValue(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        serviceOrder.assignTechnician(1L);
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.generateBudget("Update", new BigDecimal("-100.00")));
    }


    // ================= IMAGE TESTS =================


    @Test
    void shouldAddImageSuccessfully() {
        ServiceOrder serviceOrder = createValidServiceOrder();
        ServiceOrderImage image = createInitialImage();

        serviceOrder.addImage(image);

        assertEquals(1, serviceOrder.getImages().size());
    }

    @Test
    void shouldThrowExceptionWhenImageIsNull(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        assertThrows(IllegalArgumentException.class, ()-> serviceOrder.addImage(null));

    }


    @Test
    void shouldThrowExceptionWhenAddingImageToCompletedOrder(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        ServiceOrderImage image = createInitialImage();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.completeService("Update");
        assertThrows(IllegalStateException.class, ()-> serviceOrder.addImage(image));
    }


    @Test
    void shouldThrowExceptionWhenAddingImageToCancelledOrder(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        ServiceOrderImage image = createInitialImage();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.requestCancellation("Nothing", 1L);
        serviceOrder.approveCancellation(1L);
        assertThrows(IllegalStateException.class, ()-> serviceOrder.addImage(image));
    }

    @Test
    void shouldThrowExceptionWhenAddingDuplicatedInitialImage(){
        ServiceOrder serviceOrder = createValidServiceOrder();

        ServiceOrderImage image1 = createInitialImage();
        ServiceOrderImage image2 = createInitialImage();

        serviceOrder.addImage(image1);

        assertThrows(IllegalStateException.class, () -> serviceOrder.addImage(image2));
    }


    // ================= REPLACE IMAGES TESTS =================


    @Test
    void shouldReplaceImageSuccessfully(){
        ServiceOrder serviceOrder = createValidServiceOrder();

        ServiceOrderImage oldImage = createInitialImage();

        ServiceOrderImage newImage = new ServiceOrderImage(
                "new-image-url",
                ImageType.INITIAL,
                1L
        );

        serviceOrder.addImage(oldImage);
        serviceOrder.replaceImage(newImage);

        assertEquals(1, serviceOrder.getImages().size());
        assertEquals("new-image-url", serviceOrder.getImages().get(0).getImageUrl());
    }



    @Test
    void shouldThrowExceptionWhenReplacingImageWithNullImage(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        ServiceOrderImage image = createInitialImage();

        serviceOrder.addImage(image);

        assertThrows(IllegalArgumentException.class,()->serviceOrder.replaceImage(null));
    }


    @Test
    void shouldThrowExceptionWhenReplacingImageInCompletedOrder(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        ServiceOrderImage image = createInitialImage();
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.completeService("Update");
        assertThrows(IllegalStateException.class, ()-> serviceOrder.replaceImage(image));
    }

    @Test
    void shouldThrowExceptionWhenReplacingImageInCancelledOrder(){
        ServiceOrder serviceOrder = createValidServiceOrder();
        ServiceOrderImage image = createInitialImage();
        serviceOrder.addImage(image);
        serviceOrder.assignTechnician(1L);
        serviceOrder.startService();
        serviceOrder.requestCancellation("nothing", 1L);
        serviceOrder.approveCancellation(1L);
        assertThrows(IllegalStateException.class, ()-> serviceOrder.replaceImage(image));
    }

    @Test
    void shouldThrowExceptionWhenReplacingImageThatDoesNotExist(){
        ServiceOrder serviceOrder = createValidServiceOrder();

        ServiceOrderImage initialImage = createInitialImage();

        ServiceOrderImage finalImage = new ServiceOrderImage(
                "final-image-url",
                ImageType.FINAL,
                1L
        );

        serviceOrder.addImage(initialImage);

        assertThrows(IllegalStateException.class,
                () -> serviceOrder.replaceImage(finalImage));
    }






}