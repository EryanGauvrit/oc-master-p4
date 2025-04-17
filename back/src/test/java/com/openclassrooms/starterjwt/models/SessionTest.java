package com.openclassrooms.starterjwt.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void testSessionBuilder() {
        Teacher teacher = Teacher.builder().id(1L).lastName("Doe").firstName("John").build();
        List<User> users = new ArrayList<>();
        Session session = Session.builder()
                .id(1L)
                .name("Session 1")
                .date(new Date())
                .description("Description of session 1")
                .teacher(teacher)
                .users(users)
                .build();

        assertNotNull(session);
        assertEquals(1L, session.getId());
        assertEquals("Session 1", session.getName());
        assertNotNull(session.getDate());
        assertEquals("Description of session 1", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(users, session.getUsers());
    }

    @Test
    void testSessionSettersAndGetters() {
        Teacher teacher = new Teacher();
        List<User> users = new ArrayList<>();
        Session session = new Session();

        session.setId(1L);
        session.setName("Session 1");
        session.setDate(new Date());
        session.setDescription("Description of session 1");
        session.setTeacher(teacher);
        session.setUsers(users);

        assertEquals(1L, session.getId());
        assertEquals("Session 1", session.getName());
        assertNotNull(session.getDate());
        assertEquals("Description of session 1", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(users, session.getUsers());
    }

    @Test
    void testEqualsAndHashCode() {
        Teacher teacher = Teacher.builder().id(1L).lastName("Doe").firstName("John").build();
        Session session1 = Session.builder()
                .id(1L)
                .name("Session 1")
                .date(new Date())
                .description("Description of session 1")
                .teacher(teacher)
                .build();

        Session session2 = Session.builder()
                .id(1L)
                .name("Session 1")
                .date(new Date())
                .description("Description of session 1")
                .teacher(teacher)
                .build();

        Session session3 = Session.builder()
                .id(2L)
                .name("Session 2")
                .date(new Date())
                .description("Description of session 2")
                .teacher(teacher)
                .build();

        assertEquals(session1, session2);
        assertNotEquals(session1, session3);
        assertEquals(session1.hashCode(), session2.hashCode());
        assertNotEquals(session1.hashCode(), session3.hashCode());
    }

    @Test
    void testToString() {
        Teacher teacher = Teacher.builder().id(1L).lastName("Doe").firstName("John").build();
        Session session = Session.builder()
                .id(1L)
                .name("Session 1")
                .date(new Date())
                .description("Description of session 1")
                .teacher(teacher)
                .build();

        String sessionString = session.toString();

        assertTrue(sessionString.contains("Session 1"));
        assertTrue(sessionString.contains("Description of session 1"));
        assertTrue(sessionString.contains("Doe"));
        assertTrue(sessionString.contains("John"));
    }

    @Test
    void testCreatedAtAndUpdatedAt() {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session();
        session.setCreatedAt(now);
        session.setUpdatedAt(now);

        assertEquals(now, session.getCreatedAt());
        assertEquals(now, session.getUpdatedAt());
    }

    @Test
    void testSessionConstructor() {
        Teacher teacher = Teacher.builder().id(1L).lastName("Doe").firstName("John").build();
        List<User> users = new ArrayList<>();
        Session session = new Session(
                1L,
                "Session 1",
                new Date(),
                "Description of session 1",
                teacher,
                users,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        assertNotNull(session);
        assertEquals(1L, session.getId());
        assertEquals("Session 1", session.getName());
        assertNotNull(session.getDate());
        assertEquals("Description of session 1", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(users, session.getUsers());
    }
}