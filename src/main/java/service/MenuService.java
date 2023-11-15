package service;

import domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuService {

    //에피타이저 메뉴 리스트 문자열 반환
    public String appetizersMenu(Menu menu) {
        return generateMenuItems(menu.getAppetizers());
    }
    //메인 메뉴 리스트 문자열 반환
    public String mainsMenu(Menu menu) {
        return generateMenuItems(menu.getMains());
    }
    //디저트 메뉴 리스트 문자열 반환
    public String dessertsMenu(Menu menu) {
        return generateMenuItems(menu.getDesserts());
    }
    //음료 메뉴 리스트 문자열 반환
    public String beveragesMenu(Menu menu) {
        return generateMenuItems(menu.getBeverages());
    }
    //메뉴 리스트 생성후 반환
    private String generateMenuItems(Map<String, Integer> menuItems) {
        List<String> menuNames = new ArrayList<>(menuItems.keySet());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < menuNames.size(); i++) {
            String menuItemName = menuNames.get(i);
            String menuItemCost = String.format("%,d", menuItems.get(menuItemName));
            stringBuilder.append(menuItemName).append("(").append(menuItemCost).append(")");

            if (i < menuNames.size() - 1) stringBuilder.append(", ");
        }

        return stringBuilder.toString();
    }

}
