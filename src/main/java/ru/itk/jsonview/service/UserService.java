package ru.itk.jsonview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.jsonview.dto.UserDto;
import ru.itk.jsonview.exception.UserErrorCodeEnum;
import ru.itk.jsonview.exception.UserException;
import ru.itk.jsonview.mapper.UserMapper;
import ru.itk.jsonview.model.User;
import ru.itk.jsonview.repository.UserRepository;
import ru.itk.jsonview.request.CreateUserRequest;
import ru.itk.jsonview.request.UpdateUserRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public UserDto createUser(CreateUserRequest request) {

        User user = userMapper.mapToUser(request);
        user = userRepository.saveAndFlush(user);

        return userMapper.mapToDto(user);

    }

    @Transactional(rollbackFor = Exception.class)
    public UserDto updateUser(UUID id, UpdateUserRequest request) throws UserException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserErrorCodeEnum.NOT_FOUND_USER_BY_ID));

        userMapper.updateEntity(user, request);
        user = userRepository.saveAndFlush(user);

        return userMapper.mapToDto(user);

    }


    public UserDto getUser(UUID id) throws UserException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserErrorCodeEnum.NOT_FOUND_USER_BY_ID));

        return userMapper.mapToDto(user);

    }


    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
