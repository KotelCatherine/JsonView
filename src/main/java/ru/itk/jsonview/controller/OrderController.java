package ru.itk.jsonview.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.exception.OrderException;
import ru.itk.jsonview.exception.UserException;
import ru.itk.jsonview.request.CreateOrderRequest;
import ru.itk.jsonview.resource.OrderResource;
import ru.itk.jsonview.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController implements OrderResource {

    private final OrderService service;

    @Override
    @PostMapping("/createOrder")
    public OrderDto createOrder(@RequestBody CreateOrderRequest request) throws UserException {
        return service.createOrder(request);
    }

    @Override
    @GetMapping("/order/{id}")
    public OrderDto getOrder(@PathVariable UUID id) throws OrderException {
        return service.getOrder(id);
    }

    @Override
    @GetMapping("/orders")
    public Page<OrderDto> getAllOrders(@ParameterObject Pageable pageable) {
        return service.getAllOrders(pageable);
    }

    @Override
    @DeleteMapping("/deleteUser/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        service.deleteOrder(id);
    }

}
