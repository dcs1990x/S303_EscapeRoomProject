package servicelayer;

import daomodel.DaoPlayer;
import dtomodel.PlayerDTO;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {
    private final DaoPlayer daoPlayer = new DaoPlayer();

    public PlayerService(){}

    public boolean searchDuplicates(Player player){
        return daoPlayer.duplicate(player);
    }

    public void addClue(Player player){
        try{
            if(!player.isEmpty()||!searchDuplicates(player)){
                daoPlayer.insertEntity(player);}
            else{System.out.println("Player was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("Player was not added correctly, please try again with the correct format");
        }

    }
    public void updateClue(Player player){
        try{
            if(!player.isEmpty()||searchDuplicates(player)){
                daoPlayer.updateEntity(player.getIdPlayer(),player);}
            else{System.out.println("Player was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("Player was not added correctly, please try again with the correct format");
        }
    }
    public PlayerDTO readItemById(long id){
        Player readPlayer = null;
        try{
            readPlayer = daoPlayer.readEntity(id);
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("Player was not added correctly, please try again with the correct format");
        }
        return new PlayerDTO(readPlayer.getName(),readPlayer.hasMadeReservation(),readPlayer.getScore());
    }

    public List<PlayerDTO> getPlayers() throws Exception {
        final List<Player> players = daoPlayer.readAllEntities();
        List<PlayerDTO> playersDTO = new ArrayList<>();
        for(Player player:players){
            playersDTO.add(new PlayerDTO(player.getName(),player.hasMadeReservation(),player.getScore()));
        }
        return playersDTO;
    }
    public PlayerDTO getPlayer(Long id) throws Exception {
        final Player player = daoPlayer.readEntity(id);

        return new PlayerDTO(player.getName(),player.hasMadeReservation(),player.getScore());
    }
}
