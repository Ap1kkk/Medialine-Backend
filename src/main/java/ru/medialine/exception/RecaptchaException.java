package ru.medialine.exception;

import lombok.Getter;
import ru.medialine.dto.RecaptchaResponse;

@Getter
public class RecaptchaException extends Exception {
    public RecaptchaException(RecaptchaResponse response) {
        super(buildErrorMessage(response));
    }

    private static String buildErrorMessage(RecaptchaResponse response) {
        StringBuilder outputMessage = new StringBuilder("reCAPTCHA errors occurred:");
        if (response.getErrorCodes() != null) {
            for (String errorCode : response.getErrorCodes()) {
                switch (errorCode) {
                    case "missing-input-secret":
                        outputMessage.append(" The secret parameter is missing; ");
                        break;
                    case "invalid-input-secret":
                        outputMessage.append(" The secret parameter is invalid or malformed; ");
                        break;
                    case "missing-input-response":
                        outputMessage.append(" The response parameter is missing; ");
                        break;
                    case "invalid-input-response":
                        outputMessage.append(" The response parameter is invalid or malformed; ");
                        break;
                    case "bad-request":
                        outputMessage.append(" The request is invalid or malformed; ");
                        break;
                    case "timeout-or-duplicate":
                        outputMessage.append(" The response is no longer valid: either is too old or has been used previously; ");
                        break;
                }
            }
        } else {
            outputMessage.append(" Unable to identify codes");
        }
        return outputMessage.toString();
    }
}
