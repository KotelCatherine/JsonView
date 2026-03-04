package ru.itk.jsonview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderErrorCodeEnum {

    INTERNAL_SERVER_ERROR("00", "Ошибка сервиса управления данными", HttpStatus.INTERNAL_SERVER_ERROR),
    WRONG_OBJECT_PARAMS("01", "Неверные параметры объекта", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ORDER_BY_ID("03", "Заказ не найден", HttpStatus.NOT_FOUND);

    private final String errorCode;
    private final String description;
    private final HttpStatus status;

}
