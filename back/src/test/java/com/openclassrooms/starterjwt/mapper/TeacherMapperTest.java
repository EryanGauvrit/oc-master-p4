package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.models.Teacher;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherMapperTest {

    private final TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);

    @Test
    void testToEntity() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1L);
        teacherDto.setLastName("Doe");
        teacherDto.setFirstName("John");

        Teacher teacher = teacherMapper.toEntity(teacherDto);

        assertNotNull(teacher);
        assertEquals(1L, teacher.getId());
        assertEquals("Doe", teacher.getLastName());
        assertEquals("John", teacher.getFirstName());
    }

    @Test
    void testToDto() {
        Teacher teacher = Teacher.builder()
                .id(1L)
                .lastName("Doe")
                .firstName("John")
                .build();

        TeacherDto teacherDto = teacherMapper.toDto(teacher);

        assertNotNull(teacherDto);
        assertEquals(1L, teacherDto.getId());
        assertEquals("Doe", teacherDto.getLastName());
        assertEquals("John", teacherDto.getFirstName());
    }

    @Test
    void testToEntityList() {
        TeacherDto teacherDto1 = new TeacherDto();
        teacherDto1.setId(1L);
        teacherDto1.setLastName("Doe");
        teacherDto1.setFirstName("John");

        TeacherDto teacherDto2 = new TeacherDto();
        teacherDto2.setId(2L);
        teacherDto2.setLastName("Smith");
        teacherDto2.setFirstName("Jane");

        List<TeacherDto> teacherDtoList = Arrays.asList(teacherDto1, teacherDto2);

        List<Teacher> teacherList = teacherMapper.toEntity(teacherDtoList);

        assertNotNull(teacherList);
        assertEquals(2, teacherList.size());
        assertEquals("Doe", teacherList.get(0).getLastName());
        assertEquals("Smith", teacherList.get(1).getLastName());
    }

    @Test
    void testToDtoList() {
        Teacher teacher1 = Teacher.builder()
                .id(1L)
                .lastName("Doe")
                .firstName("John")
                .build();

        Teacher teacher2 = Teacher.builder()
                .id(2L)
                .lastName("Smith")
                .firstName("Jane")
                .build();

        List<Teacher> teacherList = Arrays.asList(teacher1, teacher2);

        List<TeacherDto> teacherDtoList = teacherMapper.toDto(teacherList);

        assertNotNull(teacherDtoList);
        assertEquals(2, teacherDtoList.size());
        assertEquals("Doe", teacherDtoList.get(0).getLastName());
        assertEquals("Smith", teacherDtoList.get(1).getLastName());
    }
}