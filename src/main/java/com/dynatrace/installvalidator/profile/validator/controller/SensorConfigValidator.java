package com.dynatrace.installvalidator.profile.validator.controller;

import com.dynatrace.installvalidator.profile.library.model.SensorValidationProperty;
import com.dynatrace.installvalidator.profile.validator.model.ValidationError;
import com.dynatrace.installvalidator.profile.validator.model.ValidationResult;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;

import javax.xml.namespace.QName;
import java.util.*;

/**
 * Created by kristof on 05.05.15.
 */
public class SensorConfigValidator implements IValidator{
    ValidationHelper validationHelper;

    public SensorConfigValidator() {
        this.validationHelper = new ValidationHelper();
    }

    @Deprecated
    public ValidationResult validateSensorConfig(String property, String value, SensorValidationProperty validationRules)
    {
        ValidationResult result = new ValidationResult();
        if (validationRules.getName().equals(property))
        {
            boolean isValid = validationHelper.validate(value, validationRules.getDatatype(), validationRules.getOperator(), validationRules.getValue());

            if(!isValid){
                result.setIsValid(isValid);
                String message = "Sensor Configuration for property [" + property + "] with a value of [" + value +
                    "] is unhealthy. The recommended value: [" + validationHelper.translateOperator(validationRules.getOperator()) +
                    " " + validationRules.getValue() + "]";
                result.addValidationError(new ValidationError(message));
            }
        }
        return result;
    }

    public ValidationResult validateSensorConfig(String property, String value, ArrayList<SensorValidationProperty> validationRules)
    {
        ValidationResult result = new ValidationResult();
        for (Iterator<SensorValidationProperty> iterator = validationRules.iterator(); iterator.hasNext(); ) {
            SensorValidationProperty next = iterator.next();
            if (next.getName().equals(property))
            {
                boolean isValid = validationHelper.validate(value, next.getDatatype(), next.getOperator(), next.getValue());
                result.setIsValid(isValid);
                if(!isValid){
                    String message = "Sensor Configuration for property [" + property + "] with a value of [" + value +
                            "] is unhealthy. The recommended value: [" + validationHelper.translateOperator(next.getOperator()) +
                            " " + next.getValue() + "]";
                    ValidationError error = new ValidationError(message);
                    result.addValidationError(error);
                }
            }
        }
        return result;
    }

    public ValidationResult validateSensorConfig(ArrayList<HashMap<String, String>> properties, ArrayList<SensorValidationProperty> validationProperties)
    {
        ValidationResult result = new ValidationResult();

        for (Iterator<HashMap<String, String>> hashMapIterator = properties.iterator(); hashMapIterator.hasNext(); ) {
            HashMap<String, String> stringStringHashMap = hashMapIterator.next();

            ValidationResult tempRes = validateSensorConfigProperty(stringStringHashMap, validationProperties);
            ArrayList<ValidationError> errors = tempRes.getValidationErrors();
            if(errors!=null)
            {
                result.setIsValid(false);
                for (Iterator<ValidationError> errorIterator = errors.iterator(); errorIterator.hasNext(); ) {
                    ValidationError next = errorIterator.next();
                    result.addValidationError(next);
                }
            }
        }
        return result;
    }

    public ValidationResult validateSensorConfigProperty(HashMap<String, String> properties, ArrayList<SensorValidationProperty> validationProperties)
    {
        ValidationResult result = new ValidationResult();

        for (Iterator<SensorValidationProperty> iterator = validationProperties.iterator(); iterator.hasNext(); ) {
            SensorValidationProperty next = iterator.next();
            String propertyToLookFor = next.getPropertyType();

            if(properties.containsKey(new QName("", propertyToLookFor, "")))
            {
                if(next.getPropertyTypeValidation()!=null)
                {
                    // <property propertytype="aggregate" name="aggregate" datatype="boolean" operator="=" value="true" />
                    String propTypeVal = next.getPropertyTypeValidation();
                    String curPropTypeVal = properties.get(new QName("", propertyToLookFor, ""));
                    // do a type check
                    if(propTypeVal.equals(curPropTypeVal))
                    {
                        ValidationError error = validatePropertySet(next, properties);
                        if (error != null) {
                            result.setIsValid(false);
                            result.addValidationError(error);
                        }
                    }
                }
                else
                {
                    ValidationError error = validatePropertySet(next, properties);
                    if (error != null) {
                        result.setIsValid(false);
                        result.addValidationError(error);
                    }
                }
            }
        }
        return result;
    }

