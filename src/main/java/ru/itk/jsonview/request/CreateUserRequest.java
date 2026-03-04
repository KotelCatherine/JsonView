package ru.itk.jsonview.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Запрос на создание нового пользователя")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @Schema(description = "Имя пользователя")
    @JsonProperty("firstname")
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @JsonProperty("lastname")
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @Schema(description = "E-mail пользователя")
    @JsonProperty("email")
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

}
