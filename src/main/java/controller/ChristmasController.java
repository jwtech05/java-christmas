package controller;

import domain.Guest;
import domain.Price;
import service.EventService;
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
    EventService eventService = new EventService();
    Guest guest;
    Price price;

    public void run() {
        pickVisitDate();
        pickMenu();
        printOrderedMenu();
        beforeDiscountPrice();
        presentationMenu();
        eventHistory();
    }

    private void pickVisitDate() {
        outputView.printHello();
        int dateInput = inputView.readDate();
        guest = new Guest(dateInput, null);
    }

    private void pickMenu() {
        String menu = inputView.readMenu();
        Map<String, Integer> guestMenu = guestService.guestMenuUpdate(menu);
        guest.setMenu(guestMenu);
    }

    private void printOrderedMenu() {
        String orderedMenu = guestService.guestOrderedMenuMessage(guest.getMenu());
        outputView.printMenu(orderedMenu);
    }

    private void beforeDiscountPrice() {
        List<Integer> orderedPrices = guestService.guestOrderedMenuPrice(guest.getMenu());
        List<Integer> orderedCnt = guestService.guestOrderedMenuCnt(guest.getMenu());
        String calculatedMenu = priceService.priceBeforeDiscountMessage(orderedPrices, orderedCnt);
        outputView.printBeforeDiscountPrice(calculatedMenu);
    }

    private void presentationMenu() {
        List<Integer> orderedPrices = guestService.guestOrderedMenuPrice(guest.getMenu());
        List<Integer> orderedCnt = guestService.guestOrderedMenuCnt(guest.getMenu());
        int calculatedPrice = priceService.priceBeforeDiscount(orderedPrices, orderedCnt);
        String presentationResult = eventService.overPricePresentationEvent(calculatedPrice);
        outputView.printPresentation(presentationResult);
    }

    private void eventHistory() {
        String dDayDiscountEvent = eventService.dDayDiscountEventMessage(guest.getDate());
        String dayDiscountEvent = eventService.weekendOrNot(guest.getDate(), guest.getMenu());
        String starDiscountEvent = eventService.starDiscountMessage(guest.getDate());
    }

}
