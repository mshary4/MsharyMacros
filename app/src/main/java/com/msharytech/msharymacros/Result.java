package com.msharytech.msharymacros;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Mshary on 2/25/18.
 */

@RealmClass
public class Result extends RealmObject {

    @PrimaryKey
    private String id;
    private int calBurned;
    private int intake;
    private int carb;
    private int fat;
    private int protein;
    private long timestamp;

    public Result() {
        id = UUID.randomUUID().toString();
        timestamp = Utils.getCurrentTimeStamp();
    }

    public Result(String id, int calBurned, int intake, int carb, int fat, int protein, long timestamp) {
        this.id = id;
        this.calBurned = calBurned;
        this.intake = intake;
        this.carb = carb;
        this.fat = fat;
        this.protein = protein;
        this.timestamp = timestamp;
    }

    public int getCalBurned() {
        return calBurned;
    }

    public void setCalBurned(int calBurned) {
        this.calBurned = calBurned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIntake() {
        return intake;
    }

    public void setIntake(int intake) {
        this.intake = intake;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static RealmResults<Result> getAllResults() {
        return RealmManager.getInstance().getRealmInstance()
                .where(Result.class).findAll();


    }

    public static void deleteAllResults() {
         RealmManager.getInstance().delete(getAllResults());
    }

    public static Result getResultByid(String id) {
        RealmQuery<Result> realmQuery = RealmManager.getInstance().getRealmInstance()
                .where(Result.class).contains("id", id);
        return realmQuery.findFirst();


    }



}
