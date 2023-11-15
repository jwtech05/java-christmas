package service;

import domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventService {

    // 증정 메뉴 여부 판단하에 메세지 반환
    public String overPricePresentationEvent(int totalPrice) {
        // 이벤트 주의 사항 - 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다
        if (totalPrice >= 120000) {
            return "샴페인 1개";
        }
        return "없음";
    }

    // 크리스마스 디데이 할인 금액 반환
    private int dDayDiscount(int date) {
        if (date > 25) return 0;

        int originDiscount = 1000;
        originDiscount += ((date - 1) * 100);

        return originDiscount;
    }

    // 크리스마스 디데이 할인 금액 메세지 반환
    public String dDayDiscountMessage(int date) {
        int discount = dDayDiscount(date);
        String message = String.format("크리스마스 디데이 할인 : -%,d원", discount);
        if (discount == 0) {
            return " ";
        }
        return message;
    }

    // 주말인지 평일인지 구별하여 해당 금액 반환
    private int weekendOrNotPrice(int date, Map<String, Integer> guestMenu, Menu menu) {
        int[] weekend = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30};
        for (int i = 0; i < weekend.length; i++) {
            if (weekend[i] == date) {
                return weekEndDiscount(guestMenu, menu);
            }
        }
        return weekDayDiscount(guestMenu, menu);
    }

    //주말인지 평일인지 구별하여 해당 메세지 반환
    public String weekendOrNot(int date, Map<String, Integer> guestMenu, Menu menu) {
        int[] weekend = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30};
        for (int j : weekend) {
            if (j == date) {
                return weekEndDiscountMessage(guestMenu, menu);
            }
        }
        return weekDayDiscountMessage(guestMenu, menu);
    }

    // 평일 할인 금액 반환
    private int weekDayDiscount(Map<String, Integer> guestMenu, Menu menu) {
        int cnt = 0;
        List<String> guestMenuName = new ArrayList<>(guestMenu.keySet());
        List<Integer> guestMenuQuantity = new ArrayList<>(guestMenu.values());
        for (int i = 0; i < guestMenuName.size(); i++) {
            if (menu.getDesserts().containsKey(guestMenuName.get(i))) cnt += guestMenuQuantity.get(i);
        }

        return cnt * 2023;
    }

    // 평일 할인 금액 메세지 반환
    public String weekDayDiscountMessage(Map<String, Integer> guestMenu, Menu menu) {
        int discount = weekDayDiscount(guestMenu, menu);
        String message = String.format("평일 할인 : -%,d원", discount);
        if (discount == 0) {
            return " ";
        }
        return message;
    }

    // 주말 할인 금액 반환
    private int weekEndDiscount(Map<String, Integer> guestMenu, Menu menu) {
        int cnt = 0;
        List<String> guestMenuName = new ArrayList<>(guestMenu.keySet());
        List<Integer> guestMenuQuantity = new ArrayList<>(guestMenu.values());
        for (int i = 0; i < guestMenuName.size(); i++) {
            if (menu.getMains().containsKey(guestMenuName.get(i))) cnt += guestMenuQuantity.get(i);
        }

        return cnt * 2023;
    }

    // 주말 할인 금액 메세지 반환
    public String weekEndDiscountMessage(Map<String, Integer> guestMenu, Menu menu) {
        int discount = weekEndDiscount(guestMenu, menu);
        String message = String.format("주말 할인 : -%,d원", discount);
        if (discount == 0) {
            return " ";
        }
        return message;
    }

    // 특별 할인 금액 반환
    private int starDiscount(int date) {
        int[] starDay = {3, 10, 17, 24, 25, 31};
        for (int i = 0; i < starDay.length; i++) {
            if (starDay[i] == date) return 1000;
        }
        return 0;
    }

    // 특별 할인 금액 메세지 반환
    public String starDiscountMessage(int date) {
        int discount = starDiscount(date);
        String message = String.format("특별 할인 : -%,d원", discount);
        if (discount == 0) {
            return " ";
        }
        return message;
    }

    // 증정 할인 금액 반환
    private int overPricePresentationDiscount(int price) {
        if (price >= 120000) {
            return 25000;
        }
        return 0;
    }

    // 증정 할인 금액 메세지 반환
    public String overPricePresentationDiscountMessage(int price) {
        int discount = overPricePresentationDiscount(price);
        if (discount == 0) {
            return " ";
        }
        return String.format("증정 이벤트 : -%,d원", discount);
    }

    // 모든 할인 메세지 반환
    public String totalDiscountEventMessage(int date, int price, Map<String, Integer> guestMenu, Menu menu) {
        StringBuilder stringBuilder = new StringBuilder();

        if (!dDayDiscountMessage(date).equals(" ")) stringBuilder.append(dDayDiscountMessage(date)).append("\n");
        if (!weekendOrNot(date, guestMenu,menu).equals(" ")) stringBuilder.append(weekendOrNot(date, guestMenu,menu)).append("\n");
        if (!starDiscountMessage(date).equals(" ")) stringBuilder.append(starDiscountMessage(date)).append("\n");
        if (!overPricePresentationDiscountMessage(price).equals(" ")) stringBuilder.append(overPricePresentationDiscountMessage(price)).append("\n");

        String totalMessage = stringBuilder.toString();
        // 이벤트 주의 사항 - 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다
        if (totalMessage.isBlank() || price < 10000) return "없음\n";

        return totalMessage;
    }

    //모든 할인 금액 반환
    public int totalDiscountPrice(int date, int price, Map<String, Integer> guestMenu, Menu menu) {
        int totalDiscount = 0;

        totalDiscount += dDayDiscount(date);
        totalDiscount += weekendOrNotPrice(date, guestMenu,menu);
        totalDiscount += starDiscount(date);
        totalDiscount += overPricePresentationDiscount(price);
        // 이벤트 주의 사항 - 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
        if(price < 10000) totalDiscount = 0;

        return totalDiscount;
    }

    public String totalDiscountPriceMessage(int date, int price, Map<String, Integer> guestMenu, Menu menu) {
        int totalDiscount = totalDiscountPrice(date, price, guestMenu, menu);
        if (totalDiscount == 0) {
            return "0원";
        }
        return String.format("-%,d원", totalDiscount);
    }

    public String eventBadgeMessage(int date, int price, Map<String, Integer> guestMenu, Menu menu) {
        int totalSalePrice = totalDiscountPrice(date, price, guestMenu, menu);
        // 이벤트 주의 사항 - 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다
        if (price < 10000) return "없음";
        if (totalSalePrice >= 20000) return "산타";
        if (totalSalePrice >= 10000) return "트리";
        if (totalSalePrice >= 5000) return "별";

        return "없음";
    }
}
