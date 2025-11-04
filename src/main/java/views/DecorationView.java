package views;

import servicelayer.DecorationService;

public class DecorationView {
    DecorationService service = new DecorationService();
    public DecorationView(){}

    public void showDecorations() throws Exception{
        System.out.println("<======== Decorations ========>");
      //  System.out.println(service.readAllEntities().toString());

    }
}
