package za.ac.cput.olympicsapp.repositories;

import java.util.List;

import za.ac.cput.olympicsapp.domain.Player;

/**
 * Created by Lonwabo on 8/28/2016.
 */
public interface PlayerRepository{

    Player getPlayer(Long id);
    List<Player> getAllPlayers();
    String insertPlayer(Player player);
    String updatePlayer(Player player);
    String deletePlayer(Player player);
}
