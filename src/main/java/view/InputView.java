package view;

import camp.nextstep.edu.missionutils.Console;
import validate.DateValidate;
import validate.MenuValidate;

public class InputView {

    public int readDate() {
        String input;

        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        while (true) {
            try {
                input = Console.readLine();
                DateValidate dateValidate = new DateValidate(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return Integer.parseInt(input);
    }

    public String readMenu() {
        String input;
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        while (true) {
            try {
                input = Console.readLine();
                MenuValidate menuValidate = new MenuValidate(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        return input;
    }
}
