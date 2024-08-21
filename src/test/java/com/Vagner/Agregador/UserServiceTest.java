package com.Vagner.Agregador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Vagner.Agregador.dto.CreateUserDTO;
import com.Vagner.Agregador.entity.User;
import com.Vagner.Agregador.repositories.UserRepository;
import com.Vagner.Agregador.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Captor
	private ArgumentCaptor<User> userArgumentCaptor;
	
	@Nested
	class createUser{
		
		
		@Test
		@DisplayName("Should create a user with success")
		void ShouldCreateAUserWithSuccess() {
			
			//Arrange			
			var user = new User(
					UUID.randomUUID(),
					"username",
					"email@email.com",
					"password",
					Instant.now(),
					 null
				);
			
			doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
			var input = new CreateUserDTO(
					"username",
					"email@email.com",
					"123"
				);

			//Act
			var output = userService.createUser(input);
			
			//Assert
			assertNotNull(output);
			
			var userCaptured = userArgumentCaptor.getValue();
			
			assertEquals(input.username(),userCaptured.getUsername());
			assertEquals(input.email(),userCaptured.getEmail());
			assertEquals(input.password(),userCaptured.getPassword());
		}
		
		@Test
		@DisplayName("Should throw exception when error occurs")
		void ShoulThrowExceptionWhenErrorOccurs() {
			//Arrange
			
			var user = new User(
					UUID.randomUUID(),
					"username",
					null,
					"password",
					Instant.now(),
					 null
				);
			
			doThrow(new RuntimeException()).when(userRepository).save(any());
			var input = new CreateUserDTO(
					"username",
					"email@email.com",
					"123"
				);
			
			//Act
			assertThrows(RuntimeException.class, () -> userService.createUser(input));
			
			//Assert
//			assertNotNull(output);			
			
		}
		
	}
	
}
