package inventory;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class OrderProcessorIntegrationTest {

    static class InMemoryInventoryService implements InventoryService {

        private Map<String, Integer> inventory = new HashMap<>();

        public void addInventory(String itemId, int quantity) {
            inventory.put(itemId, quantity);
        }

        @Override
        public boolean isInStock(String itemId, int quantity) {
            int available = inventory.getOrDefault(itemId, 0);
            return available >= quantity;
        }

        @Override
        public void reserveItem(String itemId, int quantity) {
            int available = inventory.getOrDefault(itemId, 0);
            inventory.put(itemId, available - quantity);
        }

        public int getQuantity(String itemId) {
            return inventory.getOrDefault(itemId, 0);
        }
    }

    @Test
    public void testOrderProcessorWithRealInventory() {
        InMemoryInventoryService inventory = new InMemoryInventoryService();

        inventory.addInventory("item1", 10);

        OrderProcessor processor = new OrderProcessor(inventory);

        boolean result = processor.processOrder("item1", 3);

        assertTrue(result);
        assertEquals(7, inventory.getQuantity("item1"));
    }
}
