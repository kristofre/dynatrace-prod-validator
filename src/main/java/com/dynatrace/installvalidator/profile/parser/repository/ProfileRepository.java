package com.dynatrace.installvalidator.profile.parser.repository;

import com.dynatrace.installvalidator.profile.parser.model.Dynatrace;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by kristof on 09.07.15.
 */
public class ProfileRepository {
    public ProfileRepository() {
    }

    public Dynatrace getDynatrace(File file)
    {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Dynatrace.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();


            Dynatrace dtProfile = (Dynatrace) jaxbUnmarshaller.unmarshal(file);
            SystemProfile profile = dtProfile.getSystemProfile();
            //System.out.println("Profile Description: " + profile.getDescription());

            return dtProfile;

            //ArrayList<AgentGroup> agentGroups = profile.getAgentGroups();

            /*for (AgentGroup group:agentGroups)
            {
                //System.out.println("Group Name: " + group.getId());
            }*/


        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new Dynatrace();
    }
}
