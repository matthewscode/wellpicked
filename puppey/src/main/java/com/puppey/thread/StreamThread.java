package com.puppey.thread;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.puppey.domain.Team;
import com.puppey.dto.StreamDto;
import com.puppey.service.StreamServiceImpl;

public class StreamThread implements Runnable {
    
    List<Team> teamList;
    List<String> teamTwitchList;
    public StreamThread(List<Team> teamList){
        this.teamList = teamList;
        teamTwitchList = new ArrayList<>();
        for(Team team: teamList){
        	teamTwitchList.add(team.getTwitchTag());
        }
    }
    
    @Override
    public void run() {
        while(true){
            try {
                
                updateStreamList();
                Thread.sleep(120000);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    @SuppressWarnings("unchecked")
    void updateStreamList(){

        StreamServiceImpl.currentStreams.clear();
        
        
            try{
            String channelJson = IOUtils.toString(new URL("https://api.twitch.tv/kraken/streams?game=dota%202&limit=8").openStream(), "UTF-8");
            JSONObject streamListJsonObject = (JSONObject) JSONValue.parseWithException(channelJson);
            List<String> liveStreamers = new ArrayList<>();
            JSONArray streamArray = (JSONArray) streamListJsonObject.get("streams");
            List<StreamDto> streamDtoList = new ArrayList<>();
            for (int i = 0; i < streamArray.size(); i++) {
            		JSONObject stream = (JSONObject)streamArray.get(i);
            		JSONObject channel = (JSONObject)stream.get("channel");
            		String name = (String)channel.get("name");
            		liveStreamers.add(name);
            	}
            for(String streamerName : liveStreamers){
            	StreamDto dto = new StreamDto();
            	dto.setName(streamerName);
            	JSONObject userJson = (JSONObject)JSONValue.parseWithException(IOUtils.toString(new URL("https://api.twitch.tv/kraken/channels/" + streamerName + "/teams").openStream(), "UTF-8"));
            	JSONArray userTeams = (JSONArray)userJson.get("teams");
            	for (int i = 0; i < userTeams.size(); i++) {
            		JSONObject team = (JSONObject)userTeams.get(i);
            		String teamName = (String)team.get("name");
            		System.out.println(teamTwitchList);
            		if(teamTwitchList.contains(teamName)){
            			dto.setTeam(teamName);
            		}
            	}
            	StreamServiceImpl.currentStreams.add(dto);
            }
            
            } catch(Exception e){
            }

    }
    
   

}
