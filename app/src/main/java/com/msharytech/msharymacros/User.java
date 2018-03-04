package com.msharytech.msharymacros;

import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Mshary on 4/9/17.
 */

@RealmClass
public class User extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;

    private String gender;
    private double age, weight, height, bodyfat;
    private int ActivityLevel;


    public User() {
        id="1";
    }




    public User(String gender, double age, double weight, double height, double bodyfat, int activityLevel) {
        this.id = UUID.randomUUID().toString();
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.bodyfat = bodyfat;
        ActivityLevel = activityLevel;
    }

    public User(String gender, double age, double weight, double height) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setActivityLevel(int activityLevel) {
        ActivityLevel = activityLevel;
    }

    public int getActivityLevel() {
        return ActivityLevel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAge() {
        return age;
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
        if (gender.equals("M") || gender.equals("m")) {
            BMR= (10 * weight) + (6.25 * height )- (5 * age )+ 5;
        } else if (gender.equals("F") || gender.equals("f")) {
            BMR= 10 * weight + 6.25 * height - 5 * age -161;
        }
        return Math.floor(BMR);

    }

    public static User getuser() {
        RealmQuery realmQuery= RealmManager.getInstance().getRealmInstance()
                .where(User.class).contains("id", "1");
        try {
            if(realmQuery!=null)
                return (User) realmQuery.findAll().last();
        }catch (Exception e){
            Toast.makeText(App.getContext(), "No data found", Toast.LENGTH_SHORT);
            return null;
        }


        return null;

    }


}

