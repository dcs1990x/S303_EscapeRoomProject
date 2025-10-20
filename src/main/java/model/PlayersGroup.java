package model;

import java.util.ArrayList;

public class PlayersGroup {

    private ArrayList<Player> players;

    public PlayersGroup(Player player){
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player){players.add(player);}

    public void removePlayer(Player player){players.remove(player);}

    public ArrayList<Player> getPlayersInfo(){return this.players;}
}