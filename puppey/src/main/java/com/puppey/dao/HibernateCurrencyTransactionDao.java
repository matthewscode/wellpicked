package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.CurrencyTransaction;
import com.puppey.domain.User;

@Repository("currencyTransactionDao")
public class HibernateCurrencyTransactionDao implements CurrencyTransactionDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTransaction(CurrencyTransaction currencyTransaction) {
        sessionFactory.getCurrentSession().save(currencyTransaction);     
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CurrencyTransaction> getTransactionsByUser(User user) {
        return sessionFactory.getCurrentSession().createCriteria(CurrencyTransaction.class).add(Restrictions.eq("user", user)).list();
    }

}
