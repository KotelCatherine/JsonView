package ru.itk.jsonview.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itk.jsonview.dto.UserDto;
import ru.itk.jsonview.exception.UserException;
import ru.itk.jsonview.request.CreateUserRequest;
import ru.itk.jsonview.request.UpdateUserRequest;
import ru.itk.jsonview.resource.UserResource;
import ru.itk.jsonview.service.UserService;
import ru.itk.jsonview.view.Views;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController implements UserResource {

    private final UserService service;


    @Override
    @PostMapping("/createUser")
    @JsonView(Views.UserSummary.class)
    public UserDto createUser(@Valid @RequestBody CreateUserRequest request) {
        return service.createUser(request);
    }

    @Override
    @GetMapping("/user/{id}")
    @JsonView(Views.UserDetails.class)
    public UserDto getUser(@PathVariable UUID id) throws UserException {
        return service.getUser(id);
    }

    @Override
    @GetMapping("/users")
    @JsonView(Views.UserSummary.class)
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }

    @Override
    @PutMapping("/updateUser/{id}")
    @JsonView(Views.UserSummary.class)
    public UserDto updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest request) throws UserException {
        return service.updateUser(id, request);
    }

    @Override
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
    }

}
