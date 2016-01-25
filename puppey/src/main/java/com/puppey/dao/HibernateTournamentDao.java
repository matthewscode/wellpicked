package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.Matchup;
import com.puppey.domain.MatchupWrapper;
import com.puppey.domain.Tournament;

@Repository("tournamentDao")
public class HibernateTournamentDao implements TournamentDao {

    int currentTime = (int) (System.currentTimeMillis() / 1000);

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Tournament> getAllTournaments() {
        return sessionFactory.getCurrentSession().createCriteria(Tournament.class).add(Restrictions.eq("deleted", 0))
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tournament> getAllTournamentsByCreationDate() {
        return sessionFactory.getCurrentSession().createCriteria(Tournament.class).add(Restrictions.eq("deleted", 0))
                .addOrder(Order.asc("creation")).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Matchup> getTournamentMatchups(Tournament tournament) {
        return sessionFactory.getCurrentSession().createCriteria(Matchup.class)
                .add(Restrictions.eq("tournament", tournament)).list();
    }

    @Override
    public void deleteTournament(Tournament tournament) {
        tournament.setDeleted(1);
        sessionFactory.getCurrentSession().saveOrUpdate(tournament);
    }

    @Override
    public void addTournament(Tournament tournament) {
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().save(tournament);
    }

    @Override
    public void addMatchup(Matchup matchup) {
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().save(matchup);
    }

    @Override
    public void updateMatchup(Matchup matchup) {
        sessionFactory.getCurrentSession().flush();
        Object sessionEntity = sessionFactory.getCurrentSession().load(Matchup.class, matchup.getMatchupId());
        if (sessionEntity != null) {
            sessionFactory.getCurrentSession().evict(sessionEntity);
        }
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().update(matchup);

    }

    @Override
    public void addMatchupWrapper(MatchupWrapper matchupWrapper) {
        for (int i = 0; i < matchupWrapper.getMatchupList().size(); i++) {
            sessionFactory.getCurrentSession().saveOrUpdate(matchupWrapper.getMatchupList().get(i));
        }
    }
    
    @Override
    public Tournament getTournament(int tid) {
        sessionFactory.getCurrentSession().flush();
        Tournament tournament = (Tournament) sessionFactory.getCurrentSession().get(Tournament.class, tid);
        // initialize mathuplist to avoid fetch=eager
        tournament.getMatchups().size();
        return tournament;
    }

    @Override
    public Tournament getTournamentNoMatchups(int tid) {
        sessionFactory.getCurrentSession().flush();
        return (Tournament) sessionFactory.getCurrentSession().get(Tournament.class, tid);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Matchup> getMatchupsByTournamentId(int tid) {
        sessionFactory.getCurrentSession().flush();
        Tournament tournamentForMatchups = getTournamentNoMatchups(tid);
        return sessionFactory.getCurrentSession().createCriteria(Matchup.class)
                .add(Restrictions.eq("tournament", tournamentForMatchups)).list();
    }

    @Override
    public Tournament getTournamentByMatchupId(int matchupId) {
        return ((Matchup) sessionFactory.getCurrentSession().get(Matchup.class, matchupId)).getTournament();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Matchup> getFirstRoundMatchups(Tournament tournament, int numFirstRoundMatchups) {
        return sessionFactory.getCurrentSession().createCriteria(Matchup.class)
                .add(Restrictions.eq("tournament", tournament)).addOrder(Order.asc("matchupId"))
                .setMaxResults(numFirstRoundMatchups).list();
    }

    @Override
    public int getMatchupWinnerId(Matchup matchup) {
        return ((Matchup) sessionFactory.getCurrentSession().get(Matchup.class, matchup.getMatchupId())).getWinnerId();
    }

    @Override
    public int getMatchupWeight(Matchup matchup) {
        return ((Matchup) sessionFactory.getCurrentSession().get(Matchup.class, matchup.getMatchupId())).getWeight();
    }

    @Override
    public Matchup getMatchup(int mid) {
        return (Matchup) sessionFactory.getCurrentSession().get(Matchup.class, mid);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int getFirstPriorMatchup(Matchup winnerMatchup) {
        List<Matchup> matchupList = sessionFactory.getCurrentSession().createCriteria(Matchup.class)
                .add(Restrictions.eq("nextMatchup", winnerMatchup.getMatchupId())).addOrder(Order.asc("matchupId"))
                .setMaxResults(1).list();
        return matchupList.get(0).getMatchupId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public int getSecondPriorMatchup(Matchup winnerMatchup) {
        List<Matchup> matchupList = sessionFactory.getCurrentSession().createCriteria(Matchup.class)
                .add(Restrictions.eq("nextMatchup", winnerMatchup.getMatchupId())).addOrder(Order.desc("matchupId"))
                .setMaxResults(1).list();
        return matchupList.get(0).getMatchupId();
    }

    @Override
    public boolean tournamentNameExists(String tournamentName) {
        return !sessionFactory.getCurrentSession().createCriteria(Tournament.class)
                .add(Restrictions.eq("tournamentName", tournamentName)).list().isEmpty();
    }

    @Override
    public boolean tournamentSlugExists(String tournamentSlug) {
        return !sessionFactory.getCurrentSession().createCriteria(Tournament.class)
                .add(Restrictions.eq("tournamentSlug", tournamentSlug)).list().isEmpty();

    }

    @Override
    public void updateTournament(Tournament editedTournament) {
        sessionFactory.getCurrentSession().update(editedTournament);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tournament> getCurrentAndUpcomingTournaments(int currentTime) {
        return sessionFactory.getCurrentSession().createCriteria(Tournament.class).add(Restrictions.eq("deleted", 0))
                .add(Restrictions.gt("tournamentEnd", currentTime)).addOrder(Order.asc("tournamentStart")).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tournament> getCompletedTournaments(int currentTime) {
        return sessionFactory.getCurrentSession().createCriteria(Tournament.class).add(Restrictions.eq("deleted", 0))
                .add(Restrictions.lt("tournamentEnd", currentTime)).addOrder(Order.asc("tournamentStart")).list();

    }

    @Override
    public Tournament getTournamentBySlug(String tournamentSlug) {
        sessionFactory.getCurrentSession().flush();
        Tournament tournamentBySlug = (Tournament) sessionFactory.getCurrentSession().createCriteria(Tournament.class)
                .add(Restrictions.eq("tournamentSlug", tournamentSlug)).uniqueResult();
        // lazily initialize
        tournamentBySlug.getMatchups().size();
        return tournamentBySlug;
    }

    @Override
    public Tournament getTournamentBySlugNoMatchups(String tournamentSlug) {
        sessionFactory.getCurrentSession().flush();
        return (Tournament) sessionFactory.getCurrentSession().createCriteria(Tournament.class)
                .add(Restrictions.eq("tournamentSlug", tournamentSlug)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tournament> getAllActiveTournaments() {
        return sessionFactory.getCurrentSession().createCriteria(Tournament.class).add(Restrictions.eq("deleted", 0))
                .add(Restrictions.gt("tournamentStart", currentTime)).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tournament> getCurrentTournaments() {
        return sessionFactory.getCurrentSession().createCriteria(Tournament.class).add(Restrictions.lt("tournamentStart", currentTime)).add(Restrictions.eq("deleted", 0)).add(Restrictions.gt("tournamentEnd", currentTime)).list();
   
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tournament> getLatestCompletedTournaments(int tAmount) {
        return sessionFactory.getCurrentSession().createCriteria(Tournament.class).add(Restrictions.eq("deleted", 0))
                .add(Restrictions.lt("tournamentEnd", currentTime)).addOrder(Order.desc("tournamentStart")).setMaxResults(tAmount).list();
    }

}
