public class event {
    private String name;
    private int id;
    private int people = 0;
    private boolean activity = false;
    private int chance;

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

    public void activate ()
    {
        activity = true;
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
}
