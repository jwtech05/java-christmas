package view;

import camp.nextstep.edu.missionutils.Console;
import validate.DateValidate;

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
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        return input;
    }
}
