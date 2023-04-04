package com.example.mvp;

public class inventory {
    // initialize variables for things in inventory
    private int ammo = 0;
    private int food = 0;
    private int ox = 0;
    private int clothing = 0;
    private int parts = 0;

    //default constructor
    public inventory () {
    }

    //getter for ammunition
    public int displayAmmo ()
    {
        return ammo;
    }
    
    //getter for food
    public int displayFood ()
    {
        return food;
    }
    
    //getter for oxen
    public int displayOxen ()
    {
        return ox;
    }
    
    //getter for clothing
    public int displayClothing ()
    {
        return clothing;
    }
    
    //getter for parts
    public int displayParts ()
    {
        return parts;
    }

    //setter for ammo
    public void changeAmmo (int x){
        ammo += x;
    }
    
    //setter for food
    public void changeFood (int x){
        food += x;
    }
    
    
    //setter for oxen
    public void changeOxen (int x){
        ox += x;
    }
    
    
    //setter for clothing
    public void changeClothing (int x){
        clothing += x;
    }
    
    
    //setter for parts
    public void changeParts (int x){
        parts += x;
    }
}
