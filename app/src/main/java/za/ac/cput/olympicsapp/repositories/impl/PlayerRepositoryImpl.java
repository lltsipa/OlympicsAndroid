package za.ac.cput.olympicsapp.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.cput.olympicsapp.conf.databases.DBConstants;
import za.ac.cput.olympicsapp.domain.Player;
import za.ac.cput.olympicsapp.repositories.PlayerRepository;

/**
 * Created by Lonwabo on 8/28/2016.
 */
public class PlayerRepositoryImpl implements PlayerRepository {

    final String BASE_URL = "http://olympics-andies.rhcloud.com/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();


    @Override
    public Player getPlayer(Long id) {

        final String url = BASE_URL + "player/" + id.toString();

        HttpEntity<Player> requestEntity = new HttpEntity<Player>(requestHeaders);
        ResponseEntity<Player> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Player.class);
        Player player = responseEntity.getBody();

        return player;
    }

    @Override
    public List<Player> getAllPlayers() {
        final String url = BASE_URL + "players/";

        List<Player> playerList = new ArrayList<>();
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Player[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Player[].class);
        Player[] results = responseEntity.getBody();

        for (Player player : results) {

            playerList.add(player);
        }

        return playerList;
    }

    @Override
    public String insertPlayer(Player player) {
        final String url = BASE_URL + "player/create";

        HttpEntity<Player> requestEntity = new HttpEntity<Player>(player, requestHeaders);

        try {

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            String result = responseEntity.getBody();

            return result;
        }
        catch (Exception e) {

            return "Player already exists.";
        }
    }

    @Override
    public String updatePlayer(Player player) {
        final String url = BASE_URL+"player/update/"+player.getId();
        HttpEntity<Player> requestEntity = new HttpEntity<Player>(player, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String deletePlayer(Player player) {
        final String url = BASE_URL+"player/delete/"+player.getId().toString();
        HttpEntity<Player> requestEntity = new HttpEntity<Player>(player, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();
    }
}
