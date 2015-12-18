package com.puppey.service;

import java.util.List;

import com.puppey.domain.Achievement;
import com.puppey.domain.Artist;
import com.puppey.domain.User;

public interface AchievementService {

    public void createAchievement(Achievement achievement);

    public List<Achievement> getAllAchievements();

    public Achievement getAchievementById(int achievementId);

    public void updateAchievement(Achievement achievement);

    public void changeUserAvatar(int achievementId, int userId);

    void scoreAchievement(User user, Achievement achievement);

    public void userAddAchievement(User user, Achievement achievement);

	void addArtist(Artist artist);
    
    
}
