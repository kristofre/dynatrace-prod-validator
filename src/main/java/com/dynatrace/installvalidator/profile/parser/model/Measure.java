package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by kristof on 26.05.15.
 */
public class Measure implements Comparable<Measure> {
    private Boolean isApplicationAggregated;
    private String errorSeverity;
    private String serviceContext;
    private Boolean userDefined;
    private String id;
    private Boolean calculatePercentiles;
    private Date createdTimeStamp;
    private String errorType;
    private String rate;
    private String description;
    private Boolean isChartable;
    private String metricId;
    private String measureType;
    private Boolean isAggregated;
    private String metricGroupId;
    private int displayAggregations;
    private Boolean calculateBaseline;
    private String displayUnit;
    private String uriMatch;
    private String queryMatch;
    private String sqlMatch;
    private String match;
    private String errorPageMatch;
    private String loggingLevelMatch;
    private String infoMatch;
    private String classNameMatch;
    private String methodNameMatch;
    private String endPointMatch;
    private String operationNameMatch;
    private MeasureThreshold measureThreshold;

    public Boolean getIsApplicationAggregated() {
        return isApplicationAggregated;
    }
    @XmlAttribute(name = "isapplicationaggregated")
    public void setIsApplicationAggregated(Boolean isApplicationAggregated) {
        this.isApplicationAggregated = isApplicationAggregated;
    }

    public String getErrorSeverity() {
        return errorSeverity;
    }
    @XmlAttribute(name = "errorseverity")
    public void setErrorSeverity(String errorSeverity) {
        this.errorSeverity = errorSeverity;
    }

    public String getServiceContext() {
        return serviceContext;
    }
    @XmlAttribute(name = "servicecontext")
    public void setServiceContext(String serviceContext) {
        this.serviceContext = serviceContext;
    }

    public Boolean getUserDefined() {
        return userDefined;
    }
    @XmlAttribute(name = "userdefined")
    public void setUserDefined(Boolean userDefined) {
        this.userDefined = userDefined;
    }

    public String getId() {
        return id;
    }
    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCalculatePercentiles() {
        return calculatePercentiles;
    }
    @XmlAttribute(name = "calculatepercentiles")
    public void setCalculatePercentiles(Boolean calculatePercentiles) {
        this.calculatePercentiles = calculatePercentiles;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }
    @XmlAttribute(name = "createdtimestamp")
    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getErrorType() {
        return errorType;
    }
    @XmlAttribute(name = "errortype")
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getRate() {
        return rate;
    }
    @XmlAttribute(name = "rate")
    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }
    @XmlAttribute(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsChartable() {
        return isChartable;
    }
    @XmlAttribute(name = "ischartable")
    public void setIsChartable(Boolean isChartable) {
        this.isChartable = isChartable;
    }

    public String getMetricId() {
        return metricId;
    }
    @XmlAttribute(name = "metricid")
    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    public String getMeasureType() {
        return measureType;
    }
    @XmlAttribute(name = "measuretype")
    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public Boolean getIsAggregated() {
        return isAggregated;
    }
    @XmlAttribute(name = "isaggregated")
    public void setIsAggregated(Boolean isAggregated) {
        this.isAggregated = isAggregated;
    }

    public String getMetricGroupId() {
        return metricGroupId;
    }
    @XmlAttribute(name = "metricgroupid")
    public void setMetricGroupId(String metricGroupId) {
        this.metricGroupId = metricGroupId;
    }

    public int getDisplayAggregations() {
        return displayAggregations;
    }
    @XmlAttribute(name = "displayaggregations")
    public void setDisplayAggregations(int displayAggregations) {
        this.displayAggregations = displayAggregations;
    }

    public Boolean getCalculateBaseline() {
        return calculateBaseline;
    }
    @XmlAttribute(name = "calculatebaseline")
    public void setCalculateBaseline(Boolean calculateBaseline) {
        this.calculateBaseline = calculateBaseline;
    }

    public String getDisplayUnit() {
        return displayUnit;
    }
    @XmlAttribute(name = "displayunit")
    public void setDisplayUnit(String displayUnit) {
        this.displayUnit = displayUnit;
    }

    public String getUriMatch() {
        return uriMatch;
    }
    @XmlAttribute(name = "urimatch")
    public void setUriMatch(String uriMatch) {
        this.uriMatch = uriMatch;
    }

    public String getQueryMatch() {
        return queryMatch;
    }
    @XmlAttribute(name = "querymatch")
    public void setQueryMatch(String queryMatch) {
        this.queryMatch = queryMatch;
    }

    public String getSqlMatch() {
        return sqlMatch;
    }
    @XmlAttribute(name = "sqlmatch")
    public void setSqlMatch(String sqlMatch) {
        this.sqlMatch = sqlMatch;
    }

    public String getMatch() {
        return match;
    }
    @XmlAttribute(name = "match")
    public void setMatch(String match) {
        this.match = match;
    }

    public String getErrorPageMatch() {
        return errorPageMatch;
    }
    @XmlAttribute(name = "errorpagematch")
    public void setErrorPageMatch(String errorPageMatch) {
        this.errorPageMatch = errorPageMatch;
    }

    public String getLoggingLevelMatch() {
        return loggingLevelMatch;
    }
    @XmlAttribute(name = "logginglevelmatch")
    public void setLoggingLevelMatch(String loggingLevelMatch) {
        this.loggingLevelMatch = loggingLevelMatch;
    }

    public String getInfoMatch() {
        return infoMatch;
    }
    @XmlAttribute(name = "infomatch")
    public void setInfoMatch(String infoMatch) {
        this.infoMatch = infoMatch;
    }

    public String getClassNameMatch() {
        return classNameMatch;
    }
    @XmlAttribute(name = "classnamematch")
    public void setClassNameMatch(String classNameMatch) {
        this.classNameMatch = classNameMatch;
    }

    public String getMethodNameMatch() {
        return methodNameMatch;
    }
    @XmlAttribute(name = "methodnamematch")
    public void setMethodNameMatch(String methodNameMatch) {
        this.methodNameMatch = methodNameMatch;
    }

    public String getEndPointMatch() {
        return endPointMatch;
    }
    @XmlAttribute(name = "endpointmatch")
    public void setEndPointMatch(String endPointMatch) {
        this.endPointMatch = endPointMatch;
    }

    public String getOperationNameMatch() {
        return operationNameMatch;
    }
    @XmlAttribute(name = "operationnamematch")
    public void setOperationNameMatch(String operationNameMatch) {
        this.operationNameMatch = operationNameMatch;
    }

    public MeasureThreshold getMeasureThreshold() {
        return measureThreshold;
    }
    @XmlElement(name = "thresholds")
    public void setMeasureThreshold(MeasureThreshold measureThreshold) {
        this.measureThreshold = measureThreshold;
    }

    public int compareTo(Measure measure) {
        return this.getId().compareTo(measure.getId());
    }
}
