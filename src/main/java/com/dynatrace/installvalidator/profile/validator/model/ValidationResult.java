package com.dynatrace.installvalidator.profile.validator.model;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 25.06.15.
 */
public class ValidationResult {
    private ArrayList<ValidationError> validationErrors;
    private boolean isValid;

    public ValidationResult() {
        isValid = true;
        this.validationErrors = new ArrayList<ValidationError>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public ArrayList<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(ArrayList<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public void addValidationError(ValidationError error)
    {
        this.validationErrors.add(error);
    }

    @Override
    public String toString()
    {
        String returnVal = "";
        for (Iterator<ValidationError> iterator = validationErrors.iterator(); iterator.hasNext(); ) {
            ValidationError next = iterator.next();
            returnVal += next.getValidationMessage() + "\n";
        }
        return returnVal;
    }

    public ValidationResult merge(ValidationResult toMerge)
    {
        ArrayList<ValidationError> errorsToMerge = toMerge.getValidationErrors();
        if(errorsToMerge!=null)
        {
            for (Iterator<ValidationError> validationErrorIterator = errorsToMerge.iterator(); validationErrorIterator.hasNext(); ) {
                ValidationError next = validationErrorIterator.next();
                this.addValidationError(next);
            }
        }
        return this;
    }
}
