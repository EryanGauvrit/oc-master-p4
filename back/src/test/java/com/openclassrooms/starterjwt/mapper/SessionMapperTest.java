package com.openclassrooms.starterjwt.mapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.TeacherService;
import com.openclassrooms.starterjwt.services.UserService;

class SessionMapperTest {

    @Mock
    private TeacherService teacherService;

    @Mock
    private UserService userService;

    @InjectMocks
    private final SessionMapper sessionMapper = Mappers.getMapper(SessionMapper.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToEntity() {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(1L);
        sessionDto.setDescription("Test session");
        sessionDto.setTeacher_id(1L);
        sessionDto.setUsers(Arrays.asList(1L, 2L));

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        when(teacherService.findById(1L)).thenReturn(teacher);
        when(userService.findById(1L)).thenReturn(user1);
        when(userService.findById(2L)).thenReturn(user2);

        Session session = sessionMapper.toEntity(sessionDto);

        assertNotNull(session);
        assertEquals("Test session", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(2, session.getUsers().size());
        assertTrue(session.getUsers().contains(user1));
        assertTrue(session.getUsers().contains(user2));
    }

    @Test
    void testToDto() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        Session session = new Session();
        session.setId(1L);
        session.setDescription("Test session");
        session.setTeacher(teacher);
        session.setUsers(Arrays.asList(user1, user2));

        SessionDto sessionDto = sessionMapper.toDto(session);

        assertNotNull(sessionDto);
        assertEquals("Test session", sessionDto.getDescription());
        assertEquals(1L, sessionDto.getTeacher_id());
        assertEquals(2, sessionDto.getUsers().size());
        assertTrue(sessionDto.getUsers().contains(1L));
        assertTrue(sessionDto.getUsers().contains(2L));
    }
    
    
    @Test
    void testToEntityList() {
        SessionDto sessionDto1 = new SessionDto();
        sessionDto1.setId(1L);
        sessionDto1.setDescription("Session 1");
        sessionDto1.setTeacher_id(1L);
        sessionDto1.setUsers(Arrays.asList(1L, 2L));

        SessionDto sessionDto2 = new SessionDto();
        sessionDto2.setId(2L);
        sessionDto2.setDescription("Session 2");
        sessionDto2.setTeacher_id(2L);
        sessionDto2.setUsers(Arrays.asList(3L, 4L));

        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);

        Teacher teacher2 = new Teacher();
        teacher2.setId(2L);

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        User user3 = new User();
        user3.setId(3L);

        User user4 = new User();
        user4.setId(4L);

        when(teacherService.findById(1L)).thenReturn(teacher1);
        when(teacherService.findById(2L)).thenReturn(teacher2);
        when(userService.findById(1L)).thenReturn(user1);
        when(userService.findById(2L)).thenReturn(user2);
        when(userService.findById(3L)).thenReturn(user3);
        when(userService.findById(4L)).thenReturn(user4);

        List<Session> sessions = sessionMapper.toEntity(Arrays.asList(sessionDto1, sessionDto2));

        assertNotNull(sessions);
        assertEquals(2, sessions.size());

        Session session1 = sessions.get(0);
        assertEquals("Session 1", session1.getDescription());
        assertEquals(teacher1, session1.getTeacher());
        assertEquals(2, session1.getUsers().size());
        assertTrue(session1.getUsers().contains(user1));
        assertTrue(session1.getUsers().contains(user2));

        Session session2 = sessions.get(1);
        assertEquals("Session 2", session2.getDescription());
        assertEquals(teacher2, session2.getTeacher());
        assertEquals(2, session2.getUsers().size());
        assertTrue(session2.getUsers().contains(user3));
        assertTrue(session2.getUsers().contains(user4));
    }

    @Test
    void testToDtoList() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        Session session1 = Session.builder()
                .id(1L)
                .description("Session 1")
                .teacher(teacher)
                .users(Arrays.asList(user1, user2))
                .build();

        Session session2 = Session.builder()
                .id(2L)
                .description("Session 2")
                .teacher(teacher)
                .users(Arrays.asList(user1, user2))
                .build();

        List<Session> sessionList = Arrays.asList(session1, session2);

        List<SessionDto> sessionDtoList = sessionMapper.toDto(sessionList);

        assertNotNull(sessionDtoList);
        assertEquals(2, sessionDtoList.size());

        SessionDto dto1 = sessionDtoList.get(0);
        assertEquals("Session 1", dto1.getDescription());
        assertEquals(1L, dto1.getTeacher_id());
        assertEquals(2, dto1.getUsers().size());
        assertTrue(dto1.getUsers().contains(1L));
        assertTrue(dto1.getUsers().contains(2L));

        SessionDto dto2 = sessionDtoList.get(1);
        assertEquals("Session 2", dto2.getDescription());
        assertEquals(1L, dto2.getTeacher_id());
        assertEquals(2, dto2.getUsers().size());
        assertTrue(dto2.getUsers().contains(1L));
        assertTrue(dto2.getUsers().contains(2L));
    }        
}