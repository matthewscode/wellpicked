package com.puppey.dao;

import java.util.List;

import com.puppey.domain.Comment;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

public interface CommentDao {
    
    public List<Comment> getUserProfileCommentsByUser(User user);

    public void addComment(Comment comment);

    public List<Comment> getCommentsByObjectAndId(String objectName, int objectId);

    public List<Integer> getPoplatObjects(String objectName, int targetObjectid, int amount);

    public List<Comment> getCommentsByTournamentPrediction(TournamentPrediction tournamentPrediction);
}
