package sunseries.travel.request.handler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sunseries.travel.request.handler.exception.FailureException;
import sunseries.travel.request.handler.message.ErrorMessage;
import sunseries.travel.request.handler.exception.FailureNotFoundException;
import sunseries.travel.request.handler.exception.FailureTimeoutException;
import sunseries.travel.request.handler.exception.FailureValidationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String PREFIX_CODE = "00";
    public static final String FAILURE = "FAILURE";
    public static final String TIMEOUT_MESSAGE = "TIMEOUT";
    public static final String INVALID_TOKEN_MESSAGE = "INVALID_TOKEN_OR_EXPIRED";
    public static final String NOT_FOUND_MESSAGE = "NOT_FOUND";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorMessage handleFailureException(FailureException exception) {
		return exception.getErrorMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public @ResponseBody ErrorMessage handleFailureTimeoutException(FailureTimeoutException exception) {
        exception.getErrorMessage().setCode(PREFIX_CODE+"-"+HttpStatus.REQUEST_TIMEOUT.toString());
        return exception.getErrorMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public @ResponseBody ErrorMessage handlerValidationException(FailureValidationException exception) {
        exception.getErrorMessage().setCode(PREFIX_CODE+"-"+HttpStatus.PRECONDITION_FAILED.toString());
        return exception.getErrorMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorMessage handleNotFoundException(FailureNotFoundException exception) {
        return exception.getErrorMessage();
    }

}