package com.puppey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.dao.TemplateDao;
import com.puppey.dao.TournamentDao;
import com.puppey.domain.Matchup;
import com.puppey.domain.Template;
import com.puppey.domain.Tournament;
import com.puppey.template.*;

@Service("tournamentCreationService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class TournamentCreationServiceImpl implements TournamentCreationService {
    
	@Autowired
	private TournamentDao tournamentDao;
    @Autowired
    private TemplateDao templateDao;
//    @Autowired
//    private Template template8SE;
    @Autowired
    private TournamentTemplate template4U4LDE; 
//    @Autowired
//    private Template template4U0LDE;
//    @Autowired
//    private Template template8U8LDE;
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void createTemplate(Tournament tournament) {
        Template template = tournament.getTemplate();
        int id = template.getTemplateId();
        if(id == 1){
            template4U4LDE.createTournament(tournament);
//        }else if("8U8LDE".equals(template)){
//            template8U8LDE.createTournament(tournament);
//        }else if("8SE".equals(template)){
//            template8SE.createTournament(tournament);
//        }else if("4U0LDE".equals(template)){
//            template4U0LDE.createTournament(tournament);
//        }else if("GS".equals(template)){
//            createGroupStage(tournament, tournament.getNumTeams());
        }
    }
    
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
    public List<Template> getAllTemplates() {
        return templateDao.getAllTemplates();
    }


}
