package com.seleniumsimplified.webdriver.manager;

public class SystemPropertyOrEnvReader {
    /**
     * Allow setting the controls via property or environment variable
     * property takes precedence, then environment variable, then default
     */
    public static String getSystemPropertyOrEnv(String name, String theDefault){

        String propertyValue = System.getProperty(name);
        if (propertyValue !=null){
            System.out.println("Using Property " + name + " with value " + propertyValue);
            return propertyValue;
        }
        else {
            String environmentValue = System.getenv(name);
            if(environmentValue !=null){
                System.out.println("Using Environment Variable " + name + " with value " + environmentValue);
                return environmentValue;

            }else{
                System.out.println("No Environment Variable or Property named [" + name + "] available. Using default value [" + theDefault + "]");
                return theDefault;
            }
        }
    }
}
