package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.library.model.MeasureType;
import com.dynatrace.installvalidator.profile.parser.model.Measure;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 26.05.15.
 */
public class MeasureController extends BaseController{
    private static ArrayList<String> typesToSkip;
    public MeasureController(SystemProfile profile, String configFile) {
        super(profile, configFile);
        typesToSkip = getCustomMeasureTypesToExcludeFromListing();
    }

    public ArrayList<Measure> getCustomMeasures()
    {
        ArrayList<Measure> customMeasures = new ArrayList<Measure>();
        ArrayList<Measure> measuresInProfile = getProfile().getMeasures();
        for (Iterator<Measure> measureIterator = measuresInProfile.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            if(measure.getUserDefined()&&!typesToSkip.contains(measure.getMeasureType())) customMeasures.add(measure);
        }

        return customMeasures;
    }

    public ArrayList<Measure> getMeasuresThatUseRegexEval(boolean onlyCustom)
    {
        ArrayList<Measure> regexMeasures = new ArrayList<Measure>();
        ArrayList<Measure> measuresInProfile = getProfile().getMeasures();
        for (Iterator<Measure> measureIterator = measuresInProfile.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            if(onlyCustom && !measure.getUserDefined()) {}
            else {
                if (measure.getQueryMatch() != null && measure.getQueryMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getSqlMatch() != null && measure.getSqlMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getUriMatch() != null && measure.getUriMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getMatch() != null && measure.getMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getMethodNameMatch() != null && measure.getMethodNameMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getInfoMatch() != null && measure.getInfoMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getClassNameMatch() != null && measure.getClassNameMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getErrorPageMatch() != null && measure.getErrorPageMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getLoggingLevelMatch() != null && measure.getLoggingLevelMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getEndPointMatch() != null && measure.getEndPointMatch().equals("regex")) {
                    regexMeasures.add(measure);
                } else if (measure.getOperationNameMatch() != null && measure.getOperationNameMatch().equals("regex")) {
                    regexMeasures.add(measure);
                }

            }
        }

        return regexMeasures;
    }

    public ArrayList<Measure> getMeasuresThatSplitByAgent(boolean onlyCustom)
    {
        return getMeasuresThatSplitByAgent(onlyCustom, false);
    }

    public ArrayList<Measure> getMeasuresThatSplitByAgent(boolean onlyCustom, boolean skipKnownTypes)
    {
        ArrayList<Measure> splitMeasures = new ArrayList<Measure>();
        ArrayList<Measure> measuresInProfile = getProfile().getMeasures();
        for (Iterator<Measure> measureIterator = measuresInProfile.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            if (onlyCustom && !measure.getUserDefined()) {}
            else
            {
                if(!measure.getIsAggregated()) {
                    if (skipKnownTypes) {
                        if(!typesToSkip.contains(measure.getMeasureType())) splitMeasures.add(measure);
                    }
                    else splitMeasures.add(measure);
                }
            }
        }

        return splitMeasures;
    }

    public ArrayList<Measure> getMeasuresThatSplitByApplication(boolean onlyCustom)
    {
        return getMeasuresThatSplitByApplication(onlyCustom, false);
    }

    public ArrayList<Measure> getMeasuresThatSplitByApplication(boolean onlyCustom, boolean skipKnownTypes)
    {
        ArrayList<Measure> splitMeasures = new ArrayList<Measure>();
        ArrayList<Measure> measuresInProfile = getProfile().getMeasures();
        for (Iterator<Measure> measureIterator = measuresInProfile.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            if (onlyCustom && !measure.getUserDefined()) {
            } else
            {
                if(!measure.getIsApplicationAggregated()) {
                    if (skipKnownTypes) {
                        if(!typesToSkip.contains(measure.getMeasureType())) splitMeasures.add(measure);
                    }
                    else splitMeasures.add(measure);
                }
            }
        }

        return splitMeasures;
    }

    public ArrayList<Measure> getMeasuresOfType(boolean onlyCustom, String type)
    {
        ArrayList<Measure> measures = new ArrayList<Measure>();
        ArrayList<Measure> measuresInProfile = getProfile().getMeasures();
        for (Iterator<Measure> measureIterator = measuresInProfile.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            if (onlyCustom && !measure.getUserDefined()) {
            } else
            {
                if(measure.getMeasureType().equals(type)) measures.add(measure);
            }
        }

        return measures;
    }

    private ArrayList<String> getCustomMeasureTypesToExcludeFromListing()
    {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<MeasureType> allTypes = getSensorLibrary().getMeasureTypes();
        for (MeasureType measureType : allTypes) {
			if (measureType.isHide()) list.add(measureType.getId());
		}

        return list;
    }
    
    private ArrayList<MeasureType> getCustomMeasureTypesToExclude()
    {
    	ArrayList<MeasureType> allTypes = getSensorLibrary().getMeasureTypes();
    	ArrayList<MeasureType> excludeTypes = new ArrayList<MeasureType>();
    	
    	for (MeasureType measureType : allTypes) {
			if (measureType.isHide()) excludeTypes.add(measureType);
		}
    	
    	return excludeTypes;
    }
    
    private String translateMeasureType(String measureTypeId)
    {
    	ArrayList<MeasureType> allTypes = getSensorLibrary().getMeasureTypes();
    	
    	for (MeasureType measureType : allTypes) {
			if (measureType.isHide()) return measureType.getName();
		}
    	
    	return measureTypeId;
    }
}
