/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Josec
 */
public class Car {
    public String name;
    public int year;
    public float mileage;
    public boolean american;
    public int serial;

    public Car(String name, int year, float mileage, boolean american, int serial) {
        this.name = name;
        this.year = year;
        this.mileage = mileage;
        this.american = american;
        this.serial = serial;
    }
    
    public Car() {
        this.name = "";
        this.year = 0;
        this.mileage = 0;
        this.american = false;
        this.serial = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public boolean isAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
    public int sizeB(){
        return this.name.length()*2+4+4+1+4;
    }

    @Override
    public String toString() {
        return "Car{" + "name=" + name + ", year=" + year + ", mileage=" + mileage + ", american=" + american + ", serial=" + serial + '}';
    }
    
    
}
