package msharytech.msharymacros;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by Mshary on 4/9/17.
 */

public class User implements Serializable {
    private char gender;
    private double age, weight, height, bodyfat;
    private int ActivityLevel;


    public User() {
    }

    public User(char gender, double age, double weight, double height, double bodyfat) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.bodyfat = bodyfat;
    }

    public User(char gender, double age, double weight, double height) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public void setActivityLevel(int activityLevel) {
        ActivityLevel = activityLevel;
    }

    public int getActivityLevel() {
        return ActivityLevel;
    }

    public char getGender() {
        return gender;
    }

    public double getAge() {
        return age;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setBodyfat(double bodyfat) {
        this.bodyfat = bodyfat;
    }

    public double getWeight() {
        return weight;
    }
    public double getWeightINlb() {
        return  weight / 0.45359237;
    }
    public double getHeight() {
        return height;
    }
    public double getBodyfat() {
        return bodyfat;
    }

    public double leanBodyMass(){
        return weight-(weight*bodyfat/100);
    }

    public double leanBodyMassINlb(){
        return getWeightINlb()-(getWeightINlb()*bodyfat/100);
    }

    public double BMR() {
        Double BMR = 0.0;
        if (gender == 'M' || gender == 'm') {
            BMR= (10 * weight) + (6.25 * height )- (5 * age )+ 5;
        } else if (gender == 'F' || gender == 'f') {
            BMR= 10 * weight + 6.25 * height - 5 * age -161;
        }
        return Math.floor(BMR);

    }



}

