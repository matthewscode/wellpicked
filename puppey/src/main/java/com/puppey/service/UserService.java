package com.puppey.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.security.core.GrantedAuthority;

import com.puppey.domain.Achievement;
import com.puppey.domain.Team;
import com.puppey.domain.User;
import com.puppey.util.Message;

public interface UserService {

    public User addUser(User user);

    public User getUserByName(String userName);

    public boolean userNameExists(String username);

    public boolean userEmailExists(String email);

    public void updateLastLogin(int userId);

    public String encodePassword(String password);

    public void updateUser(User user);

    public User getUserByEmail(String email);

    public User getUserBySteamId(String steamId);

    public User getUserById(int userId);

    public List<GrantedAuthority> getAuthorities(User user);

    public User getUserByIdWithAchievements(int userId);

    public User getUserByIdWithLists(int userId);

    public void addFavoriteTeam(User user, Team team);

    public User getUserWithFavoriteTeamsById(int userId);

	public List<User> getUsersByTopScores(int amount);

    public void removeFavoriteTeam(User currentUser, Team team);

    public List<JSONObject> getFavoriteTeamList(User user);

    void addUserAchievement(User user, Achievement achievement);

    public Message addUserFavoriteTeam(int teamId);

    public Message removeUserFavoriteTeam(int teamId);

}
