package com.dynatrace.installvalidator.profile.validator.controller;

import com.dynatrace.installvalidator.profile.library.model.SensorProperty;
import com.dynatrace.installvalidator.profile.validator.model.ValidationResult;

/**
 * Created by kristof on 05.05.15.
 */
public class SensorConfigValidator implements IValidator{
    ValidationHelper validationHelper;

    public SensorConfigValidator() {
        this.validationHelper = new ValidationHelper();
    }

    public ValidationResult validateSensorConfig(String property, String value, SensorProperty validationRules)
    {
        ValidationResult result = new ValidationResult();
        if (validationRules.getName().equals(property))
        {
            boolean isValid = validationHelper.validate(value, validationRules.getType(), validationRules.getOperator(), validationRules.getValue());
            result.setIsValid(isValid);
            if(!isValid){
                String message = "Sensor Configuration for property [" + property + "] with a value of [" + value +
                    "] is unhealthy. The recommended value: [" + validationHelper.translateOperator(validationRules.getOperator()) +
                    " " + validationRules.getValue() + "]";
                result.setValidationMessage(message);
            }
        }
        return result;
    }
}
