package com.puppey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

@Service("profileService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private TournamentPredictionService tournamentPredictionService;

    @Override
    @Transactional
    public List<TournamentPrediction> getTournamentPredictionsByUser(User user) {
        return tournamentPredictionService.getTournamentPredictionsByUser(user);
    }

}
