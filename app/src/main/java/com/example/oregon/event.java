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
    public boolean activate (location loc, player play, event [] eventlist, boolean auto)
    {
        int n = random.nextInt() % 100;

        boolean check = false;
        if (n >= chance || auto) {
            switch (id) {
                case 0:
                    check = dysentaryDie(play, eventlist);
                    break;
                case 1:
                    check = dysentaryCon();
                    break;
                case 2:
                    check = lost(play, loc);
                    break;
                case 3:
                    check = oxenIll(play);
                    break;
                case 4:
                    check = oxenDeath(play, eventlist);
                    break;
                case 5:
                    check = dysRec(eventlist);
                    break;
                case 6:
                    check = oxRec(eventlist);
                    break;
                case 7:
                    check = broken(play);
                    break;
                case 8:
                    check = blizard();
                    break;
                case 9:
                    check = thief(play);
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

    public boolean lost (player play, location loc)
    {
        int days = (random.nextInt() % 3) + 4;
        for (int i = 0; i < days; i ++) {
            play.eat(1);
            loc.inc(0);
        }
        return true;
    }

    public boolean oxenIll (player play)
    {
        activity = true;
        return true;
    }

    public boolean oxenDeath (player play, event [] eventList)
    {
        if (eventList [3].getActivity())
        {
            play.changeInv(0, -1);

            eventList [3].deactivate();

            return true;
        }
        return false;
    }

    public boolean dysRec (event [] eventList)
    {
        if (eventList [1].getActivity())
        {
            eventList [1].deactivate();
            return true;
        }
        return false;
    }

    public boolean oxRec (event [] eventList)
    {
        if (eventList [3].getActivity())
        {
            eventList [3].deactivate();
            return true;
        }
        return false;
    }

    public boolean broken (player play)
    {
        play.changeInv(3, -1);
        return true;
    }

    public boolean blizard (location loc, player play, event [] eventList)
    {
        if (play.getInv(2) <= 6)
        {
            int days = (random.nextInt() % 3) + 4;
            for (int i = 0; i < days; i ++) {
                play.eat(1);
                loc.inc(0);
            }
            eventList [1].activate(loc, play, eventList, true);
        }
        return true;
    }

    public boolean thief (player play)
    {
        play.changeInv (1, -30);
        return true;
    }
}


