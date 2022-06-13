package br.com.etechoracio.avaliacao.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@JsonPropertyOrder({"message", "dateTime", "reasonPhrase", "status", "violations"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateTime;

    private Map<String, String> violations;

    @JsonIgnore
    private HttpStatus httpStatus;

    public String getReasonPhrase() {
        return this.httpStatus.getReasonPhrase();
    }

    public int getStatus() {
        return this.httpStatus.value();
    }

}
