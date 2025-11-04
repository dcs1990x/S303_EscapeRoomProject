package servicelayer;

import daomodel.DaoDecoration;
import dtomodel.DecorationDTO;
import model.Decoration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DecorationService {
    private final DaoDecoration decorationDAO = new DaoDecoration();

    public DecorationService(){}

    public boolean searchDuplicates(Decoration decoration){
        return decorationDAO.duplicate(decoration);
    }

    public void addDecoration(Decoration decoration, int idRoom){
        try{
            if(!decoration.isEmpty()||!searchDuplicates(decoration)){
                decorationDAO.insertEntity(decoration, idRoom);}
            else{System.out.println("The clue was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The clue was not added correctly, please try again with the correct format");
        }

    }
    public void updateDecoration(Decoration decoration, long id){
        try{
            if(!decoration.isEmpty()||searchDuplicates(decoration)){
                decorationDAO.updateEntity(id, decoration);}
            else{System.out.println("The clue was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The clue was not added correctly, please try again with the correct format");
        }
    }
    public DecorationDTO readDecorationById(long id){
        Decoration readDecoration = null;
        try{
            decorationDAO.readEntity(id);
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The clue was not added correctly, please try again with the correct format");
        }
        return new DecorationDTO(readDecoration.getName(), readDecoration.getTheme().toString(),readDecoration.getPrice(),readDecoration.getIdRoom());
    }

    public List<DecorationDTO> readAllEntities() throws Exception {
        final List<Decoration> decorations = decorationDAO.readAllEntities();
        List<DecorationDTO> decorationsDTO = new ArrayList<>();
        for(Decoration decoration:decorations){
            decorationsDTO.add(new DecorationDTO(decoration.getName(),decoration.getTheme().getDescription(),decoration.getPrice(),decoration.getIdRoom()));
        }
        return decorationsDTO;
    }

    public void deleteClueById(long id) throws Exception {
        decorationDAO.deleteEntity(id);
    }
    public Optional<Decoration> getDecorationFromString(String name){
        return Optional.empty();
    }
}
