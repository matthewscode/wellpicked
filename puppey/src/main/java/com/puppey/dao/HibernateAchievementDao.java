package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.Achievement;


@Repository("achievementDao")
public class HibernateAchievementDao implements AchievementDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createAchievement(Achievement achievement) {
        sessionFactory.getCurrentSession().save(achievement);     
        
    }
    
    @Override
    public void updateAchievement(Achievement achievement) {
        sessionFactory.getCurrentSession().update(achievement);     
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Achievement> getAllAchievements() {
        return sessionFactory.getCurrentSession().createCriteria(Achievement.class).list();
    }

    @Override
    public Achievement getAchievementById(int achievementId) {
        return (Achievement)sessionFactory.getCurrentSession().get(Achievement.class, achievementId);
    }
    
}
