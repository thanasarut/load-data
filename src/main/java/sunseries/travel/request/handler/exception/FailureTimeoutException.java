package sunseries.travel.request.handler.exception;

import sunseries.travel.request.handler.message.ErrorMessage;

public class FailureTimeoutException extends FailureException {

    public FailureTimeoutException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}
