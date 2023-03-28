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

    public int getLocation ()
    {
        return location;
    }

    public String getName ()
    {
        return name;
    }

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
