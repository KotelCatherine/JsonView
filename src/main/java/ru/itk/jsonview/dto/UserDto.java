package ru.itk.jsonview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itk.jsonview.view.Views;

import java.util.List;
import java.util.UUID;

@Schema(description = "Данные о пользователе")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Schema(description = "Идентификатор пользователя")
    @JsonProperty("id")
    @JsonView(Views.UserSummary.class)
    private UUID id;

    @Schema(description = "Имя пользователя")
    @JsonProperty("firstname")
    @JsonView(Views.UserSummary.class)
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @JsonProperty("lastname")
    @JsonView(Views.UserSummary.class)
    private String lastName;

    @Schema(description = "E-mail пользователя")
    @JsonProperty("email")
    @JsonView(Views.UserDetails.class)
    private String email;

    @Schema(description = "Заказы пользователя")
    @JsonProperty("orders")
    @JsonView(Views.UserDetails.class)
    private List<OrderDto> orders;

}
