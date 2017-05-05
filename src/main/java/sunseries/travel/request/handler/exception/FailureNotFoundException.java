package sunseries.travel.request.handler.exception;

import sunseries.travel.request.handler.message.ErrorMessage;

public class FailureNotFoundException extends FailureException {

    public FailureNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}
