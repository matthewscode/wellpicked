package com.puppey.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;









//import com.puppey.achievement.AchievementQueue;
import com.puppey.dao.UserAuthorityDao;
import com.puppey.dao.UserDao;
import com.puppey.domain.Achievement;
import com.puppey.domain.Team;
import com.puppey.domain.User;
import com.puppey.util.Message;
import com.puppey.util.SiteUser;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthorityDao userAuthorityDao;
    @Autowired
    private TeamService teamService;
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private MessageService messageService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
        userAuthorityDao.addUser(user);
        //achievementQueue.addToUserQueue(user.getUserId());
        return user;
    }

    @Override
    public String encodePassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    @Transactional
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    @Transactional
    public boolean userNameExists(String username) {
        return userDao.userNameExists(username);
    }

    @Override
    @Transactional
    public boolean userEmailExists(String email) {
        return userDao.userEmailExists(email);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateLastLogin(int userId) {
        User user = userDao.getUserById(userId);
        user.setLoginDate((int) (System.currentTimeMillis() / 1000));
        userDao.updateLastLogin(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(User user) {
        userDao.updateUser(user);

    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Transactional
    @Override
    public User getUserBySteamId(String steamId) {
        return userDao.getUserBySteamId(steamId);
    }



    @Transactional
    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    @Transactional
    public List<GrantedAuthority> getAuthorities(User user) {
        List<String> userAuthorities = userDao.getUserAuthorities(user);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (String au : userAuthorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(au));
        }

        return grantedAuthorities;
    }

    @Override
    @Transactional
    public User getUserByIdWithAchievements(int userId) {
        User user = userDao.getUserById(userId);
        user.getAchievements().size();
        return user;
    }

    @Override
    @Transactional
    public User getUserByIdWithLists(int userId) {
        User user = userDao.getUserById(userId);
        user.getAchievements().size();
        user.getGroups().size();
        user.getFavoriteTeams().size();
        return user;
    }

    @Override
    @Transactional
    public void addFavoriteTeam(User user, Team team) {
        User userNewFavorite = user;
        List<Team> favoriteTeams = userNewFavorite.getFavoriteTeams();
        favoriteTeams.add(team);
        userNewFavorite.setFavoriteTeams(favoriteTeams);
        updateUser(userNewFavorite); 
        System.out.println(userNewFavorite.getFavoriteTeams().size());
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUserAchievement(User user, Achievement achievement) {
//        User userachievements = user;
        achievement.getUsers().add(user);
        achievementService.updateAchievement(achievement);
        List<Achievement> achievements = user.getAchievements();
        achievements.add(achievement);
        user.setAchievements(achievements);
        //updateUser(user); 
        System.out.println(user.getAchievements().size());
    }

    @Override
    @Transactional
    public User getUserWithFavoriteTeamsById(int userId) {
        User user = userDao.getUserById(userId);
        user.getFavoriteTeams().size();
        return user;
    }

	@Override
	@Transactional
	public List<User> getUsersByTopScores(int amount) {
		return userDao.getUsersByTopScores(amount);
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeFavoriteTeam(User currentUser, Team team) {
        currentUser.getFavoriteTeams().remove(team);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<JSONObject> getFavoriteTeamList(User user) {
        List<JSONObject> finalJson = new ArrayList<>();

        for(Team team : user.getFavoriteTeams()){
            JSONObject teamAndChannel = new JSONObject();
            JSONObject channelsJsonObject = new JSONObject();
            String url = "http://api.twitch.tv/api/team/"+ team.getTwitchTag() +"/live_channels.json";
            JSONObject teamJsonObject = new JSONObject();
            JSONArray channelsArray = new JSONArray();
        
            try{
            String channelJson = IOUtils.toString(new URL(url).openStream(), "UTF-8");
            channelsJsonObject = (JSONObject) JSONValue.parseWithException(channelJson);
            channelsArray = (JSONArray)channelsJsonObject.get("channels");
            
            teamJsonObject.put("id", team.getTeamId());
            teamJsonObject.put("channels", channelsArray);
            teamAndChannel.put("team", teamJsonObject);
            finalJson.add(teamAndChannel);
            } catch(Exception e){
            }

        }
        return finalJson;  
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Message addUserFavoriteTeam(int teamId) {
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = getUserById(su.getUserId());
        Team team = teamService.getTeam(teamId);
        int i = 0;
        
        for(Team teamToCheck : user.getFavoriteTeams()){
            if(teamToCheck.getTeamId() == team.getTeamId()){
                i++;
            }
        } 
        
        if(i == 0){
            teamService.addUser(user, team);
            return messageService.isSuccessful(true);
        }
        
        return messageService.isSuccessful(false);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Message removeUserFavoriteTeam(int teamId) {
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = getUserWithFavoriteTeamsById(su.getUserId());
        Team team = teamService.getTeam(teamId);
        int i = 0;
        for(Team teamToCheck : user.getFavoriteTeams()){
            if(teamToCheck.getTeamId() == team.getTeamId()){
                i++;
            }
        }
        
        if(i > 0){
            teamService.removeUser(user, team);
            return messageService.isSuccessful(true);
        }
        
        return messageService.isSuccessful(true);
    }

}
