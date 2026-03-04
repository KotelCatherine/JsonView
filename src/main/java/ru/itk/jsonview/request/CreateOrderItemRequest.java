package ru.itk.jsonview.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Schema(description = "Запрос на создание нового пользователя")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderItemRequest {

    @Schema(description = "Идентификатор продукта")
    @JsonProperty("productId")
    private UUID productId;

    @Schema(description = "Количество продукта")
    @JsonProperty("quantity")
    private Integer quantity;

}
