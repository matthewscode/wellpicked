package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.User;

@Repository("userDao")
public class HibernateUserDao implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserByName(String userName) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("username", userName)).uniqueResult();
    }

    @Override
    public boolean userNameExists(String username) {
        return !sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("username", username)).list().isEmpty();
    }

    @Override
    public boolean userEmailExists(String email) {
        return !sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email))
                .list().isEmpty();
    }

    @Override
    public void updateLastLogin(User user) {
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().update(user);

    }

    @Override
    public void updateUser(User user) {

        sessionFactory.getCurrentSession().update(user);

    }

    @Override
    public User getUserByEmail(String email) {

        return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email)).uniqueResult();

    }

    @Override
    public User getUserBySteamId(String steamId) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("steamId", steamId)).uniqueResult();

    }



    @Override
    public User getUserById(int userId) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("userId", userId)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getUserAuthorities(User user) {

        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT authority FROM authorities WHERE u_id = " + user.getUserId()).list();

    }

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByTopScores(int amount) {
		return sessionFactory.getCurrentSession().createCriteria(User.class).setMaxResults(amount).addOrder(Order.desc("totalPoints")).list();
	}
	
	
	

}
