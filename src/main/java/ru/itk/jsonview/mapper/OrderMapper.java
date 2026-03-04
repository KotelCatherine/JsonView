package ru.itk.jsonview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "status", source = "status")
    OrderDto mapToDto(Order order);

}
