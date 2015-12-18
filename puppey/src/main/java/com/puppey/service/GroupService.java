package com.puppey.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.puppey.domain.Group;
import com.puppey.domain.Prize;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

public interface GroupService {

    public void addGroup(Group newGroup);

    public List<Group> getAllGroups();

    public void addUserToGroup(int groupId);

    public Group getGroupById(int groupId);

    public Group getGroupByIdWithPredictions(int groupId);

    public void updateGroup(Group currentGroup);

    public Map<User, TournamentPrediction> getUsersAndPredictions(Group currentGroup);

    public void updatePrize(Prize newPrize);

	public Prize getPrize(int prizeId);

    User addUserToFeaturedGroup(int groupId);

    public List<Group> searchGroups(boolean featured, Integer tournamentId);

    public Model viewGroupModels(int groupId, Model model);

    public List<Group> getFeaturedGroups();

	public void addTournamentPredictionToGroup(int tournamentPredictionId,
			int groupId);
}
