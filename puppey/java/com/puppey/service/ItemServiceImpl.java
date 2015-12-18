package com.puppey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.dao.ItemDao;
import com.puppey.domain.Item;
import com.puppey.util.Utility;

@Service("itemService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemServiceImpl implements ItemService{
    
    @Autowired
    private ItemDao itemDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addItem(Item newItem) {
       newItem.setSlug(Utility.slugify(newItem.getItemName()));
       itemDao.addItem(newItem); 
    }

	@Override
	@Transactional
	public List<Item> getAllItems() {
		return itemDao.getAllItems();
	}

	@Override
	@Transactional
	public Item getItemById(int itemId) {
		return itemDao.getItemById(itemId);
	}
   

}
