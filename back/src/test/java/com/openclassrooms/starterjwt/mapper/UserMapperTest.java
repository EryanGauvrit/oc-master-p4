package com.openclassrooms.starterjwt.mapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.starterjwt.dto.UserDto;
import com.openclassrooms.starterjwt.models.User;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testToEntity() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("test@example.com");
        userDto.setLastName("Doe");
        userDto.setFirstName("John");
        userDto.setPassword("password123");
        userDto.setAdmin(true);

        User user = userMapper.toEntity(userDto);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertTrue(user.isAdmin());
    }

    @Test
    void testToDto() {
        User user = User.builder()
                .id(1L)
                .email("test@example.com")
                .lastName("Doe")
                .firstName("John")
                .admin(true)
                .password("password123")
                .build();

        UserDto userDto = userMapper.toDto(user);

        assertNotNull(userDto);
        assertEquals(1L, userDto.getId());
        assertEquals("test@example.com", userDto.getEmail());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("John", userDto.getFirstName());
        assertTrue(userDto.isAdmin());
    }

    @Test
    void testToEntityList() {
        UserDto userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setEmail("test1@example.com");
        userDto1.setLastName("Doe");
        userDto1.setFirstName("John");
        userDto1.setPassword("password123");
        userDto1.setAdmin(true);

        UserDto userDto2 = new UserDto();
        userDto2.setId(1L);
        userDto2.setEmail("test2@example.com");
        userDto2.setLastName("Doe");
        userDto2.setFirstName("John");
        userDto2.setPassword("password123");
        userDto2.setAdmin(true);

        List<UserDto> userDtoList = Arrays.asList(userDto1, userDto2);
        List<User> userList = userMapper.toEntity(userDtoList);

        assertNotNull(userList);
        assertEquals(2, userList.size());
        assertEquals("test1@example.com", userList.get(0).getEmail());
        assertEquals("test2@example.com", userList.get(1).getEmail());

    }

    @Test
    void testToDtoList() {
        User user1 = User.builder()
                .id(1L)
                .email("test1@example.com")
                .lastName("Doe")
                .firstName("John")
                .admin(true)
                .password("password123")
                .build();
        User user2 = User.builder()
                .id(1L)
                .email("test2@example.com")
                .lastName("Doe")
                .firstName("John")
                .admin(true)
                .password("password123")
                .build();

        List<User> userList = Arrays.asList(user1, user2);
        List<UserDto> userDtoList = userMapper.toDto(userList);

        assertNotNull(userDtoList);
        assertEquals(2, userDtoList.size());
        assertEquals("test1@example.com", userDtoList.get(0).getEmail());
        assertEquals("test2@example.com", userDtoList.get(1).getEmail());
    }
}