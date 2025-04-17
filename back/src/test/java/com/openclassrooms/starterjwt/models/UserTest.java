package com.openclassrooms.starterjwt.models;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testUserBuilder() {
        User user = User.builder()
                .id(1L)
                .email("test@example.com")
                .lastName("Doe")
                .firstName("John")
                .password("password123")
                .admin(true)
                .build();

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("password123", user.getPassword());
        assertTrue(user.isAdmin());
    }

    @Test
    void testUserSettersAndGetters() {
        User user = new User();

        user.setId(1L);
        user.setEmail("test@example.com");
        user.setLastName("Doe");
        user.setFirstName("John");
        user.setPassword("password123");
        user.setAdmin(false);

        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("password123", user.getPassword());
        assertFalse(user.isAdmin());
    }

    @Test
    void testEqualsAndHashCode() {
        User user1 = User.builder()
                .id(1L)
                .email("test@example.com")
                .lastName("Doe")
                .firstName("John")
                .password("password123")
                .admin(false)
                .build();

        User user2 = User.builder()
                .id(1L)
                .email("test@example.com")
                .lastName("Doe")
                .firstName("John")
                .password("password123")
                .admin(false)
                .build();

        User user3 = User.builder()
                .id(2L)
                .email("other@example.com")
                .lastName("Smith")
                .firstName("Jane")
                .password("password456")
                .admin(true)
                .build();

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    void testToString() {
        User user = User.builder()
                .id(1L)
                .email("test@example.com")
                .lastName("Doe")
                .firstName("John")
                .password("password123")
                .admin(false)
                .build();

        String userString = user.toString();

        assertTrue(userString.contains("test@example.com"));
        assertTrue(userString.contains("Doe"));
        assertTrue(userString.contains("John"));
    }

    @Test
    void testCreatedAtAndUpdatedAt() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        // Assert
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
void testUserConstructor() {
    User user = new User(
            "test@example.com",
            "Doe",
            "John",
            "password123",
            true
    );

    assertNotNull(user);
    assertEquals("test@example.com", user.getEmail());
    assertEquals("Doe", user.getLastName());
    assertEquals("John", user.getFirstName());
    assertEquals("password123", user.getPassword());
    assertTrue(user.isAdmin());
}
}