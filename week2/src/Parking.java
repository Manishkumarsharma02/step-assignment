import java.util.*;

public class Parking {

    static String[] spots = new String[10];

    static int hash(String plate){
        return Math.abs(plate.hashCode()) % spots.length;
    }

    static void parkVehicle(String plate){

        int index = hash(plate);

        while(spots[index]!=null){
            index = (index+1) % spots.length;
        }

        spots[index] = plate;

        System.out.println("Parked at spot "+index);
    }

    static void exitVehicle(String plate){

        for(int i=0;i<spots.length;i++){

            if(plate.equals(spots[i])){
                spots[i] = null;
                System.out.println("Vehicle exited from "+i);
            }
        }
    }

    public static void main(String[] args){

        parkVehicle("ABC123");
        parkVehicle("XYZ999");

        exitVehicle("ABC123");
    }
}