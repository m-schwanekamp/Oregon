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

    //default constructor for player
    public player ()
    {
        name = "Hattie Campbell";
        initializeEvent();
    }
    
    //constructor
    //@param inName - name for player
    public player (String inName)
    {
        name = inName;
        initializeEvent();
    }

    //initializeEvent
    //creates the list of random events
    public void initializeEvent ()
    {
        eventlist [0] = new event("death by dysentary", 95, 0);
        eventlist [1] = new event("dysentary", 90, 1);

    }

    
    //getter for event log
    public String getEventLog ()
    {
        return eventLog;
    }
    
    //getter for name
    public String getName ()
    {
        return name;
    }

    // getInvText
    // @return - string displaying all sections of inventory
    public String getInvText ()
    {
        String create = "oxen: " + inv.displayOxen() + "\nfood: " + inv.displayFood() + "\nclothing: " + inv.displayClothing() + "\nparts: " + inv.displayParts() + "\nAmmunition: " + inv.displayAmmo() + "\nmoney: " + money + "\nfamily: " + family;
        return create;
    }

    // setter for inventory
    // @param catagorize - meant to tell which variable in inventory to change
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
    
    //getter for event log
    //@param catagorize - determines which part of inventory to get
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

    //getter for number of family member
    public int getFamily ()
    {
        return family;
    }

    //getter for money
    public int getMoney ()
    {
        return money;
    }

    //spend
    //@param x - ammount of money spent
    public void spend (int x)
    {
        money -= x;
    }

    //death
    //method to remove family members on death
    public void death ()
    {
        family--;
    }

    //eat
    //method to remove food when eating
    public void eat (int x)
    {
        inv.changeFood((family * -5)*x);
    }


    //getter for which display the game is currently on
    public int getDisplay ()
    {
        return display;
    }

    //setter for which display the game is currently on
    public void setDisplay (int x)
    {
        display = x;
    }

    //getter for players choice of what to buy
    public int getChoice (){
        return choice;
    }

    //handleEvent
    //tries to activate different types of events on the event list. Updates event log with successfully activated events
    //@param loc - location object reference for events that effect location
    public void handleEvent (location loc)
    {
        eventLog = "";
        for (int i = 0; i < eventlist.length; i ++)
        {
            if (eventlist [i].activate(loc, this, eventlist))
                eventLog = eventLog + "\n" + eventlist [i].getName();
        }
    }

    //input
    //decides which other input method to use based off display
    //@param loc - location object reference for when interaction is required
    //@param in - input of the user
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
    
    //worldInput
    //decides which input to use while traveling
    //@param loc - location object reference for when interaction is required
    //@param in - input of the user
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

    //shopInput
    //decides which input to use while in a shop
    //@param in - input of the user
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
    
    //buyInput
    //decides how much of an object the player wants to buy. The object in question is determined by choice
    //@param in - how much the player wants to buy
    public void buyInput (String in)
    {
        int n = Integer.parseInt(in);

        changeInv(choice, n);
        display = 1;
    }

}
