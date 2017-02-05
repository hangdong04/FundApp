package com.example.aaa.test.model;

/**
 * Created by phatt on 4/2/2560.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Fund {
    String Name;
    String Name_th;
    Float one;

    public Fund(String Name,String Name_th,Float one) {
        this.Name = Name;
        this.Name_th = Name_th;
        this.one = one;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName_th() {
        return Name_th;
    }

    public void setName_th(String name_th) {
        Name_th = name_th;
    }

    public Float getOne() {
        return one;
    }

    public void setOne(Float one) {
        this.one = one;
    }
}
