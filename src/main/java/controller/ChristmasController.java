package controller;

import domain.Guest;
import service.EventService;
import service.GuestService;
import service.PriceService;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class ChristmasController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    GuestService guestService = new GuestService();
    PriceService priceService = new PriceService();
    EventService eventService = new EventService();
    Guest guest;

    public void run() {

        noticeAboutEvent();
        pickVisitDate();
        pickMenu();
        orderedMenu();
        beforeDiscountPrice();
        presentationMenu();
        eventHistory();
        everyEventDiscountPrice();
        afterDiscountPrice();
        eventBadge();
    }

    private void noticeAboutEvent() {
        outputView.printNoticeAboutEvent();
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

    private void orderedMenu() {
        String orderedMenu = guestService.guestOrderedMenuMessage(guest.getMenu());
        outputView.printMenu(orderedMenu);
    }

    private void beforeDiscountPrice() {
        String calculatedMenu = priceService.priceBeforeDiscountMessage(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        outputView.printBeforeDiscountPrice(calculatedMenu);
    }

    private void presentationMenu() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String presentationResult = eventService.overPricePresentationEvent(calculatedPrice);
        outputView.printPresentation(presentationResult);
    }

    private void eventHistory() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String totalDiscountEvent = eventService.totalDiscountEventMessage(guest.getDate(), calculatedPrice, guest.getMenu());
        outputView.printEventHistory(totalDiscountEvent);
    }

    private void everyEventDiscountPrice() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String everyDiscountPrice = eventService.totalDiscountPriceMessage(guest.getDate(), calculatedPrice, guest.getMenu());
        outputView.printEveryDiscountPrice(everyDiscountPrice);
    }

    private void afterDiscountPrice() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        int everyDiscountPrice = eventService.totalDiscountPrice(guest.getDate(), calculatedPrice, guest.getMenu());
        String afterDiscountEvent = priceService.priceAfterDiscountMessage(calculatedPrice, everyDiscountPrice);
        outputView.printAfterDiscountPrice(afterDiscountEvent);
    }

    private void eventBadge() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String eventBadgeEvent = eventService.eventBadgeMessage(guest.getDate(), calculatedPrice, guest.getMenu());
        outputView.printEventBadge(eventBadgeEvent);
    }

}
