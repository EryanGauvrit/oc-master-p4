package com.openclassrooms.starterjwt.models;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TeacherTest {

    @Test
    void testTeacherBuilder() {
        Teacher teacher = Teacher.builder()
                .id(1L)
                .lastName("Doe")
                .firstName("John")
                .build();

        assertNotNull(teacher);
        assertEquals(1L, teacher.getId());
        assertEquals("Doe", teacher.getLastName());
        assertEquals("John", teacher.getFirstName());
    }

    @Test
    void testTeacherSettersAndGetters() {
        Teacher teacher = new Teacher();

        teacher.setId(1L);
        teacher.setLastName("Doe");
        teacher.setFirstName("John");

        assertEquals(1L, teacher.getId());
        assertEquals("Doe", teacher.getLastName());
        assertEquals("John", teacher.getFirstName());
    }

    @Test
    void testEqualsAndHashCode() {
        Teacher teacher1 = Teacher.builder()
                .id(1L)
                .lastName("Doe")
                .firstName("John")
                .build();

        Teacher teacher2 = Teacher.builder()
                .id(1L)
                .lastName("Doe")
                .firstName("John")
                .build();

        Teacher teacher3 = Teacher.builder()
                .id(2L)
                .lastName("Smith")
                .firstName("Jane")
                .build();

        assertEquals(teacher1, teacher2);
        assertNotEquals(teacher1, teacher3);
        assertEquals(teacher1.hashCode(), teacher2.hashCode());
        assertNotEquals(teacher1.hashCode(), teacher3.hashCode());
    }

    @Test
    void testToString() {
        Teacher teacher = Teacher.builder()
                .id(1L)
                .lastName("Doe")
                .firstName("John")
                .build();

        String teacherString = teacher.toString();

        assertTrue(teacherString.contains("Doe"));
        assertTrue(teacherString.contains("John"));
    }

    @Test
    void testCreatedAtAndUpdatedAt() {
        LocalDateTime now = LocalDateTime.now();
        Teacher teacher = new Teacher();
        teacher.setCreatedAt(now);
        teacher.setUpdatedAt(now);

        assertEquals(now, teacher.getCreatedAt());
        assertEquals(now, teacher.getUpdatedAt());
    }

    @Test
    void testTeacherConstructor() {
        Teacher teacher = new Teacher(
                1L,
                "Doe",
                "John",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        assertNotNull(teacher);
        assertEquals(1L, teacher.getId());
        assertEquals("Doe", teacher.getLastName());
        assertEquals("John", teacher.getFirstName());
    }
}