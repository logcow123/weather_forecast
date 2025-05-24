import java.math.BigDecimal;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;

// import org.json.JSONObject;

public class App {
    public static void main(String[] args)
    {
        Boolean keepGoing = true;
        Scanner scanner = new Scanner(System.in);

        while(keepGoing){
            System.out.print("Enter a City\n(Enter 'Quit' to Quit):\n");
            String city = scanner.nextLine();
            city = city.trim();
            city = city.replace(' ', '+');

            if(city.toLowerCase().equals("quit"))
            {
                keepGoing = false;
            }else
            {
                ArrayList<BigDecimal> longNlat = City_Locator.getLongAndLat(city);
                JSONObject data = WeatherForcast.getWeatherData(longNlat);

                System.out.println("=========================================");

                WeatherForcast.DisplayCurrentData(data);
                System.out.println("");
            } 
        }
        scanner.close();
    }

    public static void ListKeysAndValues(JSONObject json) {
        
        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = json.get(key);
            System.out.println("Key: " + key + ", Value: " + value + ", Type: " + value.getClass().getSimpleName());
        }
        System.out.println();
    }
}
