package com.puppey.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.puppey.dto.StreamDto;

public interface StreamService {
    List<StreamDto> getCurrentStreamsJson();

    JSONObject getStreamsByTeam(int teamId);
}
