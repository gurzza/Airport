import java.util.*;


class OrderPlaneByEP implements Comparator<Plane> {
    @Override
    public int compare(Plane o1, Plane o2) {
        return o1.getEPoint().compareTo(o2.getEPoint());
    }
}

class OrderPlaneBySP implements Comparator<Plane> {
    @Override
    public int compare(Plane o1, Plane o2) {
        return o1.getSPoint().compareTo(o2.getSPoint());
    }
}

class OrderPlaneByNP implements Comparator<Plane> {
    @Override
    public int compare(Plane o1, Plane o2) {
        return Integer.compare(o1.getPNumber(), o2.getPNumber());
    }
}
public class Info {
    static ArrayList<Passenger> listPass = new ArrayList<>();
    static ArrayList<Plane> listPlane = new ArrayList<>();


    public static Plane checkRoute(int route){
        Plane p = null;

         for(int i = 0; i < listPlane.size(); i++){
             if(route == listPlane.get(i).getPNumber())
                 p = listPlane.get(i);
         }

         return p;
    }

    public static boolean isFlights() {
        return !listPlane.isEmpty();
    }

    public static void getInfo() {

        System.out.println("All available routes: ");
        for(Plane p : listPlane){
            System.out.println("Number: " + p.getPNumber() + "\n" +
                    "From: " + p.getSPoint() + "\n" +
                    "To: " + p.getEPoint() + "\n");
        }
    }
    public static void addFlight() {
        int pNumber;
        String sPoint, ePoint;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of plane: ");
        pNumber = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter the initial point: ");
        sPoint = sc.nextLine();

        //sc.nextLine();

        System.out.print("Enter the end point: ");
        ePoint = sc.nextLine();

        listPlane.add(new Plane(pNumber, sPoint, ePoint));

        System.out.println("Your flight has been successfully added!");
    }
    public static void bookFlight(){
        Scanner sc = new Scanner(System.in);
        int ord = -1;
        Passenger passenger;

        if(!isFlights()){
            System.out.println("Sorry... Right now you can't book a flight. Wait updates");
        }
        else {

            System.out.println("Enter your name: ");
            String name = sc.nextLine();

            while(ord == -1) {
                System.out.println("Enter: \n1 - order flights by initial point\n2 - order flights by end point\n3 - default");
                ord = sc.nextInt();

                switch (ord){
                    case 1:
                        Collections.sort(listPlane, new OrderPlaneBySP());
                        break;
                    case 2:
                        Collections.sort(listPlane, new OrderPlaneByEP());
                        break;
                    case 3:
                        Collections.sort(listPlane, new OrderPlaneByNP());
                        break;
                    default:
                        ord = -1;
                        System.out.println("ERROR! Try one more time...");
                }
            }
            getInfo();

            Boolean flagRoute = false;
            int route = -1;
            Plane p = null;

            while (!flagRoute) {
               System.out.println("Enter desirable route number: ");
               route = sc.nextInt();

               p = checkRoute(route);
               if (p == null) {
                   System.out.println("ERROR! No such route. Try one more time...");
                   continue;
               }
               flagRoute = true;
            }
            //passenger = new Passenger(name, route);
            listPass.add(new Passenger(name, route));
            p.bookPlace(name);
            p.getAvailPlaces();
       }

    }

    public static void cancelBook(){
        Scanner sc = new Scanner(System.in);
        String name = "";
        Passenger pass = null;

        while(pass == null) {
            System.out.println("Enter the name of passenger: ");
            name = sc.nextLine();

            for(Passenger p : listPass){
                if (p.getPassengerName().equals(name)){
                    pass = p;
                    break;
                }
            }
            if(pass == null){
                System.out.println("No such passenger! Try one more time...");
            }
        }

        int passFNumber = pass.getNFlight();
        Plane p;
        Iterator<Plane> itP = listPlane.iterator();
        while(itP.hasNext()){
            p = itP.next();
            if(p.getPNumber() == passFNumber){
                p.cancelPlace(name);
                System.out.println("Your reservation has been successfully canceled!");
                break;
            }
        }
    }


    public static void main(String[] args){

        int code = -1;
        Scanner scan = new Scanner(System.in);
        while(code != 0) {
            System.out.println("Enter the number of operation:");
            System.out.println("0 - Leave the program\n" +
                    "1 - Book a flight\n" +
                    "2 - Add flight\n"+
                    "3 - Cancel booking");
            code = scan.nextInt();

            switch (code) {
                case 0: System.out.print("Goodbye!"); return;
                case 1: bookFlight(); break;
                case 2: addFlight(); break;
                case 3: cancelBook(); break;
                default: System.out.println("No such operation!");
            }
        }
    }
}
