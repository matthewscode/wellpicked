package com.puppey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int commentId;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    
    @NotEmpty
    @Column(name = "comment", columnDefinition=("VARCHAR(150)"))
    private String userComment;
    
    @Column(name = "object")
    private String objectName;
    
    @Column(name = "object_id")
    private int objectId;
    
    @ManyToOne
    @JoinColumn(name = "replied_comment")
    private Comment repliedComment;
    
    @ManyToOne
    @JoinColumn(name = "top_comment")
    private Comment topComment;
    
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "touranment_prediction")
    private TournamentPrediction tournamentPrediction;
    
    @NotNull
    @Column(name = "creation", updatable = false)
    private Integer creation = (int) (System.currentTimeMillis() / 1000);
    
    @Override
    public String toString(){
    	return userComment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return userComment;
    }

    public void setComment(String comment) {
        this.userComment = comment;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public Comment getRepliedComment() {
        return repliedComment;
    }

    public void setRepliedComment(Comment repliedComment) {
        this.repliedComment = repliedComment;
    }

    public Comment getTopComment() {
        return topComment;
    }

    public void setTopComment(Comment topComment) {
        this.topComment = topComment;
    }

    public Integer getCreation() {
        return creation;
    }

    public void setCreation(Integer creation) {
        this.creation = creation;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public TournamentPrediction getTournamentPrediction() {
        return tournamentPrediction;
    }

    public void setTournamentPrediction(TournamentPrediction tournamentPrediction) {
        this.tournamentPrediction = tournamentPrediction;
    }
    
    
    
}
