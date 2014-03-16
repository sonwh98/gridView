package com.datayumyum.pos;

import org.junit.Test
import static org.junit.Assert.*;

/**
 * Created by sto on 3/16/14.
 */
public class ItemRepositoryTests {

    @Test
    void testGroupItemByCategory() {
        String json = new File("posGridView/src/main/res/raw/catalog.json").text
        ItemRepository itemRepository = new ItemRepository(json);
        List<Item> itemList = itemRepository.groupItemByCategory("Entrees");
        assertNotNull itemList;
        assertEquals 8, itemList.size()
    }
}