    public ValidationResult validateSensorConfigForParticularProperty(HashMap<String, String> properties, ArrayList<SensorValidationProperty> validationProperties, String partProperty)
    {
        ValidationResult result = new ValidationResult();

        for (Iterator<SensorValidationProperty> iterator = validationProperties.iterator(); iterator.hasNext(); ) {
            SensorValidationProperty next = iterator.next();
            String propertyToLookFor = next.getPropertyType();

            if(properties.containsKey(new QName("", propertyToLookFor, "")))
            {
                if(next.getPropertyTypeValidation()!=null)
                {
                    // <property propertytype="aggregate" name="aggregate" datatype="boolean" operator="=" value="true" />
                    String propTypeVal = next.getPropertyTypeValidation();
                    String curPropTypeVal = properties.get(new QName("", propertyToLookFor, ""));
                    // do a type check
                    if(propTypeVal.equals(curPropTypeVal))
                    {
                        ValidationError error = validatePropertySet(next, properties);
                        if (error != null) {
                            result.setIsValid(false);
                            result.addValidationError(error);
                        }
                    }
                }
                else
                {
                    ValidationError error = validatePropertySet(next, properties);
                    if (error != null) {
                        result.setIsValid(false);
                        result.addValidationError(error);
                    }
                }
            }
        }
        return result;
    }


    public ValidationError validatePropertySet(SensorValidationProperty validationProperty, HashMap<String, String> sensorProperties)
    {
        String valName = validationProperty.getName();
        String valDataType = validationProperty.getDatatype();
        String valOperator = validationProperty.getOperator();
        String valValue = validationProperty.getValue();
        String curValue = sensorProperties.get(new QName("", valName, ""));
        boolean isValid = validationHelper.validate(curValue, valDataType, valOperator, valValue);

        if (!isValid) {
            String message = "Sensor Configuration for property [" + valName + "] with a value of [" + curValue +
                    "] is unhealthy. The recommended value: [" + validationHelper.translateOperator(valOperator) +
                    " " + valValue + "]";
            ValidationError error = new ValidationError(message);
            return error;
        }
        return null;
    }

    /*public ValidationError validateIndividualProperty(String key, String value, HashMap<String, String> sensorProperties, ArrayList<SensorValidationProperty> validationProperties)
    {
        ValidationResult result = new ValidationResult();

        for (Iterator<SensorValidationProperty> iterator = validationProperties.iterator(); iterator.hasNext(); ) {
            SensorValidationProperty next = iterator.next();
            String propertyToLookFor = next.getPropertyType();

            if(sensorProperties.containsKey(new QName("", propertyToLookFor, "")))
            {
                if(next.getPropertyTypeValidation()!=null)
                {
                    // <property propertytype="aggregate" name="aggregate" datatype="boolean" operator="=" value="true" />
                    String propTypeVal = next.getPropertyTypeValidation();
                    String curPropTypeVal = sensorProperties.get(new QName("", propertyToLookFor, ""));
                    // do a type check
                    if(propTypeVal.equals(curPropTypeVal))
                    {
                        String valName = next.getName();
                        String valDataType = next.getDatatype();
                        String valOperator = next.getOperator();
                        String valValue = next.getValue();
                        //ValidationError error = validatePropertySet(next, properties);
                        boolean isValid = validationHelper.validate(value, valDataType, valOperator, valValue);

                        if (!isValid) {
                            String message = "Sensor Configuration for property [" + valName + "] with a value of [" + value +
                                    "] is unhealthy. The recommended value: [" + validationHelper.translateOperator(valOperator) +
                                    " " + valValue + "]";
                            ValidationError error = new ValidationError(message);
                        }
                    }
                }
                else
                {
                    ValidationError error = validatePropertySet(next, properties);
                    if (error != null) {
                        result.setIsValid(false);
                        result.addValidationError(error);
                    }
                }
            }
        }

        return result;
    }*/
}
