package ru.itk.jsonview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itk.jsonview.view.Views;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Данные о всех заказах пользователя")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    @Schema(description = "Идентификатор заказов пользователя")
    @JsonProperty("id")
    @JsonView(Views.UserDetails.class)
    private UUID id;

    @Schema(description = "Наименование продукта")
    @JsonProperty("productName")
    @JsonView(Views.UserDetails.class)
    private String productName;

    @Schema(description = "Количество товара")
    @JsonProperty("quantity")
    @JsonView(Views.UserDetails.class)
    private Integer quantity;

    @Schema(description = "Цена")
    @JsonProperty("price")
    @JsonView(Views.UserDetails.class)
    private BigDecimal price;

}
