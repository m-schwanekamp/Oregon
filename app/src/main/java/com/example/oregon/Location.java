public class Location {
   private int distance=0;
   private String name;
   public Location(int distance, String name){
       this.distance=distance;
       this.name=name;
   }

    public int getDistance() {
       distance+=20;
        return distance;
    }
    public String getName() {
        if (getDistance() == 120) {
            name = "Fort Leavenworth, Kansas";
        }
        else if (getDistance() == 340) {
            name = "Fort Kearney, Nebraska";
        }
        else if(getDistance() == 420) {
            name="Chimney Rock";
        }
        else if(getDistance() == 500) {
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
