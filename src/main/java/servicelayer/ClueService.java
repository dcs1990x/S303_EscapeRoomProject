package servicelayer;

import daomodel.DaoClue;
import daomodel.DaoInterface;
import dtomodel.ClueDTO;
import model.Clue;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClueService {
    private final DaoInterface<Clue> clueDAO = new DaoClue();

    public ClueService(){}

    public void searchDuplicates(Clue clue){}

    public void addClue(){}

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
