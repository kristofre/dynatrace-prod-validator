package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kristof on 14.07.15.
 */
public class UemSensorConfig {
    private double injectionPercentage;
    private String uemMode;
    private boolean visitPercentageBlockServerSide;
    private String domain;
    private String monitorRequestPath;
    private String agentPath;

    private UemJsAgentOption uemJsAgentOption;

    public String getAgentPath() {
        return agentPath;
    }
    @XmlAttribute(name = "agentpath")
    public void setAgentPath(String agentPath) {
        this.agentPath = agentPath;
    }

    public String getDomain() {
        return domain;
    }
    @XmlAttribute(name = "domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public double getInjectionPercentage() {
        return injectionPercentage;
    }
    @XmlAttribute(name = "injectionpercentage")
    public void setInjectionPercentage(double injectionPercentage) {
        this.injectionPercentage = injectionPercentage;
    }

    public String getMonitorRequestPath() {
        return monitorRequestPath;
    }
    @XmlAttribute(name = "monitorrequestpath")
    public void setMonitorRequestPath(String monitorRequestPath) {
        this.monitorRequestPath = monitorRequestPath;
    }

    public String getUemMode() {
        return uemMode;
    }
    @XmlAttribute(name = "uemmode")
    public void setUemMode(String uemMode) {
        this.uemMode = uemMode;
    }

    public boolean isVisitPercentageBlockServerSide() {
        return visitPercentageBlockServerSide;
    }
    @XmlAttribute(name = "visitpercentageblockserverside")
    public void setVisitPercentageBlockServerSide(boolean visitPercentageBlockServerSide) {
        this.visitPercentageBlockServerSide = visitPercentageBlockServerSide;
    }

    public UemJsAgentOption getUemJsAgentOption() {
        return uemJsAgentOption;
    }
    @XmlElement(name = "jsagentoption")
    public void setUemJsAgentOption(UemJsAgentOption uemJsAgentOption) {
        this.uemJsAgentOption = uemJsAgentOption;
    }
}
