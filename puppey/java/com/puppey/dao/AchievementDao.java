package com.puppey.dao;

import java.util.List;

import com.puppey.domain.Achievement;

public interface AchievementDao {
    
    public void createAchievement(Achievement achievement);

    public List<Achievement> getAllAchievements();

    public Achievement getAchievementById(int achievementId);

    public void updateAchievement(Achievement achievement);
    
}
