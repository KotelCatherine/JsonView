package ru.itk.jsonview.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception exception) {

        log.error("handleException -> ", exception);
        ErrorResponse errorResponse = new ErrorResponse(
                UserErrorCodeEnum.INTERNAL_SERVER_ERROR.getDescription(),
                UserErrorCodeEnum.INTERNAL_SERVER_ERROR.getErrorCode(),
                UserErrorCodeEnum.INTERNAL_SERVER_ERROR.getStatus());

        return ResponseEntity.status(errorResponse.status()).body(errorResponse);

    }

    @ExceptionHandler(UserException.class)
    protected ResponseEntity<ErrorResponse> handleCommunicationException(UserException exception) {

        log.error("handleCommunicationException -> code={}, ", exception.getErrorCode(), exception);
        ErrorResponse errorResponse = new ErrorResponse(exception.getDescription(), exception.getErrorCode(), exception.getStatus());

        return ResponseEntity.status(errorResponse.status()).body(errorResponse);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        log.error("handleMethodArgumentNotValidException -> ", exception);
        ErrorResponse errorResponse = new ErrorResponse()
                .description(UserErrorCodeEnum.WRONG_OBJECT_PARAMS.getDescription())
                .errorCode(UserErrorCodeEnum.WRONG_OBJECT_PARAMS.getErrorCode())
                .status(UserErrorCodeEnum.WRONG_OBJECT_PARAMS.getStatus());

        return ResponseEntity.status(errorResponse.status()).body(errorResponse);

    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception) {

        log.error("handleValidationException -> ", exception);
        ErrorResponse errorResponse = new ErrorResponse()
                .description(UserErrorCodeEnum.WRONG_OBJECT_PARAMS.getDescription())
                .errorCode(UserErrorCodeEnum.WRONG_OBJECT_PARAMS.getErrorCode())
                .status(UserErrorCodeEnum.WRONG_OBJECT_PARAMS.getStatus());

        return ResponseEntity
                .status(errorResponse.status())
                .body(errorResponse);

    }

}