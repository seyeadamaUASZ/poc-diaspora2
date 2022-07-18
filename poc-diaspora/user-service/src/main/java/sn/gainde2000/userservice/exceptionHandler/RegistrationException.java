package sn.gainde2000.userservice.exceptionHandler;

public class RegistrationException extends RuntimeException  {
	private  String errorMessage;


    public RegistrationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
