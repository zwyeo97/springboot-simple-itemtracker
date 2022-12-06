package com.fdmgroup.ItemsTracker.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fdmgroup.ItemsTracker.model.Item;
import com.fdmgroup.ItemsTracker.repositories.ItemRepo;

@SpringBootTest
public class ItemServiceTest {
	@Autowired
	ItemService itemService;
	
	@MockBean
	ItemRepo itemRepo;
	
	@Test
	public void test_CRUD_operations() {
		Item item = new Item("Watch", "Gucci", 420.0);
		
		itemService.addNewItem(item);
		verify(itemRepo).save(item);
		
		item.setDescription("New gucci");
		itemService.updateItemInfo(item);
		verify(itemRepo, times(2)).save(item);
		
		itemService.deleteItem(item);
		verify(itemRepo).delete(item);
		
		
	}
}
