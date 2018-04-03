/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import domain.Car;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josec
 */
public class CarData {
    //atributos
    public RandomAccessFile randomAccessFile;
    private int regSize, regsQuantity; //tamano del registro, cantidad de registros
    private String path;//ruta
    File file;
    private ArrayList<Car> RegisteredCarList;

    //Constructor
    public CarData() throws IOException {
        this.path = "Cars.dad";
        this.file = new File(this.path);
        start(file);

    }
    //crea el archivo
    private void start(File file) throws IOException {
        this.path = file.getPath();
        this.regSize = 60;

        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + "Invalid file");
        } else {
            this.randomAccessFile = new RandomAccessFile(file, "rw");
            this.regsQuantity = (int) Math.ceil((double) this.randomAccessFile.length() / (double) this.regSize);
        }//else
    }//start
    
    public void close() throws IOException{
        randomAccessFile.close();
    }
    
    public int fileSize(){
        return this.regsQuantity;
    }
    //guarda los autos
    public boolean saveCar(int position, Car car) throws IOException {

        if (position >= 0 && position <= regsQuantity) {
            if (car.sizeB() > regSize) {
                System.err.println("1002 - Record size is out of bounds");
            } else {
                this.randomAccessFile.seek(position * this.regSize);
                this.randomAccessFile.writeUTF(car.getName());
                this.randomAccessFile.writeInt(car.getYear());
                this.randomAccessFile.writeFloat(car.getMileage());
                this.randomAccessFile.writeBoolean(car.isAmerican());
                this.randomAccessFile.writeInt(car.getSerial());

                return true;
            }
        } else {
            System.err.println("1001 - Record position is out of bounds");
        }
        return false;
    }
    //asigna la posicion para escribir despues del ultimo elemento del archivo
    public void insertCAr(Car car) throws IOException {
        if(saveCar(this.regsQuantity, car)){
            regsQuantity++;
        }
    }
    
    public boolean deleteCar(int serial) throws IOException{
    int position=searchC(serial);
    if(position==-1){
        return false;
    }
    Car carD=getCar(position);
    carD.setSerial(0);
    return saveCar(position, carD);
    }
    
    public boolean updateCar(int serial,String name,boolean american,int year) throws IOException{
    int position=searchC(serial);
    if(position==-1){
        return false;
    }
    Car carD=getCar(position);
    carD.setName(name);
    carD.setAmerican(american);
    carD.setYear(year);
    return saveCar(position, carD);
    }
    
    public int searchC(int serial){
        Car c =new Car();
        if(serial == 0){
            return -1;
        }
        for (int i = 0; i < regsQuantity; i++) {
            try {
                this.randomAccessFile.seek(i*this.regSize);
                c=getCar(i);
                if(c.getSerial()==serial){
                    return i;
                }
            } catch (IOException ex) {
                Logger.getLogger(CarData.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return -1;
    }
    
    public Car getCar(int i) throws IOException{
        if(i >= 0 && i <= this.regsQuantity){
                this.randomAccessFile.seek(i*regSize);
                return new Car(randomAccessFile.readUTF(),randomAccessFile.readInt(),randomAccessFile.readFloat(),
                        randomAccessFile.readBoolean(),randomAccessFile.readInt());
        }
        else{
            return null;
        }
    }
    
    public ArrayList<Car> carList() throws IOException {
        this.RegisteredCarList = new ArrayList<Car>();

        for (int i = 0; i < fileSize(); i++) {
            this.randomAccessFile.seek(i * this.regSize);

            Car car = new Car();
            car.setName(this.randomAccessFile.readUTF());
            car.setYear(this.randomAccessFile.readInt());
            car.setMileage(this.randomAccessFile.readFloat());
            car.setAmerican(this.randomAccessFile.readBoolean());
            car.setSerial(this.randomAccessFile.readInt());

            this.RegisteredCarList.add(car);
        }//for//for
        return RegisteredCarList;
    }
}
