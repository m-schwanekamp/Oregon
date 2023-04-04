package com.example.mvp;

import com.example.mvp.event;
import com.example.mvp.inventory;
import com.example.mvp.location;

public class player {
    private String name;
    private int money = 1600;
    private int family = 5;
    private inventory inv = new inventory();
    private int display = 0;
    private int choice = 0;
    private final int eventsize = 2;
    private event[] eventlist = new event [eventsize];
    private String eventLog;

    public player (String inName)
    {
        name = inName;
        initializeEvent();
    }

    public void initializeEvent ()
    {
        eventlist [0] = new event("death by dysentary", 95, 0);
        eventlist [1] = new event("dysentary", 90, 1);

    }

    public String getEventLog ()
    {
        return eventLog;
    }

    public String getName ()
    {
        return name;
    }

    public String getInvText ()
    {
        String create = "oxen: " + inv.displayOxen() + "\nfood: " + inv.displayFood() + "\nclothing: " + inv.displayClothing() + "\nparts: " + inv.displayParts() + "\nAmmunition: " + inv.displayAmmo() + "\nmoney: " + money + "\nfamily: " + family;
        return create;
    }

    public void changeInv (int catagorize, int x)
    {
        switch (catagorize)
        {
            case 0: inv.changeOxen(x);
                break;
            case 1: inv.changeFood(x);
                break;
            case 2: inv.changeClothing(x);
                break;
            case 3: inv.changeParts(x);
                break;
            case 4: inv.changeAmmo(x);
                break;
        }
    }

    public int getInv (int catagorize)
    {
        switch (catagorize)
        {
            case 0: return inv.displayOxen();

            case 1: return inv.displayFood();

            case 2: return inv.displayClothing();

            case 3: return inv.displayParts();

            case 4: return inv.displayAmmo();

        }
        return 0;
    }

    public int getFamily ()
    {
        return family;
    }

    public int getMoney ()
    {
        return money;
    }

    public void spend (int x)
    {
        money -= x;
    }

    public void death ()
    {
        family--;
    }

    public void eat (int x)
    {
        inv.changeFood((family * -5)*x);
    }

    public int getDisplay ()
    {
        return display;
    }

    public void setDisplay (int x)
    {
        display = x;
    }

    public int getChoice (){
        return choice;
    }

    public void handleEvent (location loc)
    {
        eventLog = "";
        for (int i = 0; i < eventlist.length; i ++)
        {
            if (eventlist [i].activate(loc, this, eventlist))
                eventLog = eventLog + "\n" + eventlist [i].getName();
        }
    }

    public void input (location loc, String in)
    {
        switch (display) {
            case 0: worldInput (loc, in);
                break;
            case 1: shopInput (in);
                break;
            case 2: buyInput (in);
                break;
        }
    }

    public void worldInput (location loc, String in)
    {
        switch (in){
            case "0":
                loc.inc(20);
                eat (1);
                handleEvent(loc);
                break;
            case "1":
                break;
            case "2":
                if ( loc.getDistance() == 120 ||  loc.getDistance() == 340 || loc.getDistance() == 0)
                {
                    display = 1;
                }
                break;
        }
    }

    public void shopInput (String in) {
        display = 2;
        switch (in){
            case "0": choice = 0;
                break;
            case "1": choice = 1;
                break;
            case "2": choice = 2;
                break;
            case "3": choice = 3;
                break;
            case "4": choice = 4;
                break;
            case "5": display = 0;
        }
    }

    public void buyInput (String in)
    {
        int n = Integer.parseInt(in);

        changeInv(choice, n);
        display = 1;
    }

}
