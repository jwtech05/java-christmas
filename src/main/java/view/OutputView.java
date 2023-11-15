package view;

public class OutputView {

    public void printNoticeAboutEvent() {
        System.out.println("******************************** 이벤트 주의 사항 안내 ********************************");
        System.out.println("1. 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
        System.out.println("2. 음료만 주문 시, 주문할 수 없습니다.");
        System.out.println("3. 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println();
    }

    public void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }


    public void printMenu(String guestOrderedMenu) {
        System.out.println("<주문 메뉴>");
        System.out.println(guestOrderedMenu);

    }

    public void printBeforeDiscountPrice(String calculatedPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(calculatedPrice);
        System.out.println();
    }

    public void printPresentation(String presentationResult) {
        System.out.println("<증정 메뉴>");
        System.out.println(presentationResult);
        System.out.println();
    }

    public void printEventHistory(String totalDiscountMessage) {
        System.out.println("<혜택 내역>");
        System.out.println(totalDiscountMessage);
    }

    public void printEveryDiscountPrice(String totalDiscountPriceMessage) {
        System.out.println("<총혜택 금액>");
        System.out.println(totalDiscountPriceMessage);
        System.out.println();
    }

    public void printAfterDiscountPrice(String afterDiscountPriceMessage) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(afterDiscountPriceMessage);
        System.out.println();
    }

    public void printEventBadge(String eventBadgeMessage) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventBadgeMessage);
        System.out.println();
    }
}
