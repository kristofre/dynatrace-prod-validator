package com.dynatrace.installvalidator.profile.validator.model;

/**
 * Created by kristof on 25.06.15.
 */
public class ValidationResult {
    private String validationMessage;
    private boolean isValid;

    public ValidationResult() {
        isValid = true;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString()
    {
        return getValidationMessage();
    }
}
