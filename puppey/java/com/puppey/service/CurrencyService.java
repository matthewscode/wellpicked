package com.puppey.service;

import java.util.List;

import com.puppey.domain.CurrencyTransaction;
import com.puppey.domain.User;

public interface CurrencyService {
    
    public int getUserBalance(User user);
    
    public void increaseUserBalance(User user, int amount, String reason);
    
    public void decreaseUserBalance(User user, int amount, String reason);

    public List<CurrencyTransaction> getTransactionsByUser(int userId);
}
