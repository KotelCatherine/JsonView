package ru.itk.jsonview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.dto.OrderItemDto;
import ru.itk.jsonview.dto.UserDto;
import ru.itk.jsonview.enums.Status;
import ru.itk.jsonview.request.CreateUserRequest;
import ru.itk.jsonview.service.UserService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private UUID testUserId;
    private UserDto testUser;

    @BeforeEach
    void setUp() {

        testUserId = UUID.randomUUID();
        testUser = new UserDto();
        testUser.setId(testUserId);
        testUser.setFirstName("Иван");
        testUser.setLastName("Петров");

    }

    @Test
    void createUser_whenUserSummary_thenReturnUserWithBasicInfo() throws Exception {

        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("Петр");
        request.setLastName("Сидоров");
        request.setEmail("petr@email.com");

        UserDto createdUser = new UserDto();
        createdUser.setId(UUID.randomUUID());
        createdUser.setFirstName("Петр");
        createdUser.setLastName("Сидоров");
        createdUser.setEmail("petr@email.com");

        when(userService.createUser(any(CreateUserRequest.class))).thenReturn(createdUser);

        mockMvc.perform(post("/api/v1/user/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstname").value("Петр"))
                .andExpect(jsonPath("$.lastname").value("Сидоров"))
                .andExpect(jsonPath("$.email").doesNotExist());

    }

    @Test
    void getUserById_whenUserDetails_thenReturnAllFields() throws Exception {

        UserDto detailedUser = new UserDto();
        detailedUser.setId(testUserId);
        detailedUser.setFirstName("Иван");
        detailedUser.setLastName("Петров");
        detailedUser.setEmail("ivan@email.com");

        OrderDto orderDto = new OrderDto();
        orderDto.setId(UUID.randomUUID());
        orderDto.setTotalAmount(BigDecimal.valueOf(5000));
        orderDto.setStatus(Status.NEW);

        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setId(UUID.randomUUID());
        itemDto.setProductName("Ноутбук");
        itemDto.setQuantity(1);
        itemDto.setPrice(BigDecimal.valueOf(5000));

        orderDto.setOrderItems(List.of(itemDto));

        detailedUser.setOrders(List.of(orderDto));

        when(userService.getUser(testUserId)).thenReturn(detailedUser);

        mockMvc.perform(get("/api/v1/user/user/{id}", testUserId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testUserId.toString()))
                .andExpect(jsonPath("$.firstname").value("Иван"))
                .andExpect(jsonPath("$.lastname").value("Петров"))
                .andExpect(jsonPath("$.email").value("ivan@email.com"))
                .andExpect(jsonPath("$.orders").isArray())
                .andExpect(jsonPath("$.orders[0].id").exists())
                .andExpect(jsonPath("$.orders[0].totalAmount").value(5000))
                .andExpect(jsonPath("$.orders[0].status").value("NEW"));

    }

    @Test
    void getAllUsers_whenUserSummary_thenReturnUsersWithBasicInfo() throws Exception {

        List<UserDto> userList = Collections.singletonList(testUser);

        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/v1/user/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())  // проверяем что это массив
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(testUserId.toString()))
                .andExpect(jsonPath("$[0].firstname").value("Иван"))
                .andExpect(jsonPath("$[0].lastname").value("Петров"))
                .andExpect(jsonPath("$[0].email").doesNotExist());

    }

}