package sunseries.travel.request.handler.exception;

import sunseries.travel.request.handler.message.ErrorMessage;

public class FailureException extends RuntimeException {

    private ErrorMessage errorMessage;

    public FailureException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

}
