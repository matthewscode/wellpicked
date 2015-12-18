package com.puppey.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.User;
import com.puppey.domain.UserAuthority;

@Repository("userAuthorityDao")
public class HibernateUserAuthorityDao implements UserAuthorityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(user.getUserId());
        userAuthority.setAuthority("ROLE_USER");
        sessionFactory.getCurrentSession().save(userAuthority);
    }

    @Override
    public UserAuthority getUserAuthorityUserById(User user) {

        return (UserAuthority) sessionFactory.getCurrentSession().createCriteria(UserAuthority.class)
                .add(Restrictions.eq("userId", user.getUserId())).add(Restrictions.eq("authority", "ROLE_USER"))
                .uniqueResult();
    }

    // not ready
    @Override
    public void updateUserAuthorityUserById(User user) {
        UserAuthority userAuthority = getUserAuthorityUserById(user);
        sessionFactory.getCurrentSession().update(userAuthority);
    }

    @Override
    public UserAuthority getUserAuthorityByUser(User user) {
        return (UserAuthority) sessionFactory.getCurrentSession().get(UserAuthority.class, user.getUsername());
    }

    @Override
    public void addAdmin(User user) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(user.getUserId());
        userAuthority.setAuthority("ROLE_ADMIN");
        sessionFactory.getCurrentSession().save(userAuthority);

    }

}
