package com.puppey.security;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import com.puppey.domain.User;
import com.puppey.service.AchievementService;
import com.puppey.service.UserService;

public class OpenIdUserSuccessHandler implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

    @Autowired
    private UserService userService;
    @Autowired
    private AchievementService achievementService;

    private static final String STEAM_KEY = "1FB03DC2704C010DB4EE7FAED18311CB";
    private static final String STEAM_API_URL = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=";
    private static final int REGISTER_ACHIEVEMENT_ID = 3;
    private static final Logger LOG = Logger.getLogger(OpenIdUserSuccessHandler.class);
    
    JSONParser parser = new JSONParser();

    @Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token)throws UsernameNotFoundException{
        System.out.println(token.getPrincipal());
    	String steamId = token.getPrincipal().toString().replaceAll("^.*\\/", "");
        User user = userService.getUserBySteamId(steamId);
        if (user == null) {
            createNewUser(steamId);
            user = userService.getUserBySteamId(steamId);
        }

        return UserSuccessHandler.buildUserFromUserEntity(user, userService.getAuthorities(user));
    }

    // STEAM_API_URL + STEAM_KEY + "&steamids=" + steamId
    private void createNewUser(String steamId) {

        String personaname = null;
        try {
            InputStream in = new URL(STEAM_API_URL + STEAM_KEY + "&steamids=" + steamId).openStream();
            String jsonString = IOUtils.toString(in);
            JSONObject json = (JSONObject) JSONValue.parseWithException(jsonString);
            json = (JSONObject) json.get("response");
            JSONArray jsonArray = (JSONArray) json.get("players");
            json = (JSONObject) jsonArray.get(0);
            personaname = (String) json.get("personaname");
        } catch (ParseException e) {
            LOG.error("Error parsing user", e);
        } catch (IOException e1) {
            LOG.error("Error getting steam info", e1);
        }

        User newUser = new User();
        newUser.setSteamId(steamId);
        newUser.setUsername(personaname);
        newUser.setEmail(steamId + "@steam.com");
        newUser.setPassword("personaname");
        userService.addUser(newUser);
        achievementService.userAddAchievement(newUser, achievementService.getAchievementById(REGISTER_ACHIEVEMENT_ID));
    }


}
