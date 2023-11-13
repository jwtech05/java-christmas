package service;

import java.util.*;

public class GuestService {

    public GuestService() {
    }

    public void guestDateUpdate(int date) {

    }

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
}