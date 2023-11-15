import models.Person;
import models.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<String, Person> peopleMap = Map.of(
            "AHMET", new Person("AHMET", 17, 100),
            "AYSE", new Person("AYSE", 28, 300),
            "MEHMET", new Person("MEHMET", 32, 150),
            "SENA", new Person("SENA", 30, 250),
            "TUNA", new Person("TUNA",41,500),
            "MERVE", new Person("MERVE",35,400)
    );

    static Map<String, Restaurant> restaurantMap = Map.of(
            "ARKABAHCE", new Restaurant("Arkabahce",300),
            "KEBAP49", new Restaurant("Kebap49", 800),
            "KUCUKİTALYA", new Restaurant("KucukItalya",  700),
            "BİGCHEF", new Restaurant("BigChef", 1000),
            "BURGERDİNHO", new Restaurant("Burgerdinho",500),
            "TİRİLYE", new Restaurant("Tirilye",1500)
    );
    // Alistiktan sonra map ile calismak listten cok daha kolay geldi. Bu ikinci projede restoranlari map olarak olusturdum.

    public static void main(String[] args) {
        List<String> guestNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done){
            System.out.println("Enter a name to the list (Enter done when it's done) : ");
            String entry = scanner.nextLine().toUpperCase();
            done = entry.contains("DONE");
            if (!done && peopleMap.containsKey(entry)){
                guestNames.add(entry);
            }
            else if (!done && !peopleMap.containsKey(entry)){
                System.out.println("Invalid name!"); return;
                //Yanlis isim girildiginde invalid name yazisindan sonra iteration'in kalan kismini atlayip
                // while loop'un ilk kismina donmesini istedim ama yapamadim. Yanlis isim girildiginde sistem kapaniyor bu durumda.
                // User icin guzel bir deneyim degil malesef.
            }
            else done = true;
        }

        for (Restaurant restaurant : restaurantMap.values())
            System.out.println(restaurant.getName());
        System.out.println("Which restaurant do you want to go? : ");
        String restaurantName = scanner.nextLine().toUpperCase();


        Utilities.getMinimumAmountFor(restaurantName,restaurantMap);
        int totalBudget = Utilities.getTotalBudget(guestNames,peopleMap);
        System.out.println("Your total budget is "+totalBudget+"TL");

        boolean availability = Utilities.minimumAmountCheck(totalBudget, restaurantMap.get(restaurantName).getMinimumAmount());
        if (!availability) System.out.println("Welcome to "+restaurantName);
        if (availability){
            System.out.println("Insufficient budget for " +restaurantName);
            System.out.println("Would you like to see other options y/n? : ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("y")) {
                System.out.println("See you next time!"); return;
            }
            else{
                System.out.println("You can go to the following restaurants : ");
                Utilities.availableRestaurantOptions(totalBudget,restaurantMap);
                System.out.println("Or you can invite the following people :)");
                Utilities.possibleGuestCombinations(restaurantName,totalBudget,restaurantMap,peopleMap,guestNames);
            }
        }
        scanner.close();

    }
}