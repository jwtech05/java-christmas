package validate;

import domain.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuValidate {

    private String orderMenu;
    private Menu menu = new Menu();


    public MenuValidate(String orderMenu) {

        patternValidate(orderMenu);
        duplicateValidate(orderMenu);
        singleMenuNumValidate(orderMenu);
        existValidate(orderMenu);
        onlyDrinkBeveragesMenuValidate(orderMenu);
        limitTotalMenuNumValidate(orderMenu);

        this.orderMenu = orderMenu;
    }

    //메뉴 형식과 다른경우 에러 발생
    private void patternValidate(String orderMenu) {
        Pattern pattern = Pattern.compile("([가-힣]+-\\d+,?)+");
        Matcher matcher = pattern.matcher(orderMenu);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    //메뉴 중복이 있을 경우 에러 발생
    private void duplicateValidate(String orderMenu) {
        Map<String, Integer> inputOrders = inputMenuOrganize(orderMenu);
        List<String> orderNames = new ArrayList<>(inputOrders.keySet());
        for (int i = 0; i < orderNames.size() - 1; i++) {
            for (int j = i + 1; j < orderNames.size(); j++) {
                if (orderNames.get(i).equals(orderNames.get(j))) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }
            }
        }
    }

    //한 메뉴의 개수가 0이거나 그 이하일 경우 에러 발생
    private void singleMenuNumValidate(String orderMenu) {
        Map<String, Integer> inputOrders = inputMenuOrganize(orderMenu);
        List<Integer> orderQuantity = new ArrayList<>(inputOrders.values());
        for (int i = 0; i < orderQuantity.size(); i++) {
            int quantity = orderQuantity.get(i);
            if (quantity <= 0) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    //메뉴에 존재하지 않는 메뉴 일시 에러 발생
    private void existValidate(String orderMenu) {
        Map<String, Integer> inputOrders = inputMenuOrganize(orderMenu);
        List<String> orderNames = new ArrayList<>(inputOrders.keySet());
        for (String orderName : orderNames) {
            if (!checkMenusContain(orderName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    // 이벤트 주의 사항 - 음료만 주문시 에러 발생
    private void onlyDrinkBeveragesMenuValidate(String orderMenu){
        Map<String, Integer> inputOrders = inputMenuOrganize(orderMenu);
        List<String> orderNames = new ArrayList<>(inputOrders.keySet());
        int checkMenu = 0;
        for (String orderName : orderNames) {
            if ((menu.getAppetizers().containsKey(orderName) || menu.getMains().containsKey(orderName) || menu.getDesserts().containsKey(orderName))){
                checkMenu++;
            }
        }
        if(checkMenu == 0) throw new IllegalArgumentException("[ERROR] 이벤트 주의 사항 - 음료만 주문 시, 주문할 수 없습니다.");
    }

    private void limitTotalMenuNumValidate(String orderMenu){
        Map<String, Integer> inputOrders = inputMenuOrganize(orderMenu);
        List<Integer> orderQuantity = new ArrayList<>(inputOrders.values());
        int quantity = 0;
        for (int i = 0; i < orderQuantity.size(); i++) {
            quantity += orderQuantity.get(i);
        }
        if(quantity > 20) throw new IllegalArgumentException("[ERROR] 이벤트 주의 사항 - 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    private boolean checkMenusContain(String orderName) {

        if (menu.getAppetizers().containsKey(orderName)) return true;
        if (menu.getMains().containsKey(orderName)) return true;
        if (menu.getBeverages().containsKey(orderName)) return true;
        if (menu.getDesserts().containsKey(orderName)) return true;

        return false;
    }

    private Map<String, Integer> inputMenuOrganize(String menu) {
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
