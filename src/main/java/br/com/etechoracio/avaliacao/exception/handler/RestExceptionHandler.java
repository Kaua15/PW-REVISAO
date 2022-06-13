package br.com.etechoracio.avaliacao.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.etechoracio.avaliacao.exception.RecursoNaoEncontradoException;
import br.com.etechoracio.avaliacao.exception.RegraNegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ApiErrorResponse handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
       return ApiErrorResponse.builder()
               .dateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegraNegocioException.class)
    public ApiErrorResponse handleRegraNegocioException(RegraNegocioException ex) {
        return ApiErrorResponse.builder()
                .dateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        
        Map<String, String> violations = fieldErrors.stream()
        											.collect(Collectors.toMap(FieldError::getField,
        																	  FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(getResponseToViolations(violations, request));
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleAllException(Exception ex, WebRequest request) {
    	
       return ApiErrorResponse.builder()
               .dateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
    }

    private ApiErrorResponse getResponseToViolations(Map<String, String> violations, WebRequest request) {
        return ApiErrorResponse.builder().message("Check the field(s) error")
                .dateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .violations(violations)
                .build();
    }

}
