package com.puppey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "currency_transaction")
public class CurrencyTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int currencyTransactionId;
    
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    
    @NotNull
    @Column(name = "amount_increased", columnDefinition="int default 0")
    private int increase;
    
    @NotNull
    @Column(name = "amount_decreased", columnDefinition="int default 0")
    private int decrease;
    
    @NotEmpty
    @Column(name = "reason")
    private String reason;
    
    @NotNull
    @Column(name = "creation", updatable = false)
    private Integer creation = (int) (System.currentTimeMillis() / 1000);
    


    public int getCurrencyTransactionId() {
        return currencyTransactionId;
    }


    public void setCurrencyTransactionId(int currencyTransactionId) {
        this.currencyTransactionId = currencyTransactionId;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public int getIncrease() {
        return increase;
    }


    public void setIncrease(int increase) {
        this.increase = increase;
    }


    public int getDecrease() {
        return decrease;
    }


    public void setDecrease(int decrease) {
        this.decrease = decrease;
    }


    public String getReason() {
        return reason;
    }


    public void setReason(String reason) {
        this.reason = reason;
    }


    public Integer getCreation() {
        return creation;
    }


    public void setCreation(Integer creation) {
        this.creation = creation;
    }
    
    
    
}
