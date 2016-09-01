package za.ac.cput.olympicsapp.services.impl;

import android.content.Context;

import java.util.List;
import java.util.Set;

import za.ac.cput.olympicsapp.conf.util.App;
import za.ac.cput.olympicsapp.domain.Player;
import za.ac.cput.olympicsapp.repositories.PlayerRepository;
import za.ac.cput.olympicsapp.repositories.impl.PlayerRepositoryImpl;
import za.ac.cput.olympicsapp.services.PlayerService;

/**
 * Created by Lonwabo on 8/28/2016.
 */
public class PlayerServiceImpl implements PlayerService{
    final  PlayerRepository repository = new PlayerRepositoryImpl();

    @Override
    public String addPlayer(Player player) {
        return repository.insertPlayer(player);
    }

    @Override
    public String updatePlayer(Player player) {
        return repository.updatePlayer(player);
    }

    @Override
    public String removePlayer(Player player) {
        return repository.deletePlayer(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return repository.getAllPlayers();
    }

    @Override
    public Player getPlayer(Long id) {
        return repository.getPlayer(id);
    }
}

