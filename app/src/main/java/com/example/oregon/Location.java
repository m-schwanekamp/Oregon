/**
 Name of File: Location.java
 Date of Writing: 4/4/2023
 Name: Mark Mintzlaff
 Description: This class represents the distance traveled part of the game. When the player wants to travel the trail, distance is incremented. This class also tells the user when they are at a landmark or fort to go shopping
 **/
public class Location {
    //instance variables
    private int distance=0;
    private String name;

    //constructor
    public Location(int distance, String name){
        this.distance=distance;
        this.name=name;
    }

    //default constructor
    public Location(){
        this.distance=0;
        this.name="";
    }

    //getter method for distance
    public int getDistance() {
        return distance;
    }

    /*inc() method increments the distance whenever it is called. In the case of the game, it is incremented by 20
    @param integer x (20 miles)
    @return void
    */
    public void inc (int x) {
        distance += x;
    }

    /*
    getName function gets the name of location they are at if the distance is at one of the given points
    @param none
    @return name of location
     */
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
    //setter method for name
    public void setName(String name){
        this.name=name;
    }
}
