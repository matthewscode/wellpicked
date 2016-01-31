package com.puppey.thread;

import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.puppey.domain.Team;
import com.puppey.service.StreamServiceImpl;

public class StreamThread implements Runnable {
    
    List<Team> teamList;
    
    public StreamThread(List<Team> teamList){
        this.teamList = teamList;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                
                updateStreamList();
                Thread.sleep(60000);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    @SuppressWarnings("unchecked")
    void updateStreamList(){

        StreamServiceImpl.currentStreams.clear();
        for(Team team : teamList){
            JSONObject teamAndChannel = new JSONObject();
            JSONObject channelsJsonObject = new JSONObject();
            String url = "http://api.twitch.tv/api/team/"+ team.getTwitchTag() +"/live_channels.json";
            JSONObject teamJsonObject = new JSONObject();
            JSONArray channelsArray = new JSONArray();
        
            try{
            String channelJson = IOUtils.toString(new URL(url).openStream(), "UTF-8");
            channelsJsonObject = (JSONObject) JSONValue.parseWithException(channelJson);
            channelsArray = (JSONArray)channelsJsonObject.get("channels");
            
            teamJsonObject.put("id", team.getTeamId());
            teamJsonObject.put("slug", team.getTeamSlug());
            teamJsonObject.put("channels", channelsArray);
            teamAndChannel.put("team", teamJsonObject);
            StreamServiceImpl.currentStreams.add(teamAndChannel);
            } catch(Exception e){
            }

        }
    }

}
