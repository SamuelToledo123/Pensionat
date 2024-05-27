package com.mindre.pensionat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;

public class BaseController  {
    protected static String VersionNumber = null;
    @Autowired
    private Environment env;

    public BaseController(){
    }


    protected void setupVersion(Model model) {
        if(VersionNumber == null){
            String buildnumber = env.getProperty("schedule-app.buildnumber");
            String sourceversion = env.getProperty("schedule-app.sourceversion");

            if (sourceversion != null && sourceversion.length() >= 7) {
                VersionNumber = buildnumber + "-" + sourceversion.substring(0,7);
            } else {
                VersionNumber = buildnumber;
            }
        }

        model.addAttribute("VersionNumber", VersionNumber);
    }
}