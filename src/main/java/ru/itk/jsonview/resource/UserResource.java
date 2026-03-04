package ru.itk.jsonview.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itk.jsonview.dto.UserDto;
import ru.itk.jsonview.exception.UserException;
import ru.itk.jsonview.request.CreateUserRequest;
import ru.itk.jsonview.request.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

@Tag(name = "User", description = "Пользователь")
public interface UserResource {

    @PostMapping("/createUser")
    @Operation(description = "creatUserUsingPost", summary = "Создание пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    UserDto createUser(@Valid @RequestBody CreateUserRequest request);

    @GetMapping("/user/{id}")
    @Operation(description = "getUserUsingGet", summary = "Получение информации о пользователе")
    @ResponseStatus(HttpStatus.OK)
    UserDto getUser(@PathVariable UUID id) throws UserException;

    @GetMapping("/users")
    @Operation(description = "getAllUsersUsingGet", summary = "Получение списка всех пользователей")
    @ResponseStatus(HttpStatus.OK)
    List<UserDto> getAllUsers();

    @PutMapping("/updateUser/{id}")
    @Operation(description = "updateUserUsingPut", summary = "Обновление информации о пользователе")
    @ResponseStatus(HttpStatus.OK)
    UserDto updateUser(@PathVariable UUID id,@Valid @RequestBody UpdateUserRequest request) throws UserException;

    @DeleteMapping("/deleteUser/{id}")
    @Operation(description = "deletedUserUsingDelete", summary = "Удаление пользователя")
    @ResponseStatus(HttpStatus.OK)
    void deleteUser(@PathVariable UUID id);

}
