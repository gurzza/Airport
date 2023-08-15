public class Passenger {
    private String name;
    private int nFlight;

    public Passenger(String name){
        this.name = name;
    }

    public Passenger(String name, int nFlight){
        this(name);
        this.nFlight = nFlight;
    }

    public String getPassengerName(){
        return this.name;
    }
    public int getNFlight(){
        return this.nFlight;
    }

    public String toString(){
        return "Passenger name: " + this.name + "\n" + "Flight number: " + this.nFlight;
    }
    public static void main(String[] args) {

    }
}