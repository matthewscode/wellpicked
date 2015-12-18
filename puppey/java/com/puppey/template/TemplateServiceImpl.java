package com.puppey.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.dao.TemplateDao;
import com.puppey.dao.TournamentDao;
import com.puppey.domain.Matchup;
import com.puppey.domain.Template;
import com.puppey.domain.Tournament;

@Service("templateService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class TemplateServiceImpl implements TemplateService{

	
	@Autowired
	private TournamentDao tournamentDao;
	@Autowired
	private TemplateDao templateDao;
//	@Autowired
//	private TournamentTemplate template4U0LDE;
	@Autowired
	private TournamentTemplate template4U4LDE;
//	@Autowired
//	private TournamentTemplate template8SE;
//	@Autowired
//	private TTournamentTemplate template8U8LDE;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createGroupStage(Tournament tournament, int numTeams) {
		int j = numTeams;
		for(int i=1; i < numTeams+1; i++){
			Matchup newMatchup = new Matchup("GS"+i, tournament);
			newMatchup.setWeight(j--);
			tournamentDao.addMatchup(newMatchup);
		}
		
	}
	
	@Override
	@Transactional
	public Template getTemplateById(int id){
	    return templateDao.getTemplateById(id);
	}

	@Override
	public void create4U0LDE(Tournament tournament) {
		//template4U0LDE.createTournament(tournament);
		
	}

	@Override
	public void createTournament(Tournament tournamentToBeAdded, String template) {
		// TODO Auto-generated method stub
		
	}
	
	

}
