package br.com.caelum.cursos.infra.spring;

import br.com.caelum.cursos.domain.core.RegraDeNegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    record ErroDeValidacao(String campo, String erro){
        ErroDeValidacao(FieldError field) {
            this(field.getField().replace("AsString", ""), field.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroDeValidacao>> handleValidationError(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(ErroDeValidacao::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<String> handleRegraDeNegocioException(RegraDeNegocioException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.internalServerError().body("Ocorreu um erro no servidor: " +ex.getMessage());
    }

}
