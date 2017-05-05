package sunseries.travel.request.handler.exception;

import sunseries.travel.request.handler.message.ErrorMessage;

public class FailureValidationException extends FailureException {

    public FailureValidationException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}
