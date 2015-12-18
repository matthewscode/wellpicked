package com.puppey.dao;

import com.puppey.domain.User;
import com.puppey.domain.UserAuthority;

public interface UserAuthorityDao {

    public void addUser(User user);

    public void addAdmin(User user);

    public void updateUserAuthorityUserById(User user);

    public UserAuthority getUserAuthorityUserById(User user);

    public UserAuthority getUserAuthorityByUser(User user);
}
