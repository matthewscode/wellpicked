package com.puppey.service;

import java.util.List;

import org.json.simple.JSONObject;

public interface StreamService {
    List<JSONObject> getCurrentStreamsJson();

    JSONObject getStreamsByTeam(int teamId);
}
