package com.puppey.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppey.domain.Comment;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

@Repository("commentDao")
public class HibernateCommentDao implements CommentDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> getUserProfileCommentsByUser(User user) {
        return sessionFactory.getCurrentSession().createCriteria(Comment.class).add(Restrictions.eq("objectName", "user")).add(Restrictions.eq("objectId", user.getUserId())).list();
    }

    @Override
    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> getCommentsByObjectAndId(String objectName, int objectId) {
        return sessionFactory.getCurrentSession().createCriteria(Comment.class).add(Restrictions.eq("objectName", objectName)).add(Restrictions.eq("objectId", objectId)).list();
    }

    @Override
    public List<Integer> getPoplatObjects(String objectName, int targetObjectid, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> getCommentsByTournamentPrediction(TournamentPrediction tournamentPrediction) {
        return sessionFactory.getCurrentSession().createCriteria(Comment.class).add(Restrictions.eq("tournamentPrediction", tournamentPrediction)).list();

    }
    
    

}
