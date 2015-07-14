package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by kristof on 14.07.15.
 */
public class BusinessTransaction {
    private boolean enabled, exportIncludeSessionRef, errorDetectionSupport, exportIncludePerfData;
    private boolean aggregateGroups;
    private boolean export;
    private String servicecontext;
    private String transactiontype;
    private String errorTransactionType, subscriptionType, mode, id, description;
    private double minPercentage;
    private double maxsplittings;
    private ArrayList<MeasureReference> filters;
    private ArrayList<MeasureReference> splittings;
    private ArrayList<MeasureReference> results;

    public boolean isAggregateGroups() {
        return aggregateGroups;
    }
    @XmlAttribute(name = "aggregategroups")
    public void setAggregateGroups(boolean aggregateGroups) {
        this.aggregateGroups = aggregateGroups;
    }

    public String getDescription() {
        return description;
    }
    @XmlAttribute(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }
    @XmlAttribute(name = "enabled")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isErrorDetectionSupport() {
        return errorDetectionSupport;
    }
    @XmlAttribute(name = "errordetectionsupport")
    public void setErrorDetectionSupport(boolean errorDetectionSupport) {
        this.errorDetectionSupport = errorDetectionSupport;
    }

    public String getErrorTransactionType() {
        return errorTransactionType;
    }
    @XmlAttribute(name = "transactiontype")
    public void setErrorTransactionType(String errorTransactionType) {
        this.errorTransactionType = errorTransactionType;
    }

    public boolean isExport() {
        return export;
    }
    @XmlAttribute(name = "export")
    public void setExport(boolean export) {
        this.export = export;
    }

    public boolean isExportIncludePerfData() {
        return exportIncludePerfData;
    }
    @XmlAttribute(name = "exportincludeperfdata")
    public void setExportIncludePerfData(boolean exportIncludePerfData) {
        this.exportIncludePerfData = exportIncludePerfData;
    }

    public boolean isExportIncludeSessionRef() {
        return exportIncludeSessionRef;
    }
    @XmlAttribute(name = "exportincludesessionref")
    public void setExportIncludeSessionRef(boolean exportIncludeSessionRef) {
        this.exportIncludeSessionRef = exportIncludeSessionRef;
    }

    public ArrayList<MeasureReference> getFilters() {
        return filters;
    }
    @XmlElementWrapper(name = "filter")
    @XmlElement(name = "measureref")
    public void setFilters(ArrayList<MeasureReference> filters) {
        this.filters = filters;
    }

    public String getId() {
        return id;
    }
    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public double getMaxsplittings() {
        return maxsplittings;
    }
    @XmlAttribute(name = "maxsplittings")
    public void setMaxsplittings(double maxsplittings) {
        this.maxsplittings = maxsplittings;
    }

    public double getMinPercentage() {
        return minPercentage;
    }
    @XmlAttribute(name = "minpercentage")
    public void setMinPercentage(double minPercentage) {
        this.minPercentage = minPercentage;
    }

    public String getMode() {
        return mode;
    }
    @XmlAttribute(name = "mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    public ArrayList<MeasureReference> getResults() {
        return results;
    }
    @XmlElementWrapper(name = "evaluate")
    @XmlElement(name = "measureref")
    public void setResults(ArrayList<MeasureReference> results) {
        this.results = results;
    }
    @XmlAttribute(name = "servicecontext")
    public String getServicecontext() {
        return servicecontext;
    }

    public void setServicecontext(String servicecontext) {
        this.servicecontext = servicecontext;
    }

    public ArrayList<MeasureReference> getSplittings() {
        return splittings;
    }
    @XmlElementWrapper(name = "group")
    @XmlElement(name = "measureref")
    public void setSplittings(ArrayList<MeasureReference> splittings) {
        this.splittings = splittings;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }
    @XmlAttribute(name = "subscriptiontype")
    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getTransactiontype() {
        return transactiontype;
    }
    @XmlAttribute(name = "transactiontype")
    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }
}
