
import java.util.*;
public class E_commerce_Inventory_Manager {
        private Map<String, Integer> stock = new HashMap<>();
        private Map<String, LinkedHashMap<Integer, Boolean>> waitingList = new HashMap<>();

        public E_commerce_Inventory_Manager() {
            stock.put("IPHONE15_256GB", 100);
            waitingList.put("IPHONE15_256GB", new LinkedHashMap<>());
        }

        public synchronized String checkStock(String productId) {
            int count = stock.getOrDefault(productId, 0);
            return count + " units available";
        }

        public synchronized String purchaseItem(String productId, int userId) {

            int currentStock = stock.getOrDefault(productId, 0);

            if (currentStock > 0) {
                stock.put(productId, currentStock - 1);
                return "Success, " + (currentStock - 1) + " units remaining";
            } else {
                LinkedHashMap<Integer, Boolean> queue = waitingList.get(productId);
                queue.put(userId, true);
                int position = queue.size();
                return "Added to waiting list, position #" + position;
            }
        }

        public static void main(String[] args) {

            E_commerce_Inventory_Manager manager = new E_commerce_Inventory_Manager();

            System.out.println(manager.checkStock("IPHONE15_256GB"));

            System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
            System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));


            for (int i = 0; i < 98; i++) {
                manager.purchaseItem("IPHONE15_256GB", i);
            }

            System.out.println(manager.purchaseItem("IPHONE15_256GB", 99999));
        }

}
