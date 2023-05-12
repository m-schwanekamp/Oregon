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
    private int riskChance;
    private int restChance;

    //Creates Random Number Method
    private Random random = new Random();

    //default constructor
    public event (String nam, int chan, int i, int risk, int rest)
    {
        name = nam;
        chance = chan;
        id = i;
        riskChance = risk;
        restChance = rest;
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

        int alter = (play.getRations() + play.getTravel()) * riskChance;

        if (play.getResting())
            alter = restChance;

        n += alter;

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
                    check = blizard(loc, play, eventlist);
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
    /*lost() random event that simulates user getting lost on trail
    @param player play: object reference for player; location loc: object reference for location
    @return true if events activates therefore taking away some food, false otherwise
     */
    public boolean lost (player play, location loc)
    {
        int days = (random.nextInt() % 3) + 4;
        for (int i = 0; i < days; i ++) {
            play.eat(1);
            loc.inc(0);
        }
        return true;
    }
    //Method to simulate one of the user's oxen falling ill    
    public boolean oxenIll (player play)
    {
        activity = true;
        return true;
    }
    /* oxenDeath() random event that simulates death of oxen. Oxen Ill method must be true for this to activate
    @param player play: object reference for player; event [] eventlist: referencing the list of events
    @return true if events activates therefore taking away 1 oxen (oxenIll() method must be true in order for this class to be true, false otherwise
     */
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
    //This method deactivates dysentary on a person making them unable to further die from dysentary
    public boolean dysRec (event [] eventList)
    {
        if (eventList [1].getActivity())
        {
            eventList [1].deactivate();
            return true;
        }
        return false;
    }
    //This method deactivates oxen falling ill making them unable for that oxen to die
    public boolean oxRec (event [] eventList)
    {
        if (eventList [3].getActivity())
        {
            eventList [3].deactivate();
            return true;
        }
        return false;
    }
    // this method simulates your wagon breaking 
    public boolean broken (player play)
    {
        play.changeInv(3, -1);
        return true;
    }
    /* blizzard() random event that simulates blizzard.
    @param player play: object reference for player; event [] eventlist: referencing the list of events; location loc: object reference for location
    @return true if events activates, if not enough clothes, player gets lost for a couple of days; false otherwise
    */
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
    /* theif() random event that simulates player getting robbed by bandits.
    @param player play: object reference for player
    @return true if events activates, therefore removing 30 food; false otherwise
    */
    public boolean thief (player play)
    {
        play.changeInv (1, -30);
        return true;
    }
}

