package ru.itk.jsonview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCodeEnum {

    INTERNAL_SERVER_ERROR("00", "Ошибка сервиса управления данными", HttpStatus.INTERNAL_SERVER_ERROR),
    WRONG_OBJECT_PARAMS("01", "Неверные параметры объекта", HttpStatus.BAD_REQUEST),
    USER_EMAIL_ALREADY_EXISTS("03", "Пользователь с таким e-mail уже существует", HttpStatus.BAD_REQUEST),
    NOT_FOUND_USER_BY_ID("04", "Пользователь не найден", HttpStatus.NOT_FOUND);

    private final String errorCode;
    private final String description;
    private final HttpStatus status;


}
