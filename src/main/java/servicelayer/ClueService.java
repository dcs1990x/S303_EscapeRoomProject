package servicelayer;

import daomodel.DaoClue;
import dtomodel.ClueDTO;
import model.Clue;


import java.util.ArrayList;
import java.util.List;


public class ClueService{
    private final DaoClue clueDAO = new DaoClue();

    public ClueService(){}

    public boolean searchDuplicates(Clue clue){
        return clueDAO.duplicate(clue);
    }

    public void addClue(Clue clue){
       try{
           if(!clue.isEmpty()||!searchDuplicates(clue)){
           clueDAO.insertEntity(clue);}
           else{System.out.println("The clue was empty, therefore it could not be inserted to DB.");}
       }
       catch(Exception sqle1){
           System.err.println(sqle1.getMessage());
           System.out.println("The clue was not added correctly, please try again with the correct format");
       }

    }
    public void updateClue(Clue clue){
        try{
            if(!clue.isEmpty()||searchDuplicates(clue)){
                clueDAO.updateEntity(clue.getIdClue(),clue);}
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
    public ClueDTO getClue(Long id) throws Exception {
        final Clue clue = clueDAO.readEntity(id);

        return new ClueDTO(clue.getName(),clue.getDescription(),clue.getDifficultyPoints(),clue.getIsSolved());
    }

}
