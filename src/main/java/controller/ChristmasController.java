package controller;

import domain.Guest;
import domain.Menu;
import service.EventService;
import service.GuestService;
import service.MenuService;
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
    MenuService menuService = new MenuService();
    Guest guest;
    Menu menu;

    public void run() {

        noticeAboutEvent();
        noticeMenu();
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

    private void noticeMenu() {
        menu = new Menu();
        String appetizersInfo = menuService.appetizersMenu(menu);
        String mainsInfo = menuService.mainsMenu(menu);
        String dessertsInfo = menuService.dessertsMenu(menu);
        String beveragesInfo = menuService.beveragesMenu(menu);

        outputView.printNoticeMenu(appetizersInfo, mainsInfo, dessertsInfo, beveragesInfo);

        menuService.beveragesMenu(menu);
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
        String totalDiscountEvent = eventService.totalDiscountEventMessage(guest.getDate(), calculatedPrice, guest.getMenu(),menu);
        outputView.printEventHistory(totalDiscountEvent);
    }

    private void everyEventDiscountPrice() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String everyDiscountPrice = eventService.totalDiscountPriceMessage(guest.getDate(), calculatedPrice, guest.getMenu(),menu);
        outputView.printEveryDiscountPrice(everyDiscountPrice);
    }

    private void afterDiscountPrice() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        int everyDiscountPrice = eventService.totalDiscountPrice(guest.getDate(), calculatedPrice, guest.getMenu(),menu);
        String afterDiscountEvent = priceService.priceAfterDiscountMessage(calculatedPrice, everyDiscountPrice);
        outputView.printAfterDiscountPrice(afterDiscountEvent);
    }

    private void eventBadge() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu()), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String eventBadgeEvent = eventService.eventBadgeMessage(guest.getDate(), calculatedPrice, guest.getMenu(),menu);
        outputView.printEventBadge(eventBadgeEvent);
    }

}
