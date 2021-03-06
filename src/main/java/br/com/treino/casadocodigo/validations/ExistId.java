package br.com.treino.casadocodigo.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistId {
    String message() default "{br.com.treino.casadocodigo.ExistId}";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};

    String fieldName();
    Class<?> className();
}
