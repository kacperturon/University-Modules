package main;

/**
 *
 * @author Kacper
 */
public class NormalDistribution { //Class solely dedicated to Normal Distribution calculations, both static/dynamic implementations

    double mean, sd;
    double lowerBound, upperBound;
    int accuracy;

    public NormalDistribution() { //Assigns default values
        this.mean = 0.0;
        this.sd = 1.0;
        this.lowerBound = 0.0;
        this.upperBound = 1.0;
        this.accuracy = 5;
    }

    public NormalDistribution(double mean, double sd, double lowerBound, double upperBound, int accuracy) { //Values provided by user 
        this.mean = mean;
        this.sd = sd;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.accuracy = accuracy;
    }

    public static double probabilityDensityFunction(double mean, double sd, double x) { //Implemented PDF formula
        if (sd<1) { //Throw exception if standard deviation is lower than 1
            throw new IllegalArgumentException("Standard deviation must be larger than 0.");
        }
        double pdf = (1.0 / (sd * Math.sqrt(2.0 * Math.PI))) * Math.exp(-(Math.pow(x - mean, 2.0) / (2 * Math.pow(sd, 2.0))));
        return pdf;
    }

    public static double trapeziumRule(double mean, double sd, double lowerBound, double upperBound, int accuracy) { //Calculate Area using trapezium rule
        double area;
        int n = accuracy;
        double x1 = lowerBound, x2 = upperBound, delta = (x2 - x1) / n;
       
        if (n<1 || sd<1 ) { //Throw exception if standard deviation or accuracy is lower than 1
            throw new IllegalArgumentException("Standard Deviation and accuracy need to be larger than 0.");
        } 
        
        double basesFirstLast = 0, basesMiddle = 0; //basesFirstLast - sum of PDF for the first and last part of the curve, basesMiddle - everything excluding first and last
        double xTemp = x1; //Used to not overwrite x1 variable is used for PDF calculations and increased every loop by delta
        for (int i = 0; i < n + 1; i++) { //n+1 hence each trapezoid has 2 bases, therefore the number of heights we have to calculate will always be n+1
            if (i == 0 || i == n) {//first & last iteration
                basesFirstLast += probabilityDensityFunction(mean, sd, xTemp); //Sum of last and first loop PDF
            } else {
                basesMiddle += probabilityDensityFunction(mean, sd, xTemp);
            }
            xTemp += delta;
        }
        area = 1.0 / 2.0 * delta * (basesFirstLast + 2 * basesMiddle);
        return area;
    }

    public static double midPointRule(double mean, double sd, double lowerBound, double upperBound, int accuracy) { //Calculate Area using midPoint/rectangular rule
        double area;
        int n = accuracy;
        double x1 = lowerBound, x2 = upperBound, delta = (x2 - x1) / n;
        
        if (n<1 || sd<1 ) { //Throw exception if standard deviation or accuracy is lower than 1 
            throw new IllegalArgumentException("Standard Deviation and accuracy need to be larger than 0.");
        } 
        
        double midPointPDFs = 0; //Sum of all PDFs 
        double midPoint = (x1 + (x1 + delta)) / 2; //Value of the first midPoint between the first x and the second
        for (int i = 0; i < n; i++) { //Every loop increment midPoint and calculate PDF for that midpoint
            midPointPDFs += probabilityDensityFunction(mean, sd, midPoint);
            midPoint += delta;
        }
        area = delta * midPointPDFs; //Multiply the sum of PDFs with the step between each X value
        return area;
    }

    public static double simpsonsRule(double mean, double sd, double lowerBound, double upperBound, int accuracy) { //N - must be even
        double area;
        int n = accuracy;
        double x1 = lowerBound, x2 = upperBound, delta = (x2 - x1) / n;

        if (n<1 || n%2!=0 || sd<1 ) { //Throw exception if standard deviation or accuracy is lower than 1 or when accuracy isn't even 
            throw new IllegalArgumentException("Standard Deviation and accuracy need to be larger than 0 and even.");
        } 
        
        double simpsonsPDFs = 0; //Value of the sum of PDFs
        double xTemp = x1; //Temporary x1 value not to overwrite x1 is used as the x value for PDF incremented by delta
        for (int i = 0; i < n + 1; i++) { //Requires 2 points to make a measurement therefore n+1
            if (i == 0 || i == n) {//first & last iteration
                simpsonsPDFs += probabilityDensityFunction(mean, sd, xTemp); //Sum of last and first loop PDF
            } else {
                simpsonsPDFs += (i % 2 == 0 ? 2 : 4) * (probabilityDensityFunction(mean, sd, xTemp)); //Sum of PDFs excluding first and last, if odd multiply by 4 if even multiply by 2
            }
            xTemp += delta;
        }
        area = (delta / 3) * simpsonsPDFs; //Divide steps or delta X by 3 multiply with the sum of PDFs to get the area for simpsons rule
        return area;
    }

    public static double CDF(String integrationType, double mean, double sd, double lowerBound, double upperBound, int accuracy) { //Simply calculates area with the method passed by user to integrationType string
        if (integrationType.equals("TrapeziumRule")) {
            return trapeziumRule(mean, sd, lowerBound, upperBound, accuracy);
        } else if (integrationType.equals("MidPointRule")) {
            return midPointRule(mean, sd, lowerBound, upperBound, accuracy);
        } else if (integrationType.equals("SimpsonsRule")) {
            return simpsonsRule(mean, sd, lowerBound, upperBound, accuracy);
        }
        return 0;
    }

    /*Getters, setters below*/
    
    public double getTrapeziumRule() {
        return trapeziumRule(mean, sd, lowerBound, upperBound, accuracy);
    }

    public double getMidPointRule() {
        return midPointRule(mean, sd, lowerBound, upperBound, accuracy);
    }

    public double getSimpsonsRule() {
        return simpsonsRule(mean, sd, lowerBound, upperBound, accuracy);
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

}
