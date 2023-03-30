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

    public void activate (Location loc, player play)
    {
        int n = random.nextInt() % 100;
        
        if (n <= chance) {
            switch (id) {
                case 0:
                    dysentaryCon();
                    break;
                case 1:
                    dysentaryDie(play);
                    break;
            }
        }
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
    
    public void dysentaryCon ()
    {
        activity = true;
        people ++;
    }
    
    public void dysentaryDie (player play)
    {
        play.death();
    }
}
