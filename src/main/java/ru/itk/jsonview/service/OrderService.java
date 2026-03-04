package ru.itk.jsonview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.enums.Status;
import ru.itk.jsonview.exception.OrderErrorCodeEnum;
import ru.itk.jsonview.exception.OrderException;
import ru.itk.jsonview.exception.UserErrorCodeEnum;
import ru.itk.jsonview.exception.UserException;
import ru.itk.jsonview.mapper.OrderMapper;
import ru.itk.jsonview.model.Order;
import ru.itk.jsonview.model.OrderItem;
import ru.itk.jsonview.model.Product;
import ru.itk.jsonview.model.User;
import ru.itk.jsonview.repository.OrderRepository;
import ru.itk.jsonview.repository.ProductRepository;
import ru.itk.jsonview.repository.UserRepository;
import ru.itk.jsonview.request.CreateOrderItemRequest;
import ru.itk.jsonview.request.CreateOrderRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;


    @Transactional(rollbackFor = Exception.class)
    public OrderDto createOrder(CreateOrderRequest request) throws UserException {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserException(UserErrorCodeEnum.NOT_FOUND_USER_BY_ID));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Status.NEW);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CreateOrderItemRequest orderItemRequest: request.getOrderItems()) {

            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()))
            );

            orderItems.add(orderItem);

            totalAmount = totalAmount.add(orderItem.getPrice());

        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);


        order = orderRepository.save(order);

        return orderMapper.mapToDto(order);

    }


    public OrderDto getOrder(UUID id) throws OrderException {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderException(OrderErrorCodeEnum.NOT_FOUND_ORDER_BY_ID));

        return orderMapper.mapToDto(order);

    }


    public Page<OrderDto> getAllOrders(Pageable pageable) {

        if (!pageable.getSort().isSorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        }

        Page<Order> pageOrders = orderRepository.findAll(pageable);

        return pageOrders.
                map(orderMapper::mapToDto);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

}
