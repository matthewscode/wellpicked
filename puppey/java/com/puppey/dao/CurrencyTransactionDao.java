package com.puppey.dao;

import java.util.List;

import com.puppey.domain.CurrencyTransaction;
import com.puppey.domain.User;


public interface CurrencyTransactionDao {
    
    public void addTransaction(CurrencyTransaction currencyTransaction);

    public List<CurrencyTransaction> getTransactionsByUser(User user);
    
}
