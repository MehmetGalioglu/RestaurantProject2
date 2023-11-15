import models.Person;
import models.Restaurant;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Utilities {

    public static void getMinimumAmountFor( String restaurantName,Map<String, Restaurant> restaurantMap){
        boolean running = true;
        while (running){
            if (restaurantMap.containsKey(restaurantName)){
                System.out.println(restaurantName + " requests a minimum order of "
                        + restaurantMap.get(restaurantName).getMinimumAmount() + "TL!");
                running = false;
            }
            else System.out.println("Invalid restaurant name!"); return;
        }
    }
    public static int getTotalBudget(List<String> guestName, Map<String, Person> peopleMap) {
        int totalBudget = 0;
        for (String name : guestName) {
            totalBudget += peopleMap.get(name).getBudget();
        }
        return totalBudget;
    }

    public static boolean minimumAmountCheck(int totalBudget, int minimumAmountOfOrder){
        return totalBudget < minimumAmountOfOrder;
    }

    public static void availableRestaurantOptions(int totalBudget, Map<String,Restaurant> restaurantMap){
        for (Restaurant restaurant : restaurantMap.values()){
            if (totalBudget >= restaurant.getMinimumAmount())
                System.out.println(restaurant.getName());
        }

    }


    //Bu method'ta kisilerin butcesi istenen restorana girmeye yeterli degilse
    // yeterli butceyi saglamak icin kimleri davet edebileceklerini sunmak istedim.
    // Ama her seferinde else kismina atliyor. Onerebilecegi kisiler olmasina ragmen if calismiyor. Bir tek burada sorun yasadim.
    public static void possibleGuestCombinations(
            String restaurantName,
            int totalBudget,
            Map<String, Restaurant> restaurantMap,
            Map<String,Person> peopleMap,
            List<String> guestNames
    ){
        int gapInBudget = restaurantMap.get(restaurantName).getMinimumAmount() - totalBudget;
        for (Person person : peopleMap.values()){
            System.out.println(person.getName());
            if (!guestNames.contains(person.getName()) && person.getBudget() >= gapInBudget)
                System.out.println("You can invite "+person.getName());
            else System.out.println("There is no someone with sufficient budget in the list"); return;
        }

    }
}

