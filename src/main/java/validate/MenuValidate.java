package validate;

import domain.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuValidate {

    private String orderMenu;
    private Menu menu;

    public MenuValidate(String orderMenu) {
        patternValidate(orderMenu);
        this.orderMenu = orderMenu;
        menu = new Menu();
    }

    private void existValidate(String orderMenu){

        InputMenuOrganize(orderMenu);

    }

    private void patternValidate(String orderMenu){
        Pattern pattern = Pattern.compile("([가-힣]+-\\d+,?)+");
        Matcher matcher = pattern.matcher(orderMenu);

        if(!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public Map<String, Integer> InputMenuOrganize(String menu) {
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
