package service;

import domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuService {


    public String appetizersMenu(Menu menu) {
        return generateMenuItems(menu, menu.getAppetizers());
    }

    public String mainsMenu(Menu menu) {
        return generateMenuItems(menu, menu.getMains());
    }

    public String dessertsMenu(Menu menu) {
        return generateMenuItems(menu, menu.getDesserts());
    }

    public String beveragesMenu(Menu menu) {
        return generateMenuItems(menu, menu.getBeverages());
    }

    private String generateMenuItems(Menu menu, Map<String, Integer> menuItems) {
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
