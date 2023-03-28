public class Location {
   private int distance=0;
   private String name;
   public Location(int distance, String name){
       this.distance=distance;
       this.name=name;
   }

    public int getDistance() {
       distance+=10;
        return distance;
    }
    public String getName() {
       int setDistance=getDistance()+10;
        if (setDistance == 120) {
            name = "Fort Leavenworth, Kansas";
        }
        else if (setDistance == 340) {
            name = "Fort Kearney, Nebraska";
        }
        else if(setDistance == 420) {
            name="Chimney Rock";
        }
        else if(setDistance == 500) {
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

