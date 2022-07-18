package sn.gainde2000.userservice.exceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import sn.gainde2000.userservice.business.Users.UserController;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class RegistrationControllerAdvice {
	@ExceptionHandler(RegistrationException.class)
    ResponseEntity<ErrorDetails> handleRegistrationException(RegistrationException exception, WebRequest request) {

        final ErrorDetails response = new ErrorDetails(new Date(), exception.getMessage(),request.getDescription(false),HttpStatus.BAD_REQUEST);


        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
