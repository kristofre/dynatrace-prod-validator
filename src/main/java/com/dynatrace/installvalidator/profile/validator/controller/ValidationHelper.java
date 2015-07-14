package com.dynatrace.installvalidator.profile.validator.controller;

/**
 * Created by kristof on 25.06.15.
 */
public class ValidationHelper {
    private static final String EQUALS = "=";
    private static final String NOTEQUALS = "!=";
    private static final String GREATER = "gt";
    private static final String LESSER = "lt";
    private static final String GREATEROREQUAL = "egt";
    private static final String LESSEROREQUAL = "elt";

    private static final String BOOLEAN = "boolean";
    private static final String INTEGER = "integer";
    private static final String STRING = "string";
    private static final String DOUBLE = "double";

    public boolean validate(String value, String type, String operator, String targetValue)
    {
        EnumDataType enumType = getDataType(type);
        EnumOperator enumOperator = getOperator(operator);
        switch (enumType)
        {
            case BOOLEAN: return compareBoolean(value, targetValue);
            case INTEGER: return compareInteger(value, enumOperator, targetValue);
            case STRING: return compareString(value, enumOperator, targetValue);
            case DOUBLE: return compareDouble(value, enumOperator, targetValue);
        }

        return false;
    }

    private boolean compareBoolean(String value, String targetValue) throws IllegalArgumentException
    {
        if (!value.equals("true") && !value.equals("false")) throw new IllegalArgumentException(value + " IS NOT OF TYPE BOOLEAN");
        if (!targetValue.equals("true") && !targetValue.equals("false")) throw new IllegalArgumentException(targetValue + " IS NOT OF TYPE BOOLEAN");

        Boolean bValue = Boolean.parseBoolean(value);
        Boolean bTargetValue = Boolean.parseBoolean(targetValue);

        if(bValue.equals(bTargetValue)) return true;

        return false;
    }

    private boolean compareInteger(String value, EnumOperator operator, String targetValue) throws NumberFormatException
    {
        try {
            Integer iValue = Integer.parseInt(value);
            Integer iTargetValue = Integer.parseInt(targetValue);

            switch (operator)
            {
                case EQUALS: return iValue==iTargetValue?true:false;
                case NOTEQUALS: return iValue==iTargetValue?false:true;
                case GREATER: return iValue>iTargetValue?true:false;
                case EQUALSGREATER: return iValue>=iTargetValue?true:false;
                case LESSER: return iValue<iTargetValue?true:false;
                case EQUALSLESSER: return iValue<=iTargetValue?true:false;
                default: return false;
            }
        } catch (NumberFormatException ex)
        {
            throw new IllegalArgumentException("ILLEGAL INTEGER FORMAT");
        }
    }

    private boolean compareDouble(String value, EnumOperator operator, String targetValue) throws NumberFormatException, NullPointerException
    {
        try {
            Double dValue = Double.parseDouble(value);
            Double dTargetValue = Double.parseDouble(targetValue);

            switch (operator)
            {
                case EQUALS: return dValue==dTargetValue?true:false;
                case NOTEQUALS: return dValue==dTargetValue?false:true;
                case GREATER: return dValue>dTargetValue?true:false;
                case EQUALSGREATER: return dValue>=dTargetValue?true:false;
                case LESSER: return dValue<dTargetValue?true:false;
                case EQUALSLESSER: return dValue<=dTargetValue?true:false;
                default: return false;
            }
        } catch (NumberFormatException ex)
        {
            throw new IllegalArgumentException("ILLEGAL INTEGER FORMAT");
        }
        catch (NullPointerException ex)
        {
            throw new IllegalArgumentException("VALUE EMPTTY");
        }
    }

    private boolean compareString(String value, EnumOperator operator, String targetValue)
    {
        switch (operator)
        {
            case EQUALS: return value.equals(targetValue);
            case NOTEQUALS: return !value.equals(targetValue);
            default: return false;
        }
    }

    private EnumDataType getDataType(String dataType)
    {
        if(dataType.equals(STRING)) return EnumDataType.STRING;
        if(dataType.equals(DOUBLE)) return EnumDataType.DOUBLE;
        if(dataType.equals(INTEGER)) return EnumDataType.INTEGER;
        if(dataType.equals(BOOLEAN)) return EnumDataType.BOOLEAN;
        return EnumDataType.STRING;
    }

    private EnumOperator getOperator(String operator)
    {
        if(operator.equals(EQUALS)) return EnumOperator.EQUALS;
        if(operator.equals(NOTEQUALS)) return EnumOperator.NOTEQUALS;
        if(operator.equals(GREATER)) return EnumOperator.GREATER;
        if(operator.equals(GREATEROREQUAL)) return EnumOperator.EQUALSGREATER;
        if(operator.equals(LESSER)) return EnumOperator.LESSER;
        if(operator.equals(LESSEROREQUAL)) return EnumOperator.EQUALSLESSER;
        return EnumOperator.EQUALS;
    }

    public String translateOperator(String operator)
    {
        if(operator.equals(EQUALS)) return "=";
        if(operator.equals(NOTEQUALS)) return "!=";
        if(operator.equals(GREATER)) return ">";
        if(operator.equals(GREATEROREQUAL)) return ">=";
        if(operator.equals(LESSER)) return "<";
        if(operator.equals(LESSEROREQUAL)) return "<=";
        return "NAN";
    }
}
