package com.example.mvp;

public class inventory {
    private int ammo = 0;
    private int food = 0;
    private int ox = 0;
    private int clothing = 0;
    private int parts = 0;

    public inventory () {
    }

    public int displayAmmo ()
    {
        return ammo;
    }
    public int displayFood ()
    {
        return food;
    }
    public int displayOxen ()
    {
        return ox;
    }
    public int displayClothing ()
    {
        return clothing;
    }
    public int displayParts ()
    {
        return parts;
    }

    public void changeAmmo (int x){
        ammo += x;
    }
    public void changeFood (int x){
        food += x;
    }
    public void changeOxen (int x){
        ox += x;
    }
    public void changeClothing (int x){
        clothing += x;
    }
    public void changeParts (int x){
        parts += x;
    }
}
