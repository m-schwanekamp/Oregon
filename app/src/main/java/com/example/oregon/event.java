/**
 * Description: The Event class is where Random events take place and have an effect on how the game is played
 * @author (Carson Pahl)
 * @version (4/4/23)
 */
package com.example.mvp;

import java.util.Random;

public class event {
    //instance variables
    private String name;
    private int id;
    private int people = 0;
    private boolean activity = false;
    private int chance;

    //Creates Random Number Method
    private Random random = new Random();
    
    //default constructor
    public event (String nam, int chan, int i)
    {
        name = nam;
        chance = chan;
        id = i;
    }

    //getter method for name 
    public String getName ()
    {
        return name;
    }

    //getter method for finding number of people affected by random events
    public int getPeople ()
    {
        return people;
    }
    
    //getter method for chance of the random event happening
    public int getChance ()
    {
        return chance;
    }

    //getter method for displaying whether method is active (true) or not active (false)
    public boolean getActivity ()
    {
        return activity;
    }

    /* activate() method generates a random number to decide whether that event happen
    @param location loc: object reference for location; player play: object reference for player; event [] eventlist: referencing the list of events
    @return true if the event activate; false if not activated
     */
    public boolean activate (location loc, player play, event [] eventlist)
    {
        int n = random.nextInt() % 100;

        boolean check = false;
        if (n >= chance) {
            switch (id) {
                case 0:
                    check = dysentaryDie(play, eventlist);
                    break;
                case 1:
                    check = dysentaryCon();
                    break;
            }
            return check;
        }
        return false;
    }

    //method to deactivate an event
    public void deactivate ()
    {
        activity = false;
    }

    //method to remove people when affected by a certain event
    public void affectPeople (int x)
    {
        people += x;
    }

    //setter method for chance
    public void setChance (int x)
    {
        chance = x;
    }

    //method for when dysentary is contracted
    public boolean dysentaryCon ()
    {
        activity = true;
        people ++;
        return true;
    }
    
    /*dysentaryDie() checks to see if dysentary event is active and if so a family member dies
    @param player play: object reference for player; event [] eventlist: referencing the list of events
    @return true if events activates therefore killing family member, false otherwise
     */
    public boolean dysentaryDie (player play, event [] eventList)
    {
        if (eventList [1].getActivity()) {
            play.death();

            eventList[1].affectPeople(-1);

            if (eventList[1].getPeople() == 0)
                eventList[1].deactivate();
            return true;
        }
        return false;
    }
}
