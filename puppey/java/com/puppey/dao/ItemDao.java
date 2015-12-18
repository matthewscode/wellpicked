package com.puppey.dao;

import java.util.List;

import com.puppey.domain.Item;

public interface ItemDao {

    void addItem(Item newItem);

	List<Item> getAllItems();

	Item getItemById(int itemId);

}
