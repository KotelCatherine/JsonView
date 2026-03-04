package ru.itk.jsonview.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itk.jsonview.enums.Status;
import ru.itk.jsonview.view.Views;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Schema(description = "Данные о заказах")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @Schema(description = "Идентификатор заказа")
    @JsonView(Views.UserDetails.class)
    private UUID id;

    @Schema(description = "Список заказов пользователя")
    @JsonView(Views.UserDetails.class)
    private List<OrderItemDto> orderItems;

    @Schema(description = "Полная стоимость заказа")
    @JsonView(Views.UserDetails.class)
    private BigDecimal totalAmount;

    @Schema(description = "Статус заказа")
    @JsonView(Views.UserDetails.class)
    private Status status;

}
