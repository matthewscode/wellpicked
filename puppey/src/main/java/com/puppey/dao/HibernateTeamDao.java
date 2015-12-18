package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.Team;

@Repository("teamDao")
public class HibernateTeamDao implements TeamDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTeam(Team team) {
        sessionFactory.getCurrentSession().saveOrUpdate(team);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Team> getAllTeams() {
        return sessionFactory.getCurrentSession().createCriteria(Team.class).add(Restrictions.ne("teamId",0)).list();
    }

    @Override
    public void updateTeam(Team team) {
        sessionFactory.getCurrentSession().update(team);
    }

    @Override
    public Team getTeam(int teamId) {
        return (Team) sessionFactory.getCurrentSession().get(Team.class, teamId);

    }

    @Override
    public Boolean getTeamExistsByTeamName(String teamName) {
        return !sessionFactory.getCurrentSession().createCriteria(Team.class)
                .add(Restrictions.eq("teamName", teamName)).list().isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Team> getAllActiveTeams() {
        return sessionFactory.getCurrentSession().createCriteria(Team.class).add(Restrictions.eq("isDeleted",false)).addOrder(Order.asc("region")).addOrder(Order.asc("teamName")).list();
    }

	@Override
	public Team getTeamBySlug(String teamSlug) {
		return (Team) sessionFactory.getCurrentSession().createCriteria(Team.class).add(Restrictions.eq("teamSlug", teamSlug)).uniqueResult();
	}


}
