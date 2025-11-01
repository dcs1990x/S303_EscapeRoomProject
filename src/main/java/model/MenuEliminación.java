package model;

import model.MenuLecturaLois;
import servicelayer.ClueService;
import servicelayer.ItemService;
import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.util.List;

public class MenuEliminación {
    ClueService clueService = new ClueService();
    MenuLecturaLois menuLectura = new MenuLecturaLois();
    public MenuEliminación(){}

    public void logicaMenuEliminacion(){
        deleteClue();
    }
            //ChooseRoom elegir que borrar de ella
    private void deleteClue()  {
        //Mostramos todas las clues
        menuLectura.readClue();

        int id = 1; //aquí iría el scanner pidiendo que se elija la clave que quiere borrar.
        try {
            clueService.deleteClueById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        menuLectura.readClue();

    }

        //Borrar room
        //Borrar objeto
        //Borrar pista

/*
    private int chooseRoom(EscapeRoomManager escapeRoomManager) throws NoSuchObjectException {
        escapeRoomManager.getEscapeRoomsList();



        //retorna el id
        //Listar todas las salas en la base de datos con un service.
        //Preguntarle al usuario que id de habitación querrá borrar


    }
*/
    private void chooseItemToDelete(EscapeRoomManager escapeRoomManager){
        //Listar todas las salas en la base de datos.
        //Elegir por nombre o id la sala a eliminar.
    }

    private void chooseClueToDelete(EscapeRoomManager escapeRoomManager){
        //Listar todas las salas en la base de datos.
        //Elegir por nombre o id la sala a eliminar.
    }


    private void deleteRoom(){

    }


    private void deleteItem(){

    }

}
