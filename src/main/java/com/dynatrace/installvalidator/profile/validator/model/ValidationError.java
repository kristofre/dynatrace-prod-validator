package com.dynatrace.installvalidator.profile.validator.model;

/**
 * Created by kristof on 10.07.15.
 */
public class ValidationError {
    private String validationMessage;

    public ValidationError(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    @Override
    public String toString()
    {
        return getValidationMessage();
    }
}
