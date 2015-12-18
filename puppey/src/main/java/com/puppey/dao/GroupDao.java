package com.puppey.dao;

import java.util.List;

import com.puppey.domain.Group;
import com.puppey.domain.Prize;
import com.puppey.domain.Tournament;

public interface GroupDao {

    void addGroup(Group newGroup);

    List<Group> getAllGroups();

    Group getGroupById(int groupId);

    void updateGroup(Group group);

    void updatePrize(Prize newPrize);

	Prize getPrize(int prizeId);

    List<Group> getFeaturedGroupsByTournament(Tournament tournament);

    List<Group> searchGroups(boolean featured, Tournament tournament);

    List<Group> getAllFeaturedGroups();

}
