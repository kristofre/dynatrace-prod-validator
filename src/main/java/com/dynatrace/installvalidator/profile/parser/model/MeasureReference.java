package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 14.07.15.
 */
public class MeasureReference {
    private String aggregate, logicalOperator, refMetric, refMetricGroup, refMeasure;

    public String getAggregate() {
        return aggregate;
    }
    @XmlAttribute(name = "aggregate")
    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }
    @XmlAttribute(name = "logicaloperator")
    public void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public String getRefMeasure() {
        return refMeasure;
    }
    @XmlAttribute(name = "refmeasure")
    public void setRefMeasure(String refMeasure) {
        this.refMeasure = refMeasure;
    }

    public String getRefMetric() {
        return refMetric;
    }
    @XmlAttribute(name = "refmetric")
    public void setRefMetric(String refMetric) {
        this.refMetric = refMetric;
    }

    public String getRefMetricGroup() {
        return refMetricGroup;
    }
    @XmlAttribute(name = "refmetricgroup")
    public void setRefMetricGroup(String refMetricGroup) {
        this.refMetricGroup = refMetricGroup;
    }
}
