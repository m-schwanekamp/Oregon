package com.example.mvp;

import com.example.mvp.event;
import com.example.mvp.inventory;
import com.example.mvp.location;

public class player {
    private String name;
    private int money = 1600;
    private int family = 5;
    private inventory inv = new inventory();
    private int display = 999;
    private int choice = 0;
    private final int eventsize = 2;
    private event[] eventlist = new event [eventsize];
    private String eventLog;
    private int rations = 0;
    private int travel = 0;
    private boolean resting = false;

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
        eventlist [0] = new event("death by dysentary", 95, 0, 1, -100);
        eventlist [1] = new event("dysentary", 90, 1, 1, -100);
        eventlist [2] = new event("got lost", 90, 2, 0, -100);
        eventlist [3] = new event("oxen fallen ill", 90, 3, 1, -100);
        eventlist [4] = new event("oxen has died", 90, 4, 1, -100);
        eventlist [5] = new event("recovered from dysentary", 90, 5, -1, 20);
        eventlist [6] = new event("oxen recovered", 90, 6, -1, 20);
        eventlist [7] = new event("parts broken", 90, 7, 1, -100);
        eventlist [8] = new event("blizard", 90, 8, 0, 0);
        eventlist [9] = new event("thief has stollen food", 90, 9, 0, 0);
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
        inv.changeFood((family * (5 * (rations - 3)))*x);
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

    public int getRations () {return rations;}

    public int getTravel () {return travel;}

    public boolean getResting () {return resting;}

    //handleEvent
    //tries to activate different types of events on the event list. Updates event log with successfully activated events
    //@param loc - location object reference for events that effect location
    public void handleEvent (Location loc)
    {
        eventLog = "";
        for (int i = 0; i < eventlist.length; i ++)
        {
            if (eventlist [i].activate(loc, this, eventlist, false))
                eventLog = eventLog + "\n" + eventlist [i].getName();
        }
    }

    public String inputScreen ()
    {
        String output = "";
        switch (display) {
            case 0:
                output = "type 0 to progress on the trail\ntype 1 to manage your inventory\ntype 2 to go to the shop";
                break;
            case 1:
                output = "choose which goods to purchase:\n0 = oxen\n1 = food\n2 = clothing\n3 = parts\n4 = ammunition\npress 5 to leave";
                break;
            case 2:
                output = "type how much you wish to buy";
                break;
            case 3:
                output = "choose how long you wish to rest";
                break;
            case 4:
                output = "choose which ration plan:\n0 = large rations\n1 = mediumn rations\n2 = small rations";
                break;
            case 5:
                output = "choose how long you wish to travel each day:\n0 = 20 miles\n1 = 40 miles\n2 = 60 miles";
                break;
            case 999:
                output = "type anything to continue";
        }

        return output;
    }

    //input
    //decides which other input method to use based off display
    //@param loc - location object reference for when interaction is required
    //@param in - input of the user
    public void input (Location loc, String in)
    {
        switch (display) {
            case 0: worldInput (loc, in);
                break;
            case 1: shopInput (in);
                break;
            case 2: buyInput (in);
                break;
            case 3: restInput(in, loc);
                break;
            case 4: rationInput(in);
                break;
            case 5: travelInput(in);
                break;
            case 999:
                display = 0;
                break;
        }
    }

    //worldInput
    //decides which input to use while traveling
    //@param loc - location object reference for when interaction is required
    //@param in - input of the user
    public void worldInput (Location loc, String in)
    {
        switch (in){
            case "0":
                loc.inc(20 * (travel + 1));
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
            case "3":
                display = 3;
                break;
            case "4":
                display = 4;
                break;
            case "5":
                display 5;
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

    public void restInput (String in, Location loc)
    {
        resting = true;
        int n = Integer.parseInt(in);
        for (int i = 0; i < n; i ++) {
            loc.inc(0);
            handleEvent(loc);
        }
        resting = false;
    }

    public void rationInput (String in)
    {
        int n = Integer.parseInt(in);
        if (0 <= n || n <= 2)
        {
            rations = n;
        }
    }

    public void travelInput (String in)
    {
        int n = Integer.parseInt(in);
        if (0 <= n || n <= 2)
        {
            travel = n;
        }
    }
}
