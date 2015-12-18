package com.puppey.dao;

import java.util.List;

import com.puppey.domain.User;

public interface UserDao {

    public void addUser(User user);

    public User getUserByName(String userName);

    public boolean userNameExists(String username);

    public boolean userEmailExists(String email);

    public void updateLastLogin(User user);

    public void updateUser(User user);

    public User getUserByEmail(String email);

    public User getUserBySteamId(String steamId);

    public User getUserById(int userId);

    public List<String> getUserAuthorities(User user);

	public List<User> getUsersByTopScores(int amount);

}
