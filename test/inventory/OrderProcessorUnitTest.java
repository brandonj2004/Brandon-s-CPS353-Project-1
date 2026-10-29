package inventory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderProcessorUnitTest {

    @Test
    public void testProcessOrderInStock() {
        InventoryService inventoryService = mock(InventoryService.class);

        when(inventoryService.isInStock("item1", 2)).thenReturn(true);

        OrderProcessor processor = new OrderProcessor(inventoryService);

        boolean result = processor.processOrder("item1", 2);

        assertTrue(result);
        verify(inventoryService).reserveItem("item1", 2);
    }

    @Test
    public void testProcessOrderOutOfStock() {
        InventoryService inventoryService = mock(InventoryService.class);

        when(inventoryService.isInStock("item1", 2)).thenReturn(false);

        OrderProcessor processor = new OrderProcessor(inventoryService);

        boolean result = processor.processOrder("item1", 2);

        assertFalse(result);
        verify(inventoryService, never()).reserveItem("item1", 2);
    }
}
