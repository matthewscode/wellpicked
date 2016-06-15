package com.puppey.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.dao.CommentDao;
import com.puppey.domain.Comment;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;
import com.puppey.util.Message;
import com.puppey.util.SiteUser;

@Service("commentService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CommentServiceImpl implements CommentService{
  
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserService userService;
    @Autowired
    private TournamentPredictionService tournamentPredictionService;
    @Autowired 
    private MessageService messageService;
    
    private static final Logger LOG = Logger.getLogger(CommentServiceImpl.class);

    @Override
    @Transactional
    public List<Comment> getUserProfileCommentsByUser(User user) {
        return commentDao.getUserProfileCommentsByUser(user);
    }


    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Message addComment(Comment comment) {
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.getUserById(su.getUserId());
        comment.setUser(currentUser);
        try{
            commentDao.addComment(comment);
        }catch(Exception e){
            LOG.error("Unable to save comment, you probably didn't fill out everything", e);
            return messageService.isSuccessful(false);
        }
        return messageService.isSuccessful(true);
        
    }


    @Override
    @Transactional
    public List<Comment> getCommentsByObjectAndId(String objectName, int objectId) {
        return commentDao.getCommentsByObjectAndId(objectName, objectId);
    }


    @Override
    @Transactional
    public List<Integer> getPopularObjects(String objectName, int targetObjectid, int amount) {
        return commentDao.getPoplatObjects(objectName,targetObjectid,amount);
    }


    @Override
    @Transactional
    public List<Comment> getCommentsByTournamentPrediction(int objectId) {
//        TournamentPrediction tournamentPrediction = tournamentPredictionService.getTournamentPredictionById(objectId);
//        return commentDao.getCommentsByTournamentPrediction(tournamentPrediction);
    	return null;
    }


    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Message addTournamentPredictionComment(Comment jsonComment) {
//        System.out.println(jsonComment.getObjectId());
//        TournamentPrediction tp = tournamentPredictionService.getTournamentPredictionById(jsonComment.getObjectId());
//        jsonComment.setTournamentPrediction(tp);
//        System.out.println(TournamentPredictionServiceImpl.getPopularTournamentPredictions().size());
//        Message message = addComment(jsonComment);
//        if(message.getSuccess()){
//        TournamentPredictionServiceImpl.addToDeque(tp);
//        }
//        return message;
    	return null;
    }

}
