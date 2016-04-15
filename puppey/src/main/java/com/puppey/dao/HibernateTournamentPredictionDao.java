package com.puppey.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.MatchupPrediction;
import com.puppey.domain.Tournament;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

@Repository("tournamentPredictionDao")
public class HibernateTournamentPredictionDao implements TournamentPredictionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTournamentPrediction(TournamentPrediction tournamentPrediction) {
        sessionFactory.getCurrentSession().save(tournamentPrediction);

    }

    @Override
    public TournamentPrediction getTournamentPrediction(User user, Tournament tournament) {
        return (TournamentPrediction) sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .add(Restrictions.eq("user", user)).add(Restrictions.eq("tournament", tournament)).uniqueResult();
    }

    @Override
    public TournamentPrediction getTournamnetPredictionById(int tournamentPredictionId) {
        return (TournamentPrediction) sessionFactory.getCurrentSession().get(TournamentPrediction.class,
                tournamentPredictionId);

    }

    @Override
    public void addMatchupPrediction(MatchupPrediction matchupPrediction) {
        sessionFactory.getCurrentSession().save(matchupPrediction);
    }

    @Override
    public void updateMatchupPrediction(MatchupPrediction matchupPrediction) {
        sessionFactory.getCurrentSession().update(matchupPrediction);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MatchupPrediction> getMatchupPredictionsByUser(User predictingUser) {
        return sessionFactory.getCurrentSession().createCriteria(MatchupPrediction.class)
                .add(Restrictions.eq("user", predictingUser)).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MatchupPrediction> getMatchupPredictionsByUserAndTournament(User predictingUser, Tournament tournament) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MatchupPrediction.class,
                "MatchupPrediction");
        criteria.createAlias("MatchupPrediction.matchup", "matchup");
        criteria.add(Restrictions.eq("user", predictingUser)).add(Restrictions.eq("matchup.tournament", tournament));
        return criteria.list();
    }

    @Override
    public boolean userPredictionsForTournamentExists(User user, Tournament tournament) {
        return !sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .add(Restrictions.eq("tournament", tournament)).add(Restrictions.eq("user", user)).list().isEmpty();

    }

    @Override
    public void updateTournamentPrediction(TournamentPrediction tournamentPrediction) {
        sessionFactory.getCurrentSession().update(tournamentPrediction);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TournamentPrediction> getTopFivePredictionScores(Tournament tournament) {
        return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .add(Restrictions.eq("tournament", tournament)).setMaxResults(5)
                .addOrder(Order.asc("tournamentPredictionScore")).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TournamentPrediction> getLastFivePredictions(Tournament tournament) {
        return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .add(Restrictions.eq("tournament", tournament)).setMaxResults(5).addOrder(Order.desc("creation")).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TournamentPrediction> getLatestPredictions(Tournament tournament, int amount) {
        return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .add(Restrictions.eq("tournament", tournament)).setMaxResults(amount).addOrder(Order.desc("creation"))
                .list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TournamentPrediction> getTopPredictions(Tournament tournament, int amount) {
        return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .add(Restrictions.eq("tournament", tournament)).setMaxResults(amount)
                .addOrder(Order.asc("tournamentPredictionScore")).list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TournamentPrediction> getTournamentPredictionsByTournament(Tournament tournament) {
        return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .add(Restrictions.eq("tournament", tournament)).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TournamentPrediction> getTournamentPredictionsByUser(User user) {
        return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .add(Restrictions.eq("user", user)).list();
    }

    @Override
    public MatchupPrediction getMatchupPredictionById(int matchupPredictionId) {
        return (MatchupPrediction) sessionFactory.getCurrentSession().get(MatchupPrediction.class, matchupPredictionId);
    }

    @Override
    public TournamentPrediction getTournamentPredictionById(int tournamentPredictionId) {
        return (TournamentPrediction) sessionFactory.getCurrentSession().get(TournamentPrediction.class,
                tournamentPredictionId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TournamentPrediction> getTournamentPredictionsByUserAndTournaments(User currentUser,
            List<Tournament> tournaments) {
        List<TournamentPrediction> tournamentPredictions = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            List<TournamentPrediction> currentPredictions = sessionFactory.getCurrentSession()
                    .createCriteria(TournamentPrediction.class).add(Restrictions.eq("user", currentUser))
                    .add(Restrictions.eq("tournament", tournament)).list();
            for (TournamentPrediction tp : currentPredictions) {
                tournamentPredictions.add(tp);
            }
        }
        return tournamentPredictions;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TournamentPrediction> getTournamentPredictionsForTournamentByUser(
            Tournament tournament, User user) {
       return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("tournament", tournament)).list(); 
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<TournamentPrediction> getLatestTournamentPredictions(
			int numResults) {
		return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class)
                .setMaxResults(numResults)
                .addOrder(Order.desc("tournamentPredictionScore")).list();
	}

	@Override
	public List<TournamentPrediction> getTournamentPredictionsByScore(
			Tournament tournament, int numResults) {
		return sessionFactory.getCurrentSession().createCriteria(TournamentPrediction.class).add(Restrictions.eq("tournament", tournament))
                .setMaxResults(numResults)
                .addOrder(Order.desc("tournamentPredictionScore")).list();
	}

}
