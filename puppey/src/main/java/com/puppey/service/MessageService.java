package com.puppey.service;

import com.puppey.util.Message;

public interface MessageService {

    public Message isSuccessful(boolean success);

	Message getTournamentPredictionId(int tid);
}
