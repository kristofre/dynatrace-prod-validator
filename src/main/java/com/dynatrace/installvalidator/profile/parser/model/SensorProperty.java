package com.dynatrace.installvalidator.profile.parser.model;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;

public class SensorProperty {
	
	private Map<String,String> attributes;
    

    @XmlAnyAttribute
    public Map<String,String> getAttributes(){
        if( attributes == null ){
        	attributes = new HashMap<String,String>();
        }
        return attributes;
    }
}
