package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by kristof on 14.07.15.
 */
public class UemJsAgentOption {
    private String agentOption;
    private ArrayList<UemModule> modules;

    public String getAgentOption() {
        return agentOption;
    }
    @XmlAttribute(name = "agentoption")
    public void setAgentOption(String agentOption) {
        this.agentOption = agentOption;
    }

    public ArrayList<UemModule> getModules() {
        return modules;
    }
    @XmlElementWrapper(name = "modules")
    @XmlElement(name = "module")
    public void setModules(ArrayList<UemModule> modules) {
        this.modules = modules;
    }
}
