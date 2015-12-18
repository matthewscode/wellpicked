package com.puppey.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.Group;
import com.puppey.domain.Prize;
import com.puppey.domain.Tournament;

@Repository("groupDao")
public class HibernateGroupDao implements GroupDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addGroup(Group newGroup) {
        sessionFactory.getCurrentSession().save(newGroup);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> getAllGroups() {
        return sessionFactory.getCurrentSession().createCriteria(Group.class).list();
    }

    @Override
    public Group getGroupById(int groupId) {
        return (Group) sessionFactory.getCurrentSession().get(Group.class, groupId);
    }

    @Override
    public void updateGroup(Group group) {
    	 sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().update(group);

    }

    @Override
    public void updatePrize(Prize newPrize) {
    	 sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().saveOrUpdate(newPrize);

    }

	@Override
	public Prize getPrize(int prizeId) {
		return (Prize) sessionFactory.getCurrentSession().get(Prize.class, prizeId);
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> getFeaturedGroupsByTournament(Tournament tournament) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Group.class, "group");
        c.createAlias("group.tournamentList", "tournament");      
        c.add(Restrictions.eq("tournament.tournamentId", tournament.getTournamentId()));
        c.add(Restrictions.eq("featured", true));
        return c.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> searchGroups(boolean featured, Tournament tournament) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Group.class, "group");
        c.createAlias("group.tournamentList", "tournament");      
        c.add(Restrictions.eq("tournament.tournamentId", tournament.getTournamentId()));
        c.add(Restrictions.eq("featured", featured));
        return c.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> getAllFeaturedGroups() {
        return sessionFactory.getCurrentSession().createCriteria(Group.class).add(Restrictions.eq("featured", true)).add(Restrictions.eq("deleted", false)).list();
    }

}
