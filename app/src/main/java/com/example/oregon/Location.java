public class Location {
    private int distance=0;
    private String name;
    public Location(int distance, String name){
        this.distance=distance;
        this.name=name;
    }

    public int getDistance() {
        return distance;
    }

    public void inc (int x) {
        distance += x;
    }
    public String getName() {
        if (distance == 120) {
            name = "Fort Leavenworth, Kansas";
        }
        else if (distance == 340) {
            name = "Fort Kearney, Nebraska";
        }
        else if(distance == 420) {
            name="Chimney Rock";
        }
        else if(distance == 500) {
            name="Ash Hollow, Nebraska";
        }
        else{
            name="";
        }
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}
