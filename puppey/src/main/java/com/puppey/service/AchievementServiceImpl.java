package com.puppey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.dao.AchievementDao;
import com.puppey.domain.Achievement;
import com.puppey.domain.Artist;
import com.puppey.domain.User;
import com.puppey.util.Utility;

@Service("achievementService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AchievementServiceImpl implements AchievementService {
    
    @Autowired
    private AchievementDao achievementDao;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void createAchievement(Achievement achievement) {
        achievement.setAchievementSlug(Utility.slugify(achievement.getAchievementName()));
        achievementDao.createAchievement(achievement);
    }

    @Override
    @Transactional
    public List<Achievement> getAllAchievements() {
        return achievementDao.getAllAchievements();
    }

    @Override
    @Transactional
    public Achievement getAchievementById(int achievementId) {
        return achievementDao.getAchievementById(achievementId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void updateAchievement(Achievement achievement) {
        achievement.setAchievementSlug(Utility.slugify(achievement.getAchievementName()));
        achievementDao.updateAchievement(achievement);
        
    }
    

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void changeUserAvatar(int achievementId, int userId) {
        User user = userService.getUserById(userId);
        for(Achievement au : user.getAchievements()){
            if(au.getAchievementId() == achievementId){
                user.setAvatarName(au.getAchievementSlug());
            }
        }
        
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void scoreAchievement(User user, Achievement achievement){
        user.setCurrency(user.getCurrency() + achievement.getReward());
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void userAddAchievement(User user, Achievement achievement) {
        user.getAchievements().add(achievement);
        currencyService.increaseUserBalance(user, achievement.getReward(), ("Achievement: " + achievement.getAchievementId() + " " + achievement.getAchievementName()));

    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void addArtist(Artist artist){
    	
    }

}
