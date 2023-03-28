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
    public String getName() {
        return name;
    }
    public void setName(String name){
       this.name=name;
    }
}
