package ru.itk.jsonview.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Schema(description = "Запрос на создание нового заказа")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @Schema(description = "Идентификатор пользователя чей заказ")
    @JsonProperty("userId")
    private UUID userId;

    @Schema(description = "Идентификатор пользователя чей заказ")
    @JsonProperty("userId")
    private List<CreateOrderItemRequest> orderItems;

    @Schema(description = "Итоговая цена")
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;

}
