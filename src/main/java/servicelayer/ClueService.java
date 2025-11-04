package servicelayer;

import daomodel.DaoClue;
import dtomodel.ClueDTO;
import model.Clue;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClueService{
    private final DaoClue clueDAO =  DaoClue.getInstance();

    public ClueService(){}

    public boolean searchDuplicates(Clue clue){
        return clueDAO.duplicate(clue);
    }

    public void addClue(Clue clue, int idRoom){
        try{
            if(!clue.isEmpty()||!searchDuplicates(clue)){
                clueDAO.insertEntity(clue, idRoom);}
            else{System.out.println("The clue was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The clue was not added correctly, please try again with the correct format");
        }

    }
    public void updateClue(Clue clue, long id){
        try{
            if(!clue.isEmpty()||searchDuplicates(clue)){
                clueDAO.updateEntity(id, clue);}
            else{System.out.println("The clue was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The clue was not added correctly, please try again with the correct format");
        }
    }
    public ClueDTO readClueById(long id){
        Clue readClue = null;
        try{
            clueDAO.readEntity(id);
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The clue was not added correctly, please try again with the correct format");
        }
        return new ClueDTO(readClue.getName(),readClue.getDescription(),readClue.getDifficultyPoints(),readClue.getIsSolved());
    }

    public List<ClueDTO> getClues() throws Exception {
        final List<Clue> clues = clueDAO.readAllEntities();
        List<ClueDTO> cluesDTO = new ArrayList<>();
        for(Clue clue:clues){
            cluesDTO.add(new ClueDTO(clue.getName(),clue.getDescription(),clue.getDifficultyPoints(),clue.getIsSolved()));
        }
        return cluesDTO;
    }

    public void deleteClueById(long id) throws Exception {
            clueDAO.deleteEntity(id);
    }

}
