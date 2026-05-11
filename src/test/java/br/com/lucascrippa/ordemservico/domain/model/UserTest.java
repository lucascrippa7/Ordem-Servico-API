package br.com.lucascrippa.ordemservico.domain.model;

import br.com.lucascrippa.ordemservico.domain.enums.UserRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User createValidUser() {
        return new User(
                1L,
                "Lucas",
                "lucas@email.com",
                "Senha@123",
                UserRole.TECHNICIAN,
                true
        );
    }

    // ================= NAME TESTS =================

    @Test
    void shouldChangeNameSuccessfully() {
        User user = createValidUser();

        user.changeName("Felipe");

        assertEquals("Felipe", user.getName());
    }

    @Test
    void shouldThrowExceptionWhenNameContainsNumbers() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changeName("Felipe123"));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changeName(null));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changeName(" "));
    }

    // ================= EMAIL TESTS =================

    @Test
    void shouldChangeEmailSuccessfully() {
        User user = createValidUser();

        user.changeEmail("felipe@email.com");

        assertEquals("felipe@email.com", user.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenEmailHasNoAtSign() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changeEmail("lucasemail.com"));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changeEmail(null));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changeEmail(" "));
    }

    // ================= PASSWORD TESTS =================

    @Test
    void shouldThrowExceptionWhenPasswordHasOnlyNumbers() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changePassword("123"));
    }

    @Test
    void shouldThrowExceptionWhenPasswordHasOnlySpecialCharacters() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changePassword("!@#"));
    }

    @Test
    void shouldThrowExceptionWhenPasswordHasNoUppercase() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changePassword("lucas@123"));
    }

    @Test
    void shouldThrowExceptionWhenPasswordHasNoLowercase() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changePassword("LUCAS123@"));
    }

    @Test
    void shouldThrowExceptionWhenPasswordHasNoNumber() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changePassword("Lucas@"));
    }

    @Test
    void shouldThrowExceptionWhenPasswordHasNoSpecialCharacter() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changePassword("Lucas123"));
    }

    @Test
    void shouldSucceedWhenPasswordIsValid() {
        User user = createValidUser();

        user.changePassword("Lucas@123");

        assertTrue(user.matchesPassword("Lucas@123"));
    }

    @Test
    void shouldReturnFalseWhenPasswordDoesNotMatch() {
        User user = createValidUser();

        user.changePassword("Lucas@123");

        assertFalse(user.matchesPassword("Senha@123"));
    }

    // ================= ROLE TESTS =================

    @Test
    void shouldChangeRoleSuccessfully() {
        User user = createValidUser();

        user.changeRole(UserRole.ATTENDANT);

        assertEquals(UserRole.ATTENDANT, user.getRole());
    }

    @Test
    void shouldThrowExceptionWhenRoleIsNull() {
        User user = createValidUser();

        assertThrows(IllegalArgumentException.class, () -> user.changeRole(null));
    }

    // ================= ACTIVE STATUS TESTS =================

    @Test
    void shouldBeActiveByDefault() {
        User user = createValidUser();

        assertTrue(user.isActive());
    }

    @Test
    void shouldDeactivateUser() {
        User user = createValidUser();

        user.deactivate();

        assertFalse(user.isActive());
    }

    @Test
    void shouldActivateUser() {
        User user = createValidUser();

        user.deactivate();
        user.activate();

        assertTrue(user.isActive());
    }

    @Test
    void shouldThrowExceptionWhenActivatingAlreadyActiveUser() {
        User user = createValidUser();

        assertThrows(IllegalStateException.class, user::activate);
    }

    @Test
    void shouldThrowExceptionWhenDeactivatingAlreadyInactiveUser() {
        User user = createValidUser();

        user.deactivate();

        assertThrows(IllegalStateException.class, user::deactivate);
    }
}