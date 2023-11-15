package service;

import domain.Menu;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceTest {

    GuestService guestService = new GuestService();
    Menu menu = new Menu();
    @Test
    void testGuestMenuUpdate() {
        String orderedMenu = "티본스테이크-2, 타파스-1, 제로콜라-3";
        Map<String, Integer> menuOrganize = new LinkedHashMap<>();
        menuOrganize.put("티본스테이크", 2);
        menuOrganize.put("타파스", 1);
        menuOrganize.put("제로콜라", 3);

        Map<String, Integer> result = guestService.guestMenuUpdate(orderedMenu);

        assertEquals(menuOrganize, result);
    }

    @Test
    void testGuestOrderedMenuMessage() {
        Map<String, Integer> menuOrganize = new LinkedHashMap<>();
        menuOrganize.put("티본스테이크", 2);
        menuOrganize.put("타파스", 1);
        menuOrganize.put("제로콜라", 3);

        String expectedMessage = "티본스테이크 2개\n타파스 1개\n제로콜라 3개\n";
        String result = guestService.guestOrderedMenuMessage(menuOrganize);

        assertEquals(expectedMessage, result);
    }

    @Test
    void testGuestOrderedMenuCnt() {
        Map<String, Integer> menu = new LinkedHashMap<>();
        menu.put("티본스테이크", 2);
        menu.put("타파스", 1);
        menu.put("제로콜라", 3);

        List<Integer> result = guestService.guestOrderedMenuCnt(menu);

        assertEquals(Arrays.asList(2, 1, 3), result);
    }

    @Test
    void testGuestOrderedMenuPrice() {
        Map<String, Integer> orderedMenu = new LinkedHashMap<>();
        orderedMenu.put("티본스테이크", 2);
        orderedMenu.put("타파스", 1);
        orderedMenu.put("제로콜라", 3);

        List<Integer> result = guestService.guestOrderedMenuPrice(orderedMenu,menu);

        assertEquals(Arrays.asList(55000, 5500, 3000), result);
    }

}