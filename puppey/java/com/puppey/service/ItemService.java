package com.puppey.service;

import java.util.List;

import com.puppey.domain.Item;

public interface ItemService {

    void addItem(Item newItem);

	List<Item> getAllItems();

	Item getItemById(int itemId);
}
