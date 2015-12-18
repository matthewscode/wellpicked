package com.puppey.service;

import java.util.List;

import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

public interface ProfileService {

    List<TournamentPrediction> getTournamentPredictionsByUser(User user);

}
