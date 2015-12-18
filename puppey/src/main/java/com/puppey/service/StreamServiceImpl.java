package com.puppey.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;


@DependsOn("teamService")
@Service("streamService")
public class StreamServiceImpl implements StreamService {
    
    @Autowired
    private TeamService teamService;
    
    public static List<JSONObject> currentStreams = new ArrayList<>();

    
    @Override
    public List<JSONObject> getCurrentStreamsJson() {
        return currentStreams;
    }
    
    //API

    @Override
    public JSONObject getStreamsByTeam(int teamId) {
        String url = "http://api.twitch.tv/api/team/"+ teamService.getTeam(teamId).getTwitchTag() +"/live_channels.json";
        JSONObject finalJson = new JSONObject();
        try{
        String channelJson = IOUtils.toString(new URL(url).openStream(), "UTF-8");
        finalJson = (JSONObject) JSONValue.parseWithException(channelJson);
        } catch(Exception e){
        }
       return finalJson;  
    }


}
