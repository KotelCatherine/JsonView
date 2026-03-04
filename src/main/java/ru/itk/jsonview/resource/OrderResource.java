package ru.itk.jsonview.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.exception.OrderException;
import ru.itk.jsonview.exception.UserException;
import ru.itk.jsonview.request.CreateOrderRequest;

import java.util.UUID;

@Tag(name = "Order", description = "Заказы")
public interface OrderResource {

    @PostMapping("/createOrder")
    @Operation(description = "creatUserUsingPost", summary = "Создание пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    OrderDto createOrder(@RequestBody CreateOrderRequest request) throws UserException;

    @GetMapping("/order/{id}")
    @Operation(description = "getUserUsingGet", summary = "Получение информации о пользователе")
    @ResponseStatus(HttpStatus.OK)
    OrderDto getOrder(@PathVariable UUID id) throws OrderException;

    @GetMapping("/orders")
    @Operation(description = "getAllUsersUsingGet", summary = "Получение списка всех пользователей")
    @ResponseStatus(HttpStatus.OK)
    Page<OrderDto> getAllOrders(@ParameterObject Pageable pageable);

    @DeleteMapping("/deleteUser/{id}")
    @Operation(description = "deletedUserUsingDelete", summary = "Удаление пользователя")
    @ResponseStatus(HttpStatus.OK)
    void deleteOrder(@PathVariable UUID id);

}
