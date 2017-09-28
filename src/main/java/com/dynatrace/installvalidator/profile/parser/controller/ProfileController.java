package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;
import com.dynatrace.installvalidator.profile.parser.model.Technology;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 01.06.15.
 */
public class ProfileController extends BaseController {
    public ProfileController(SystemProfile profile, String configFile) {
        super(profile, configFile);
    }

    public ArrayList<String> getActiveTechnologies()
    {
        ArrayList<String> technos = new ArrayList<String>();
        ArrayList<Technology> profileTechnos = this.getProfile().getTechnologiesUsed();
        for (Iterator<Technology> iterator = profileTechnos.iterator(); iterator.hasNext(); ) {
            Technology next = iterator.next();
            technos.add(next.getType());
        }

        return technos;
    }
}
