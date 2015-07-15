package com.dynatrace.installvalidator.profile.parser.model;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;

public class SensorProperty {
	
	private HashMap<String,String> attributes;
    

    @XmlAnyAttribute
    public HashMap<String,String> getAttributes(){
        if( attributes == null ){
        	attributes = new HashMap<String,String>();
        }
        return attributes;
    }
}