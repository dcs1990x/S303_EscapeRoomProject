package servicelayer;

import daomodel.DaoClue;
import daomodel.DaoInterface;
import model.Clue;

public class ClueService {
    DaoInterface<Clue> clueWriterOrReader = new DaoClue();


}
