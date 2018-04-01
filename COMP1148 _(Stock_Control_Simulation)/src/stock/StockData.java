package stock;

import java.util.HashMap;
import java.util.Map;

public class StockData {

    public static class Item {

        Item(String n, double p, int q, String f) {
            name = n;
            price = p;
            quantity = q;
            filename = f;
        }
        Item(String n, double p, int q) {
            name = n;
            price = p;
            quantity = q;
            filename = "noImage.jpg";
        }

        // get methods
        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
        
        public String getFilename() {
            return filename;
        }

        // instance variables 
        private final String name;
        private final double price;
        private int quantity;
        private String filename;
    }
    public final static Map<String, Item> stock = new HashMap();
    
    public static Map<String, Item> getStock() {
       return stock;
     }
     
    public static String getName(String key) {
        Item item = stock.get(key);
        if (item == null) {
            return null; // null means no such item
        } else {
            return item.getName();
        }
    }

    public static double getPrice(String key) {
        Item item = stock.get(key);
        if (item == null) {
            return -1.0; // negative price means no such item
        } else {
            return item.getPrice();
        }
    }

    public static int getQuantity(String key) {
        Item item = stock.get(key);
        if (item == null) {
            return -1; // negative quantity means no such item
        } else {
            return item.getQuantity();
        }
    }
    public static String getFilename(String key) {
        Item item = stock.get(key);
        if (item == null) {
            return null; // null means no such item
        } else {
            return item.getFilename();
        }
    }

	// update stock levels
    // extra is +ve if adding stock
    // extra is -ve if selling stock
    public static void update(String key, int extra) {
        Item item = stock.get(key);
        if (item != null) {
            item.quantity += extra;
        }
    }

    public static void close() {
        // Does nothing for this static version.
        // Write a statement to close the database when you are using one
    }

}
