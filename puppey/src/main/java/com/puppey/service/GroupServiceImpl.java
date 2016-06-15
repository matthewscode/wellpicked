package com.puppey.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.puppey.dao.GroupDao;
import com.puppey.domain.Group;
import com.puppey.domain.Prize;
import com.puppey.domain.Tournament;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;
import com.puppey.util.SiteUser;

@Service("groupService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GroupServiceImpl implements GroupService {

    
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private UserService userService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private TournamentPredictionService tournamentPredictionService;
    @Autowired
    private CurrencyService currencyService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addGroup(Group newGroup) {
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.getUserById(su.getUserId());
        newGroup.setUser(currentUser);
        groupDao.addGroup(newGroup);
    }

    @Override
    @Transactional
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUserToGroup(int groupId) {
    	Group group = getGroupById(groupId);
    	SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(su.getUserId());
        if (!groupHasUser(group, user.getUserId())) {
            group.getUserList().add(user);
        }

    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUserToFeaturedGroup(int groupId) {
    	Group group = getGroupById(groupId);
    	SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(su.getUserId());
        if (!groupHasUser(group, user.getUserId())) {
            group.getUserList().add(user);
        }
        
        return user;

    }

    public boolean groupHasUser(Group group, int userId) {
        for (User user : group.getUserList()) {
            if (user.getUserId() == userId) {
                return true;
            }
        }

        return false;
    }

    @Override
    @Transactional
    public Group getGroupById(int groupId) {
        Group group = groupDao.getGroupById(groupId);
        // initialize userlist
        group.getUserList().size();
        group.getGroupPrizes().size();
        return group;
    }

    @Override
    @Transactional
    public Group getGroupByIdWithPredictions(int groupId) {
        Group group = getGroupById(groupId);
        group.getTournamentPredictions().size();
        group.getTournamentList().size();
        return group;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateGroup(Group currentGroup) {
        groupDao.updateGroup(currentGroup);

    }

    @Override
    public Map<User, TournamentPrediction> getUsersAndPredictions(Group currentGroup) {
        Map<User, TournamentPrediction> usersAndPredictions = new HashMap<User, TournamentPrediction>();
        for (TournamentPrediction tp : currentGroup.getTournamentPredictions()) {
            usersAndPredictions.put(tp.getUser(), tp);
        }
        return usersAndPredictions;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updatePrize(Prize newPrize) {
        groupDao.updatePrize(newPrize);
        
    }

	@Override
	@Transactional
	public Prize getPrize(int prizeId) {
		return groupDao.getPrize(prizeId);
	}

	//api
    @Override
    @Transactional
    public List<Group> searchGroups(boolean featured, Integer tournamentId) {
        Tournament tournament = tournamentService.getTournament(tournamentId);
        return groupDao.searchGroups(featured, tournament);
    }

    @Override
    @Transactional
    public Model viewGroupModels(int groupId, Model model) {
        Group currentGroup = getGroupByIdWithPredictions(groupId);
        Map<User, TournamentPrediction> userPredictions = getUsersAndPredictions(currentGroup);
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.getUserById(su.getUserId());
        boolean userIsInGroup = false;
        
        for (User user : currentGroup.getUserList()) {
            if (user.getUserId() == currentUser.getUserId()) {
                userIsInGroup = true;
            }
        }
        
        if (userIsInGroup) {
            List<TournamentPrediction> userTournamentPredictions = tournamentPredictionService
                    .getTournamentPredictionsByUserAndTournaments(currentUser, currentGroup.getTournamentList());
            boolean userHasPrediction = false;
            for (TournamentPrediction tp : currentGroup.getTournamentPredictions()) {
                if (tp.getUser().getUserId() == currentUser.getUserId()) {
                    userHasPrediction = true;
                    break;
                }
            }

            if (!userHasPrediction) {
                model.addAttribute("fillBracket", "You still need to add your Bracket to this League!");
                if (userTournamentPredictions.isEmpty()) {
                    model.addAttribute("createBracket", "You still need to create a Bracket for this Tournament!");
                } else {
                    model.addAttribute("userPredictions", userTournamentPredictions);
                }
            }
        }
        
        model.addAttribute("usersAndPredictions", userPredictions);
        model.addAttribute("group", currentGroup);
        return model;
    }

    @Override
    @Transactional
    public List<Group> getFeaturedGroups() {
        List<Group> groups = groupDao.getAllFeaturedGroups();
        for(Group group: groups){
            group.getTournamentList().size();
        }
        
        return groups;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addTournamentPredictionToGroup(int tournamentPredictionId, int groupId) {
//		
//		int userId =  ((SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
//		User user = userService.getUserById(userId);
//        Group currentGroup = getGroupById(groupId);
//        TournamentPrediction tournamentPrediction = tournamentPredictionService.getTournamentPredictionById(tournamentPredictionId);
//        
//       if(tournamentPrediction.getUser().getUserId() == userId){
//               currentGroup.getTournamentPredictions().add(tournamentPrediction);
//               currencyService.decreaseUserBalance(user, currentGroup.getEntryCost(), "Entry fee for joining pool:" + currentGroup.getGroupName());
//       }
		
	}

}
