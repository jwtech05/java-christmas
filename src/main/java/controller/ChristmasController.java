package controller;

import domain.Guest;
import service.GuestService;
import service.PriceService;
import view.InputView;
import view.OutputView;

import javax.print.attribute.IntegerSyntax;
import java.util.List;
import java.util.Map;

public class ChristmasController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    GuestService guestService = new GuestService();
    PriceService priceService = new PriceService();
    Guest guest;

    public void run(){
        pickVisitDate();
        pickMenu();
        printOrderedMenu();
        beforeDiscountPrice();
        presentationMenu();
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

    private void printOrderedMenu(){
        String orderedMenu = guestService.guestOrderedMenuMessage(guest.getMenu());
        outputView.printMenu(orderedMenu);
    }

    private void beforeDiscountPrice() {
        List<Integer> orderedPrices = guestService.guestOrderedMenuPrice(guest.getMenu());
        List<Integer> orderedCnt = guestService.guestOrderedMenuCnt(guest.getMenu());
        String calculatedMenu =priceService.priceBeforeDiscountMessage(orderedPrices,orderedCnt);
        outputView.printBeforeDiscountPrice(calculatedMenu);
    }

    private void presentationMenu(){

    }
}
