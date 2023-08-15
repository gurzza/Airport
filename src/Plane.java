import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Plane {
    private int pNumber; // plane number
    private String sPoint;
    private String ePoint;
    HashMap<String, Integer> passPlace = new HashMap<String, Integer>();
    byte[][] availPlace = new byte[5][5];

    Plane(int pNumber, String sPoint, String ePoint){
        this.pNumber = pNumber;
        this.sPoint = sPoint;
        this.ePoint = ePoint;

        for(int i = 0; i < availPlace.length; i++){
            availPlace[i][0] = (byte) i;
            availPlace[0][i] = (byte) i;
        }

        for(int i = 1; i < availPlace.length; i++) {
            availPlace[i][i] = 1;
            for (int j = 1; j < i; j++) {
                availPlace[i][j] = 1;
                availPlace[j][i] = 1;
            }
        }
    }

    public void getAvailPlaces(){
        System.out.println("1 - available, 2 - booked:");
        for (int i = 0; i < availPlace.length; i++) {
            for (int j = 0; j < availPlace[i].length; j++) {
                System.out.print(String.valueOf(availPlace[i][j]) + '\t');
            }
            System.out.println();
        }
    }

    public void bookPlace(String name){
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        int x, y;

        while(!flag) {
            getAvailPlaces();
            System.out.println("Enter desirable place (row and column): ");
            x = sc.nextInt();
            y = sc.nextInt();
            //
            if (x >= availPlace.length | y >= availPlace.length | x < 1 | y < 1){
                System.out.println("ERROR! Be attentive with numbers! Try one more time...");
            } else if(availPlace[x][y] == 0) {
                System.out.println("ERROR! This place has already booked! Try one more time...");
            } else {
                availPlace[x][y] = 0;
                flag = true;
                System.out.println("Place " + x + y + " has successfully booked");
                passPlace.put(name, x*10+y);
            }
        }
    }

    public void cancelPlace(String name){
        int x,y;
        int place = passPlace.get(name);

        x = place / 10;
        y = place % 10;
        availPlace[x][y] = 1;

        passPlace.remove(name);
    }

    public String getEPoint() { return this.ePoint; }
    public String getSPoint() { return this.sPoint; }
    public int getPNumber() { return this.pNumber; }



}
