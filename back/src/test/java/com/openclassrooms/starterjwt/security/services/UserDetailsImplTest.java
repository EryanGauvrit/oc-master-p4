package com.openclassrooms.starterjwt.security.services;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserDetailsImplTest {

    @Test
    void testUserDetailsImplBuilderAndGetters() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .admin(true)
                .password("password123")
                .build();

        assertEquals(1L, userDetails.getId());
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("John", userDetails.getFirstName());
        assertEquals("Doe", userDetails.getLastName());
        assertTrue(userDetails.getAdmin());
        assertEquals("password123", userDetails.getPassword());
    }

    @Test
    void testGetAuthorities() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder().build();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());
    }

    @Test
    void testAccountNonExpired() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder().build();
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    void testAccountNonLocked() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder().build();
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    void testCredentialsNonExpired() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder().build();
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder().build();
        assertTrue(userDetails.isEnabled());
    }

    @Test
    void testEquals_SameObject() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .build();

        assertTrue(userDetails.equals(userDetails));
    }

    @Test
    void testEquals_DifferentObjectSameId() {
        UserDetailsImpl userDetails1 = UserDetailsImpl.builder()
                .id(1L)
                .build();

        UserDetailsImpl userDetails2 = UserDetailsImpl.builder()
                .id(1L)
                .build();

        assertTrue(userDetails1.equals(userDetails2));
    }

    @Test
    void testEquals_DifferentObjectDifferentId() {
        UserDetailsImpl userDetails1 = UserDetailsImpl.builder()
                .id(1L)
                .build();

        UserDetailsImpl userDetails2 = UserDetailsImpl.builder()
                .id(2L)
                .build();

        assertFalse(userDetails1.equals(userDetails2));
    }

    @Test
    void testEquals_NullObject() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .build();

        assertFalse(userDetails.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .build();

        Object otherObject = new Object();
        assertFalse(userDetails.equals(otherObject));
    }
}