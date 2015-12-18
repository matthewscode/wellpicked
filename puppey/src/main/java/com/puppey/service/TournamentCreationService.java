package com.puppey.service;

import java.util.List;

import com.puppey.domain.Template;
import com.puppey.domain.Tournament;

public interface TournamentCreationService {

    public void createTemplate(Tournament tournament);

    public List<Template> getAllTemplates();

	void createGroupStage(Tournament tournament, int numTeams);

}
