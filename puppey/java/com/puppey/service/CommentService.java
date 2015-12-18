package com.puppey.service;

import java.util.List;

import com.puppey.domain.Comment;
import com.puppey.domain.User;
import com.puppey.util.Message;

public interface CommentService {
    public List<Comment> getUserProfileCommentsByUser(User user);

    public Message addComment(Comment comment);

    public List<Comment> getCommentsByObjectAndId(String objectName, int objectId);
    
    public List<Integer> getPopularObjects(String objectName,int targetObjectid, int amount);

    public List<Comment> getCommentsByTournamentPrediction(int objectId);

    public Message addTournamentPredictionComment(Comment jsonComment);
}
