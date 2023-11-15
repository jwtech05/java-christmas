package service;

import domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {

    EventService eventService = new EventService();
    Menu menu = new Menu();
    Map<String, Integer> guestMenu = new HashMap<>();
    Map<String, Integer> guestMenu2 = new HashMap<>();
    Map<String, Integer> guestMenu3 = new HashMap<>();
    Map<String, Integer> guestMenu4 = new HashMap<>();

    @Test
    void testOverPricePresentationEvent() {
        assertEquals("없음", eventService.overPricePresentationEvent(119999));
        assertEquals("샴페인 1개", eventService.overPricePresentationEvent(120001));
    }

    @Test
    void testDDayDiscountMessage() {
        assertEquals(" ", eventService.dDayDiscountMessage(26));
        assertEquals("크리스마스 디데이 할인 : -1,200원", eventService.dDayDiscountMessage(3));
        assertEquals("크리스마스 디데이 할인 : -3,400원", eventService.dDayDiscountMessage(25));
    }

    @Test
    void testWeekendOrNot() {
        guestMenu.put("티본스테이크",3);
        guestMenu.put("아이스크림",2);
        guestMenu.put("타파스",3);
        guestMenu.put("샴페인",1);

        guestMenu2.put("양송이수프",3);
        guestMenu2.put("제로콜라",1);

        assertTrue(eventService.weekendOrNot(11, guestMenu, menu).contains("평일"));
        assertTrue(eventService.weekendOrNot(15, guestMenu, menu).contains("주말"));
        assertTrue(eventService.weekendOrNot(11, guestMenu, menu).contains(" "));
        assertTrue(eventService.weekendOrNot(15, guestMenu, menu).contains(" "));
    }

    @Test
    void testStarDiscountMessage() {
        assertEquals("특별 할인 : -1,000원", eventService.starDiscountMessage(10));
        assertEquals(" ", eventService.starDiscountMessage(26));
    }

    @Test
    void testOverPricePresentationDiscountMessage() {
        assertEquals(" ", eventService.overPricePresentationDiscountMessage(119999));
        assertEquals("증정 이벤트 : -25,000원", eventService.overPricePresentationDiscountMessage(120001));
    }

    @Test
    void testTotalDiscountEventMessage() {
        guestMenu3.put("티본스테이크",3);
        guestMenu3.put("아이스크림",2);

        String christmasDiscount = "크리스마스 디데이 할인 : -1,200원";
        String weekDiscount = "평일 할인 : -4,046원";
        String starDiscount = "특별 할인 : -1,000원";
        String presentationDiscount = "증정 이벤트 : -25,000원";

        String allDiscounts = christmasDiscount + "\n" + weekDiscount + "\n" + starDiscount + "\n" + presentationDiscount;

        assertEquals("없음\n", eventService.totalDiscountEventMessage(3, 9000, guestMenu3, menu));
        assertEquals(allDiscounts+"\n", eventService.totalDiscountEventMessage(3, 175000, guestMenu3, menu));
    }

    @Test
    void testTotalDiscountPriceMessage() {
        guestMenu4.put("티본스테이크",3);
        guestMenu4.put("아이스크림",2);

        assertEquals("-31,246원", eventService.totalDiscountPriceMessage(3, 175000, guestMenu4, menu));
    }

    @Test
    void testEventBadgeMessage() {
        assertEquals("산타", eventService.eventBadgeMessage(6, 175000, guestMenu3, menu));
        assertEquals("없음", eventService.eventBadgeMessage(10, 0, guestMenu3, menu));
        assertEquals("없음", eventService.eventBadgeMessage(26, 15000, guestMenu2, menu));
    }
}