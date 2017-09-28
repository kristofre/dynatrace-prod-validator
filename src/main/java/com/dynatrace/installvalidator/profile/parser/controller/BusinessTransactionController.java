package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.parser.model.BusinessTransaction;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 14.07.15.
 */
public class BusinessTransactionController extends BaseController {
    public BusinessTransactionController(SystemProfile profile, String configFile) {
        super(profile, configFile);
    }

    public ArrayList<BusinessTransaction> getBusinessTransactions()
    {
        return getProfile().getBusinessTransactions();
    }

    public ArrayList<BusinessTransaction> getUserDefinedBusinessTransactions()
    {
        ArrayList<BusinessTransaction> result = new ArrayList<BusinessTransaction>();
        ArrayList<BusinessTransaction> allBT = getProfile().getBusinessTransactions();
        for (Iterator<BusinessTransaction> businessTransactionIterator = allBT.iterator(); businessTransactionIterator.hasNext(); ) {
            BusinessTransaction businessTransaction = businessTransactionIterator.next();
            if(businessTransaction.getSubscriptionType().equals("userdefined")) result.add(businessTransaction);
        }
        return result;
    }

    public String translateServiceContext(String serviceContext)
    {
        if(serviceContext.toLowerCase().equals("server")) return "Server-Side PurePath";
        if(serviceContext.toLowerCase().equals("end_user")) return "User Action";
        if(serviceContext.toLowerCase().equals("visit")) return "Visit";
        return "N/A";
    }
}
