package za.ac.cput.olympicsapp.services;

import android.content.Context;

import java.util.List;
import java.util.Set;

import za.ac.cput.olympicsapp.domain.Player;

/**
 * Created by Lonwabo on 8/28/2016.
 */
public interface PlayerService {
    String addPlayer(Player player);
    String updatePlayer(Player player);
    String removePlayer(Player player);
    List<Player> getAllPlayers();
    Player getPlayer(Long id);
}
