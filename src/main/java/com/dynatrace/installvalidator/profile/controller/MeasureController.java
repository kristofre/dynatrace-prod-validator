package com.dynatrace.installvalidator.profile.controller;

import com.dynatrace.installvalidator.profile.parser.model.Measure;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 26.05.15.
 */
public class MeasureController {
    private static SystemProfile profile = null;
    public MeasureController(SystemProfile profile) {
        this.profile = profile;
    }

    public ArrayList<Measure> getCustomMeasures()
    {
        ArrayList<Measure> customMeasures = new ArrayList<Measure>();
        ArrayList<Measure> measuresInProfile = profile.getMeasures();
        for (Iterator<Measure> measureIterator = measuresInProfile.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            if(measure.getUserDefined()) customMeasures.add(measure);
        }

        return customMeasures;
    }

    public ArrayList<Measure> getMeasuresThatUseRegexEval()
    {
        ArrayList<Measure> regexMeasures = new ArrayList<Measure>();
        ArrayList<Measure> measuresInProfile = profile.getMeasures();
        for (Iterator<Measure> measureIterator = measuresInProfile.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            if(measure.getQueryMatch() != null && measure.getQueryMatch().equals("regex")) { regexMeasures.add(measure); }
            else if(measure.getSqlMatch() != null && measure.getSqlMatch().equals("regex")) { regexMeasures.add(measure); }
            else if(measure.getUriMatch() != null && measure.getUriMatch().equals("regex")) { regexMeasures.add(measure); }
            else if(measure.getMatch() != null && measure.getMatch().equals("regex")) { regexMeasures.add(measure); }
            else if(measure.getMethodNameMatch() != null && measure.getMethodNameMatch().equals("regex")) { regexMeasures.add(measure); }
            else if(measure.getInfoMatch() != null && measure.getInfoMatch().equals("regex")) { regexMeasures.add(measure); }
            else if(measure.getClassNameMatch() != null && measure.getClassNameMatch().equals("regex")) { regexMeasures.add(measure); }
            else if(measure.getErrorPageMatch() != null && measure.getErrorPageMatch().equals("regex")) { regexMeasures.add(measure); }
            else if(measure.getLoggingLevelMatch() != null && measure.getLoggingLevelMatch().equals("regex")) { regexMeasures.add(measure); }
        }

        return regexMeasures;
    }
}
