
public class player {
    private String name;
    private int money = 1600;
    private int family = 5;
    private inventory inv = new inventory();

    public player (String inName)
    {
        name = inName;
    }

    public String displayName ()
    {
        return name;
    }

    public String displayInvText ()
    {
        String create = "oxen: " + inv.displayOxen() + "\nfood: " + inv.displayFood() + "\nclothing: " + inv.displayClothing() + "\nparts: " + inv.displayParts() + "\nAmmunition: " + inv.displayAmmo();
        return create;
    }

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

    public int displayInv (int catagorize)
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

    public int displayFamily ()
    {
        return family;
    }

    public int displayMoney ()
    {
        return money;
    }

    public void spend (int x)
    {
        money -= x;
    }

    public void death ()
    {
        family--;
    }

    public void eat ()
    {
        inv.changeFood(family * -5);
    }
}
