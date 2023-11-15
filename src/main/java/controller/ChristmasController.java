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

    // 이벤트 주의 사항 안내
    private void noticeAboutEvent() {
        outputView.printNoticeAboutEvent();
    }

    // 메뉴판 출력
    private void noticeMenu() {
        menu = new Menu();

        String appetizersInfo = menuService.appetizersMenu(menu);
        String mainsInfo = menuService.mainsMenu(menu);
        String dessertsInfo = menuService.dessertsMenu(menu);
        String beveragesInfo = menuService.beveragesMenu(menu);

        outputView.printNoticeMenu(appetizersInfo, mainsInfo, dessertsInfo, beveragesInfo);

        menuService.beveragesMenu(menu);
    }

    // 방문일 선택
    private void pickVisitDate() {
        outputView.printHello();

        int dateInput = inputView.readDate();

        guest = new Guest(dateInput, null);
    }

    // 주문 메뉴 선택
    private void pickMenu() {
        String menu = inputView.readMenu();
        Map<String, Integer> guestMenu = guestService.guestMenuUpdate(menu);

        guest.setMenu(guestMenu);
    }

    // 주문한 메뉴 출력
    private void orderedMenu() {
        String orderedMenu = guestService.guestOrderedMenuMessage(guest.getMenu());

        outputView.printMenu(orderedMenu);
    }

    // 할인 전 주문 금액 출력
    private void beforeDiscountPrice() {
        String calculatedMenu = priceService.priceBeforeDiscountMessage(guestService.guestOrderedMenuPrice(guest.getMenu(), menu), guestService.guestOrderedMenuCnt(guest.getMenu()));

        outputView.printBeforeDiscountPrice(calculatedMenu);
    }

    // 초과 금액 증정 이벤트 출력
    private void presentationMenu() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu(), menu), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String presentationResult = eventService.overPricePresentationEvent(calculatedPrice);

        outputView.printPresentation(presentationResult);
    }

    // 전체 이벤트 적용 내역 출력
    private void eventHistory() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu(), menu), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String totalDiscountEvent = eventService.totalDiscountEventMessage(guest.getDate(), calculatedPrice, guest.getMenu(), menu);

        outputView.printEventHistory(totalDiscountEvent);
    }

    // 각 이벤트에 따른 할인 금액 출력
    private void everyEventDiscountPrice() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu(), menu), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String everyDiscountPrice = eventService.totalDiscountPriceMessage(guest.getDate(), calculatedPrice, guest.getMenu(), menu);

        outputView.printEveryDiscountPrice(everyDiscountPrice);
    }

    // 할인 후 총 주문 금액 출력
    private void afterDiscountPrice() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu(), menu), guestService.guestOrderedMenuCnt(guest.getMenu()));
        int everyDiscountPrice = eventService.totalDiscountPrice(guest.getDate(), calculatedPrice, guest.getMenu(), menu);
        String afterDiscountEvent = priceService.priceAfterDiscountMessage(calculatedPrice, everyDiscountPrice);

        outputView.printAfterDiscountPrice(afterDiscountEvent);
    }

    // 이벤트에 따른 뱃지 출력
    private void eventBadge() {
        int calculatedPrice = priceService.priceBeforeDiscount(guestService.guestOrderedMenuPrice(guest.getMenu(), menu), guestService.guestOrderedMenuCnt(guest.getMenu()));
        String eventBadgeEvent = eventService.eventBadgeMessage(guest.getDate(), calculatedPrice, guest.getMenu(), menu);

        outputView.printEventBadge(eventBadgeEvent);
    }

    // 프로그램 실행 메서드
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

}
