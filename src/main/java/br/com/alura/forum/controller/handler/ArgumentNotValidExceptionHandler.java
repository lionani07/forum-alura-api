package br.com.alura.forum.controller.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ArgumentNotValidExceptionHandler {

    private final InternationalizationMessage internationalizationMessage;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormErrorDto> handle(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FormErrorDto(
                        fieldError.getField(),
                        this.internationalizationMessage.getMessageTranslateFrom(fieldError))
                )
                .collect(Collectors.toList());
    }
}
