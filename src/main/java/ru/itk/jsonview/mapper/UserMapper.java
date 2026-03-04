package ru.itk.jsonview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.itk.jsonview.dto.UserDto;
import ru.itk.jsonview.model.User;
import ru.itk.jsonview.request.CreateUserRequest;
import ru.itk.jsonview.request.UpdateUserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "orders", ignore = true)
    User mapToUser(CreateUserRequest request);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "orders", ignore = true)
    UserDto mapToDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    void updateEntity(@MappingTarget User user, UpdateUserRequest request);

}
