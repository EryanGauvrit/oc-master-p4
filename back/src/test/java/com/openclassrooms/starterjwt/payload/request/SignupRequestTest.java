package com.openclassrooms.starterjwt.payload.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class SignupRequestTest {

    @Test
    void testGettersAndSetters() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("John");
        signupRequest.setLastName("Doe");
        signupRequest.setPassword("password123");

        assertEquals("test@example.com", signupRequest.getEmail());
        assertEquals("John", signupRequest.getFirstName());
        assertEquals("Doe", signupRequest.getLastName());
        assertEquals("password123", signupRequest.getPassword());
    }

    @Test
    void testToString() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setFirstName("John");
        signupRequest.setLastName("Doe");
        signupRequest.setPassword("password123");

        String expected = "SignupRequest(email=test@example.com, firstName=John, lastName=Doe, password=password123)";
        assertEquals(expected, signupRequest.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        SignupRequest signupRequest1 = new SignupRequest();
        signupRequest1.setEmail("test@example.com");
        signupRequest1.setFirstName("John");
        signupRequest1.setLastName("Doe");
        signupRequest1.setPassword("password123");

        SignupRequest signupRequest2 = new SignupRequest();
        signupRequest2.setEmail("test@example.com");
        signupRequest2.setFirstName("John");
        signupRequest2.setLastName("Doe");
        signupRequest2.setPassword("password123");

        SignupRequest signupRequest3 = new SignupRequest();
        signupRequest3.setEmail("different@example.com");
        signupRequest3.setFirstName("Jane");
        signupRequest3.setLastName("Smith");
        signupRequest3.setPassword("password456");

        assertEquals(signupRequest1, signupRequest2);
        assertEquals(signupRequest1.hashCode(), signupRequest2.hashCode());

        assertNotEquals(signupRequest1, signupRequest3);
        assertNotEquals(signupRequest1.hashCode(), signupRequest3.hashCode());

        assertEquals(signupRequest1, signupRequest1);
        assertNotEquals(signupRequest1, null);
        assertNotEquals(signupRequest1, new Object());
        assertEquals(signupRequest2, signupRequest1);

        SignupRequest signupRequest4 = new SignupRequest();
        signupRequest4.setEmail("test@example.com");
        signupRequest4.setFirstName("John");
        signupRequest4.setLastName("Doe");
        signupRequest4.setPassword("password123");

        assertEquals(signupRequest1, signupRequest2);
        assertEquals(signupRequest2, signupRequest4);
        assertEquals(signupRequest1, signupRequest4);
    }
}