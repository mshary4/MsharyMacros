package msharytech.msharymacros;

import android.util.Log;

/**
 * Created by Mshary on 7/9/17.
 */

public class Calculate{
    private User user;
    private boolean isItLb=new App().isItLB();
    private double protien,carb,fat,calorieIntake;
    private boolean Complete=false;

    public Calculate() {
    }

    public Calculate(double protien, double carb, double fat) {
        this.protien = protien;
        this.carb = carb;
        this.fat = fat;
    }

    public Calculate(User user) {

        this.user = user;
    }



public Calculate Macros (User user){
    Calculate calculate = new Calculate(user);
    double Cal=calculate.getCaloriesintake();
    Log.e("FINAL",String.valueOf(Cal));
    double[] p=calculate.protein();
    double[]f=calculate.fat(Cal);
    double[] c=calculate.carb(f[1],Cal,p);
    calculate.setCarb(c[0]);
    calculate.setFat(f[0]);
    calculate.setProtien(p[0]);
    calculate.setComplete(true);

    return calculate;
}



    private double[] protein() {
        //// TODO: 7/9/17 add Calc without bodyfat
        double weight=user.leanBodyMass(); // get user weight
        char Gender=user.getGender(); // get user gender
        if(!isItLb) weight=user.leanBodyMassINlb(); // Convert user Kg in LB
        double[] protein = new double[2];
        if ( Gender== 'M' || Gender == 'm') {
            protein[0] = Math.floor(weight* 1.25); // calculate cal for protein
            protein[1] = Math.floor(protein[0] * 4); //calculate Gram of protein
        } else if (Gender == 'F' || Gender == 'f') {
            protein[0] =Math.round(weight*1.3); // calculate cal for protein
            protein[1] = Math.round(protein[0] * 4); //calculate Gram of protein
        }

    return  protein;
    }


    private double[] fat(double calintake) {
        double[] fat = new double[2];
        fat[1] = calintake * 0.25;
        fat[0] = Math.round(fat[1] / 9);
        return fat;

    }


    //fat 1
    private double[] carb(double fat, double cal, double[] p) {

        double[] carb = new double[2];
        carb[1] = cal - (fat + p[1]);
        carb[0] = Math.floor(carb[1] / 4);

        return carb;

    }

    public double getCaloriesintake(){
       return 0.75*DailyCaloriesBurned();
    }

    public double getProtien() {
        return protien;
    }

    private void setProtien(double protien) {
        this.protien = protien;
    }

    public double getCarb() {
        return carb;
    }

    private void setCarb(double carb) {
        this.carb = carb;
    }

    public double getFat() {
        return fat;
    }

    private void setFat(double fat) {
        this.fat = fat;
    }



    private void setCalorieIntake(double calorieIntake) {
        this.calorieIntake = calorieIntake;
    }

    private void setComplete(boolean complete) {
        Complete = complete;
    }

    public boolean isComplete() {
        return Complete;
    }

    public double DailyCaloriesBurned(){
        double BMR = user.BMR();
        switch (user.getActivityLevel()) {
            case 0:
                BMR = BMR * 1;
            case 1:
                BMR = BMR * 1.2;
                break;
            case 2:
                BMR = BMR * 1.375;

            case 3:
                BMR = BMR * 1.55;
                break;
            case 4:
                BMR = BMR * 1.725;
                break;
            case 5:
                BMR = BMR * 1.9;
                break;

        }
        return Math.floor(BMR);
    }


}
