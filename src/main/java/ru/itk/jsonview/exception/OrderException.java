package ru.itk.jsonview.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderException extends Exception {

    private final String errorCode;
    private final String description;
    private final HttpStatus status;

    public OrderException(OrderErrorCodeEnum code) {
        super(code.getDescription());
        this.errorCode = code.getErrorCode();
        this.description = code.getDescription();
        this.status = code.getStatus();
    }

}