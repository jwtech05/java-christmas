package service;

import domain.Menu;

import java.util.*;

public class GuestService {

    //고객이 주문한 메뉴 Map 으로 정리 후 반환
    public Map<String, Integer> guestMenuUpdate(String menu) {
        Map<String, Integer> menuOrganize = new HashMap<>();
        String[] menuItems = menu.split(",");

        for (String menuItem : menuItems) {
            String[] oneMenu = menuItem.split("-");
            String foodName = oneMenu[0].trim();
            int quantity = Integer.parseInt(oneMenu[1].trim());

            menuOrganize.put(foodName, quantity);
        }
        return menuOrganize;
    }

    //고객이 주문한 메뉴 리스트 문자열 제공
    public String guestOrderedMenuMessage(Map<String, Integer> menu) {
        List<String> orderNames = new ArrayList<>(menu.keySet());
        List<Integer> orderQuantity = new ArrayList<>(menu.values());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < menu.size(); i++) {
            stringBuilder.append(orderNames.get(i)).append(" ").append(orderQuantity.get(i)).append("개\n");
        }

        return stringBuilder.toString();
    }

    //고객이 주문한 메뉴의 개수 리스트 반환
    public List<Integer> guestOrderedMenuCnt(Map<String, Integer> guestMenu) {
        Map<String, Integer> orderedMenus = guestMenu;
        List<Integer> orderedCnt = new ArrayList<>(orderedMenus.values());

        return orderedCnt;
    }

    //고객이 주문한 메뉴의 가격 리스트 반환
    public List<Integer> guestOrderedMenuPrice(Map<String, Integer> guestMenu) {
        Map<String, Integer> orderedMenus = guestMenu;
        List<String> orderedNames = new ArrayList<>(orderedMenus.keySet());
        List<Integer> orderedMenuPrices = new ArrayList<>();

        for (int i = 0; i < orderedNames.size(); i++) {
            orderedMenuPrices.add(menuPrices(orderedNames.get(i)));
        }

        return orderedMenuPrices;
    }

    //주문 메뉴의 가격 반환
    public int menuPrices(String guestOrderedMenu) {
        Menu menu = new Menu();

        if (menu.getAppetizers().containsKey(guestOrderedMenu)) return menu.getAppetizers().get(guestOrderedMenu);
        if (menu.getMains().containsKey(guestOrderedMenu)) return menu.getMains().get(guestOrderedMenu);
        if (menu.getBeverages().containsKey(guestOrderedMenu)) return menu.getBeverages().get(guestOrderedMenu);
        if (menu.getDesserts().containsKey(guestOrderedMenu)) return menu.getDesserts().get(guestOrderedMenu);

        return 0;
    }

    public int priceBeforeDiscount(List<Integer> prices, List<Integer> cnt) {
        int totalPrice = 0;
        for (int i = 0; i < prices.size(); i++) {
            totalPrice += (prices.get(i) * cnt.get(i));
        }

        return totalPrice;
    }

}