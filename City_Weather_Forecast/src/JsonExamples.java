import org.json.*;
import java.util.List;
import java.util.ArrayList;

public class JsonExamples {
    
    public static void main(String[] args) {
        // Example 1: Creating a JSONObject from scratch
        createJsonObject();
        
        // Example 2: Parsing JSON string
        parseJsonString();
        
        // Example 3: Working with JSONArray
        workWithJsonArray();
        
        // Example 4: Nested JSON objects
        nestedJsonExample();
        
        // Example 5: Converting Java objects to JSON
        javaObjectToJson();
        
        // Example 6: Error handling
        errorHandlingExample();
    }
    
    // Example 1: Creating JSONObject from scratch
    public static void createJsonObject() {
        System.out.println("=== Creating JSONObject ===");
        
        JSONObject person = new JSONObject();
        person.put("name", "John Doe");
        person.put("age", 30);
        person.put("city", "New York");
        person.put("isEmployed", true);
        person.put("salary", 75000.50);
        
        System.out.println(person.toString(2)); // Pretty print with indent
    }
    
    // Example 2: Parsing JSON string
    public static void parseJsonString() {
        System.out.println("\n=== Parsing JSON String ===");
        
        String jsonString = "{\"name\":\"Alice\",\"age\":25,\"hobbies\":[\"reading\",\"swimming\"]}";
        
        JSONObject obj = new JSONObject(jsonString);
        
        String name = obj.getString("name");
        int age = obj.getInt("age");
        JSONArray hobbies = obj.getJSONArray("hobbies");
        
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Hobbies: " + hobbies);
    }
    
    // Example 3: Working with JSONArray
    public static void workWithJsonArray() {
        System.out.println("\n=== Working with JSONArray ===");
        
        JSONArray users = new JSONArray();
        
        // Add objects to array
        JSONObject user1 = new JSONObject();
        user1.put("id", 1);
        user1.put("username", "john_doe");
        
        JSONObject user2 = new JSONObject();
        user2.put("id", 2);
        user2.put("username", "jane_smith");
        
        users.put(user1);
        users.put(user2);
        
        // Add primitive values
        JSONArray numbers = new JSONArray();
        numbers.put(1);
        numbers.put(2);
        numbers.put(3);
        
        System.out.println("Users: " + users.toString(2));
        System.out.println("Numbers: " + numbers);
        
        // Iterate through array
        System.out.println("\nIterating through users:");
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            System.out.println("User " + user.getInt("id") + ": " + user.getString("username"));
        }
    }
    
    // Example 4: Nested JSON objects
    public static void nestedJsonExample() {
        System.out.println("\n=== Nested JSON Objects ===");
        
        JSONObject company = new JSONObject();
        company.put("name", "Tech Corp");
        company.put("founded", 2010);
        
        // Nested address object
        JSONObject address = new JSONObject();
        address.put("street", "123 Main St");
        address.put("city", "San Francisco");
        address.put("zipCode", "94102");
        
        company.put("address", address);
        
        // Array of employees
        JSONArray employees = new JSONArray();
        
        JSONObject emp1 = new JSONObject();
        emp1.put("name", "Bob Wilson");
        emp1.put("position", "Developer");
        emp1.put("skills", new JSONArray().put("Java").put("Python").put("JavaScript"));
        
        JSONObject emp2 = new JSONObject();
        emp2.put("name", "Sarah Johnson");
        emp2.put("position", "Designer");
        emp2.put("skills", new JSONArray().put("Photoshop").put("Illustrator"));
        
        employees.put(emp1);
        employees.put(emp2);
        
        company.put("employees", employees);
        
        System.out.println(company.toString(2));
        
        // Accessing nested data
        String companyCity = company.getJSONObject("address").getString("city");
        String firstEmployeeName = company.getJSONArray("employees")
                                          .getJSONObject(0)
                                          .getString("name");
        
        System.out.println("\nCompany city: " + companyCity);
        System.out.println("First employee: " + firstEmployeeName);
    }
    
    // Example 5: Converting Java objects to JSON
    public static void javaObjectToJson() {
        System.out.println("\n=== Java Objects to JSON ===");
        
        // From List
        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("orange");
        
        JSONArray fruitsJson = new JSONArray(fruits);
        System.out.println("Fruits JSON: " + fruitsJson);
        
        // From Map-like structure using JSONObject constructor
        String mapJson = "{\"key1\":\"value1\",\"key2\":42,\"key3\":true}";
        JSONObject fromMap = new JSONObject(mapJson);
        System.out.println("From map: " + fromMap);
    }
    
    // Example 6: Error handling and safe access
    public static void errorHandlingExample() {
        System.out.println("\n=== Error Handling ===");
        
        String jsonString = "{\"name\":\"Test\",\"age\":null,\"active\":true}";
        JSONObject obj = new JSONObject(jsonString);
        
        // Safe way to check if key exists
        if (obj.has("name")) {
            System.out.println("Name: " + obj.getString("name"));
        }
        
        // Handle null values
        if (obj.isNull("age")) {
            System.out.println("Age is null");
        } else {
            System.out.println("Age: " + obj.getInt("age"));
        }
        
        // Using opt methods for safe access (returns null/default if key doesn't exist)
        String email = obj.optString("email", "No email provided");
        int score = obj.optInt("score", 0);
        boolean isActive = obj.optBoolean("active", false);
        
        System.out.println("Email: " + email);
        System.out.println("Score: " + score);
        System.out.println("Active: " + isActive);
        
        // Exception handling for malformed JSON
        try {
            String badJson = "{invalid json}";
            JSONObject badObj = new JSONObject(badJson);
        } catch (JSONException e) {
            System.out.println("JSON parsing error: " + e.getMessage());
        }
    }
}