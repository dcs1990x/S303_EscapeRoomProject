package views;

import servicelayer.ClueService;

public class ClueView {
    ClueService service = new ClueService();
    public ClueView(){}

    public void showClues() throws Exception{
        System.out.println("<======== CLUES ========>");
        System.out.println(service.getClues().toString());

    }


}
