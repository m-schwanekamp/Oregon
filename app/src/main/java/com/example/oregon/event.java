import java.util.Random;

public class event {
    private String name;
    private int id;
    private int people = 0;
    private boolean activity = false;
    private int chance;
    private Random random = new Random();

    public event (String nam, int chan, int i)
    {
        name = nam;
        chance = chan;
        id = i;
    }

    public String getName ()
    {
        return name;
    }

    public int getPeople ()
    {
        return people;
    }

    public int getChance ()
    {
        return chance;
    }

    public boolean getActivity ()
    {
        return activity;
    }

    public boolean activate (Location loc, player play, event [] eventlist)
    {
        int n = random.nextInt() % 100;
        
        boolean check = false; 
        if (n <= chance) {
            switch (id) {
                case 0:
                    check = dysentaryCon();
                    break;
                case 1:
                    check = dysentaryDie(play, eventlist);
                    break;
            }
            return check;
        }
        return false;
    }

    public void deactivate ()
    {
        activity = false;
    }

    public void affectPeople (int x)
    {
        people += x;
    }

    public void setChance (int x)
    {
        chance = x;
    }

    public boolean dysentaryCon ()
    {
        activity = true;
        people ++;
        return true;
    }

    public boolean dysentaryDie (player play, event [] eventList)
    {
        if (eventList [0].getActivity()) {
            play.death();

            eventList[0].affectPeople(-1);

            if (eventList[0].getPeople() == 0)
                eventList[0].deactivate();
            return true;
        }
        return false;
    }
}
