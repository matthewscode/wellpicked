package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.Item;

@Repository("itemDao")
public class HibernateItemDao implements ItemDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void addItem(Item newItem) {
        sessionFactory.getCurrentSession().save(newItem);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAllItems() {
		return sessionFactory.getCurrentSession().createCriteria(Item.class).list();
	}

	@Override
	public Item getItemById(int itemId) {
		return (Item) sessionFactory.getCurrentSession().get(Item.class, itemId);
	}

}
