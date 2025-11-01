package servicelayer;


import daomodel.DaoTicket;
import dtomodel.TicketDTO;
import model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private final DaoTicket daoTicket = new DaoTicket();

    public TicketService(){}

    public boolean searchDuplicates(Ticket ticket){
        return daoTicket.duplicate(ticket);
    }

    public void addClue(Ticket ticket){
        try{
            if(!ticket.isEmpty()||!searchDuplicates(ticket)){
                daoTicket.insertEntity(ticket);}
            else{System.out.println("The ticket was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The ticket was not added correctly, please try again with the correct format");
        }

    }
    public void updateClue(Ticket ticket){
        try{
            if(!ticket.isEmpty()||searchDuplicates(ticket)){
                daoTicket.updateEntity(ticket.getIdTicket(),ticket);}
            else{System.out.println("The ticket was empty, therefore it could not be inserted to DB.");}
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The ticket was not added correctly, please try again with the correct format");
        }
    }
    public TicketDTO readItemById(long id){
        Ticket readTicket = null;
        try{
            readTicket = daoTicket.readEntity(id);
        }
        catch(Exception sqle1){
            System.err.println(sqle1.getMessage());
            System.out.println("The ticket was not added correctly, please try again with the correct format");
        }
        return new TicketDTO(readTicket.getTicketTitle(),readTicket.getPlayerName(),readTicket.getDateSold().toString(),readTicket.getPricePaid());
    }

    public List<TicketDTO> getTickets() throws Exception {
        final List<Ticket> tickets = daoTicket.readAllEntities();
        List<TicketDTO> ticketDTO = new ArrayList<>();
        for(Ticket ticket:tickets){
            ticketDTO.add(new TicketDTO(ticket.getTicketTitle(),ticket.getPlayerName(),ticket.getDateSold().toString(),ticket.getPricePaid()));
        }
        return ticketDTO;
    }
    public TicketDTO getTicket(Long id) throws Exception {
        final Ticket ticket = daoTicket.readEntity(id);

        return new TicketDTO(ticket.getTicketTitle(),ticket.getPlayerName(),ticket.getDateSold().toString(),ticket.getPricePaid());
    }
}
