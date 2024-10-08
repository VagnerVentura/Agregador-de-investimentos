package com.Vagner.Agregador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
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
import com.Vagner.Agregador.dto.UpdateUserDTO;
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

	@Captor
	private ArgumentCaptor<UUID> uuidArgumentCaptor;
	
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
	
	@Nested
	class getUserById{
		
		@Test
		@DisplayName("Should get user by id with success when optional is present")
		void ShouldGetUserByIdWhitSuccessWhenOptionalIsPresent() {
			
			//Arrange
			
			var user = new User(
					UUID.randomUUID(),
					"username",
					"email@email.com",
					"password",
					Instant.now(),
					 null
				);
			
			doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
						
			//Act
			var output = userService.getUserById(user.getUserId().toString());
			
			//Assert
			assertTrue(output.isPresent());
			assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
			
		}
		
		@Test
		@DisplayName("Should get user by id with success when optional is empty")
		void ShouldGetUserByIdWhitSuccessWhenOptionalIsEmpty() {
			
			//Arrange
			

			var userId = UUID.randomUUID();
			
			doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());
			
			//Act
			var output = userService.getUserById(userId.toString());
			
			//Assert
			assertTrue(output.isEmpty());
			assertEquals(userId, uuidArgumentCaptor.getValue());
			
		}
		
	}
	
	@Nested
	class listUsers {
		
		@Test
		@DisplayName("Should return all users with success")
		void shouldReturnAllUsersWithSuccess(){
			
			//Arrange
			
			var user = new User(
					UUID.randomUUID(),
					"username",
					"email@email.com",
					"password",
					Instant.now(),
					 null
				);
			
			var userList = List.of(user);	
			
			doReturn(userList)
			.when(userRepository)
			.findAll();

			//Act
			
			var output = userService.listUsers(); 
			
			//Assert
			
			assertNotNull(output);
			assertEquals(userList.size(), output.size());
			
		}
		
	}
	
	@Nested
	class deleteById{
		
		@Test
		@DisplayName("Should delete user with success When User Exists")
		void shouldDeleteUserWithSuccessWhenUserExists() {
			
			doReturn(true)
			.when(userRepository)
			.existsById(uuidArgumentCaptor.capture());
			
			doNothing()
			.when(userRepository)
			.deleteById(uuidArgumentCaptor.capture());
			var userId = UUID.randomUUID();
			
			//Act
			 userService.deleteById(userId.toString());
			
//			doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
			 
			 //Assert
			 var idList = uuidArgumentCaptor.getAllValues();
			 assertEquals(userId, idList.get(0));
			 assertEquals(userId, idList.get(1));
			 
			 verify(userRepository, times(1)).existsById(idList.get(0));
			 verify(userRepository, times(1)).existsById(idList.get(0));
			 
			 
			
		}
		
		@Test
		@DisplayName("Should not delete user when User NOT Exists")
		void shouldNotDeleteUserWhenUserNotExists() {
			
			//Arrange
			doReturn(false)
			.when(userRepository)
			.existsById(uuidArgumentCaptor.capture());
			
			var userId = UUID.randomUUID();
			
			//Act
			userService.deleteById(userId.toString());
			
//			doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
			
			//Assert
			
			assertEquals(userId, uuidArgumentCaptor.getValue());
			
			verify(userRepository, times(1)).existsById(
					uuidArgumentCaptor.getValue());
			
			verify(userRepository, times(0)).deleteById(any());
			
			
			
		}
		
	}
	
	@Nested
	class updateUserById {
		
		@Test
		@DisplayName("Should update user by id when user exists and username and password is filled")
		void shouldUpdateUserByIdWhenUserExistsAndPasswordIsFilled() {
			
			var updateUserDto = new UpdateUserDTO("newusername", "newPassowrd");
			
			//Arrange
			var user = new User(
					UUID.randomUUID(),
					"username",
					"email@email.com",
					"password",
					Instant.now(),
					null
					);
					
			doReturn(Optional.of(user))
			.when(userRepository)
			.findById(uuidArgumentCaptor.capture());
			
			doReturn(user)
			.when(userRepository)
			.save(userArgumentCaptor.capture());
			
			//Act
			
			userService.updateUserById(user.getUserId().toString(), updateUserDto);
		
			//Assert
			
			assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
			
			var usercaptured = userArgumentCaptor.getValue();
			
			assertEquals(updateUserDto.username(), usercaptured.getUsername());
			assertEquals(updateUserDto.password(), usercaptured.getPassword());
						
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
