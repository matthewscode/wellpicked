package com.puppey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.dao.CurrencyTransactionDao;
import com.puppey.dao.UserDao;
import com.puppey.domain.CurrencyTransaction;
import com.puppey.domain.User;

@Service("currencyService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CurrencyServiceImpl implements CurrencyService {
    
    @Autowired
    UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CurrencyTransactionDao currencyTransactionDao;

    @Override
    public int getUserBalance(User user) {
        return user.getCurrency();
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void increaseUserBalance(User user, int amount, String reason){
        user.setCurrency(user.getCurrency() + amount);
        CurrencyTransaction currencyTransaction = new CurrencyTransaction();
        currencyTransaction.setIncrease(amount);
        currencyTransaction.setUser(user);
        currencyTransaction.setReason(reason);
        userDao.updateUser(user);
        currencyTransactionDao.addTransaction(currencyTransaction);
     
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void decreaseUserBalance(User user, int amount, String reason){
        user.setCurrency(user.getCurrency() - amount);
        CurrencyTransaction currencyTransaction = new CurrencyTransaction();
        currencyTransaction.setDecrease(amount);
        currencyTransaction.setUser(user);
        currencyTransaction.setReason(reason);
        userDao.updateUser(user);
        currencyTransactionDao.addTransaction(currencyTransaction);
    }

    @Override
    @Transactional
    public List<CurrencyTransaction> getTransactionsByUser(int userId) {   
       return currencyTransactionDao.getTransactionsByUser(userService.getUserById(userId));
    }
    
    
}
