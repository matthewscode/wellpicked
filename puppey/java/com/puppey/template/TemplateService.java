package com.puppey.template;
import com.puppey.domain.Template;
import com.puppey.domain.Tournament;

public interface TemplateService {

	public void createGroupStage(Tournament tournament, int numTeams);
	public void create4U0LDE(Tournament tournament);
	public void createTournament(Tournament tournamentToBeAdded, String template);
    public Template getTemplateById(int parseInt);
}
