package com.puppey.service;

import org.springframework.stereotype.Service;

import com.puppey.util.Message;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Override
    public Message isSuccessful(boolean success) {
        if (success) {
            return new Message(true);
        } else {
            return new Message(false);
        }
    }
    @Override
    public Message getTournamentPredictionId(int tid) {
        if (tid > 0) {
            return new Message(tid);
        } else {
            return new Message(false);
        }
    }

}
