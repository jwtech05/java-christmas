package view;

public class OutputView {

    public void printHello(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }


    public void printMenu(String guestOrderedMenu) {
        System.out.println("<주문 메뉴>");
        System.out.println(guestOrderedMenu);

    }
    public void printBeforeDiscountPrice(String calculatedPrice){
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(calculatedPrice);
        System.out.println();
    }

    public void printPresentation(String presentationResult) {
        System.out.println("<증정 메뉴>");
        System.out.println(presentationResult);
        System.out.println();
    }

    public void printEventHistory(){
        System.out.println("<혜택 내역>");
    }
}
