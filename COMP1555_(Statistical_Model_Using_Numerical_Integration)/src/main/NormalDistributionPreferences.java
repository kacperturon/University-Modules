package main;

import java.util.prefs.Preferences;

/**
 *
 * @author Kacper
 */
public final class NormalDistributionPreferences { //Class responsible for saving and obtaining data from the register

    private final static Preferences preferences; //Object allowing access to register 

    static { //Static constructor that sets default values in the register if there are none set
        preferences = Preferences.userRoot().node("NormalDistributionAnalysisTool"); //Create node in register
        if (preferences.get("IntegrationType", null) == null) { //If there are no settings on computer set default
            setIntegrationType("SimpsonsRule"); //Default integration type
            setAccuracyAuto(true); //Default accuracy
        }
    }

    private NormalDistributionPreferences() { //Private constructor so the objects cannot be initialized (all methods are static)
    }

    public static void setIntegrationType(String integrationType) { //Save the name of integration in the register
        if (!integrationType.equals("TrapeziumRule") && !integrationType.equals("MidPointRule") && !integrationType.equals("SimpsonsRule")) { //Allow only three rules
            throw new IllegalArgumentException("Only TrapeziumRule, MidPointRule & SimpsonsRule are allowed.");
        }
        preferences.put("IntegrationType", integrationType); //Save in register under IntegrationType variable
    }

    public static void setAccuracyAuto(boolean auto) { //Save that accuracy should be calculated from predefined values (auto == true) or from user defined values (auto == false)
        preferences.putBoolean("AccuracyAuto", auto); //Set accuracyAuto variable in register to true/false
        //If auto is set to true one of the below values will be set in register depending on type of integration
        if (auto && getIntegrationType().equals("TrapeziumRule")) { //Optimal accuracy value for TrapeziumRule 1119 is set when user chooses auto accuracy and Trapezium rule
            setAccuracy(1119);
        } else if (auto && getIntegrationType().equals("MidPointRule")) { //Optimal accuracy value for MidPointRule 1905 is set when user chooses auto accuracy and MidPoint rule
            setAccuracy(1905);
        } else if (auto && getIntegrationType().equals("SimpsonsRule")) { //Optimal accuracy value for SimpsonsRule 32 is set when user chooses auto accuracy and Simpsons rule
            setAccuracy(32);
        }
    }

    public static void setAccuracy(int accuracy) throws IllegalArgumentException {
        if (accuracy < 1 && getAccuracyAuto() == false) { //Make sure accuracy is positive and not set to auto
            throw new IllegalArgumentException("Accuracy must be positive number.");
        }
        if (getIntegrationType().equals("SimpsonsRule") && accuracy % 2 != 0) { //For simpsons rule accuracy must be even
            throw new IllegalArgumentException("For Simspons Rule accuracy must be even");
        }

        preferences.putInt("Accuracy", accuracy); //Save the value for accuracy in the register
    }

    public static String getIntegrationType() {
        return preferences.get("IntegrationType", ""); //Get the string for integrationType fromr egsiter
    }

    public static boolean getAccuracyAuto() {
        return preferences.getBoolean("AccuracyAuto", false); //Get the boolean true if accuracy is set to auto and false if accuracy is not set to auto
    }

    public static int getAccuracy() { 
        return preferences.getInt("Accuracy", 0); //Get the value for accuracy
    }

}
