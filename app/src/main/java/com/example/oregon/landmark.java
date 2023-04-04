public class landmark {
    private int location;
    private String name;
    private int type;

    public landmark (int loc, int ty, String nam)
    {
        location = loc;
        name = nam;
        type = ty;
    }
    //@returns location to game display
    public int getLocation ()
    {
        return location;
    }
    //@returns name of landmark
    public String getName ()
    {
        return name;
    }
    //@returns location type
    public int getType ()
    {
        return type;
    }

    public String getTypeName ()
    {
        if (type == 0) return "fort";
        else if (type == 1) return "river";
        return "misc";
    }
}
