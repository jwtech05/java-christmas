package controller;

import domain.Guest;
import service.GuestService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class ChristmasController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    GuestService guestService = new GuestService();
    Guest guest;

    public void run(){
        pickVisitDate();
        pickMenu();

    }
    private void pickVisitDate() {
        outputView.printHello();
        int dateInput = inputView.readDate();
        guest = new Guest(dateInput, null);
    }

    private void pickMenu() {
        String menu = inputView.readMenu();
        Map<String,Integer> guestMenu = guestService.guestMenuUpdate(menu);
        guest.setMenu(guestMenu);
    }


}
