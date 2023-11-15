package service;

import java.util.List;

public class PriceService {

    // 할인 전 총주문 금액 반환
    public int priceBeforeDiscount(List<Integer> prices, List<Integer> cnt) {
        int totalPrice = 0;

        for (int i = 0; i < prices.size(); i++) {
            totalPrice += (prices.get(i) * cnt.get(i));
        }

        return totalPrice;
    }

    // 계산 내용 문자열로 변환
    public String priceBeforeDiscountMessage(List<Integer> prices, List<Integer> cnt) {
        int totalPrice = priceBeforeDiscount(prices, cnt);

        return String.format("%,d원", totalPrice);
    }

    // 할인 후 총주문 금액 반환
    private int priceAfterDiscount(int beforeDiscountPrice, int everyDiscountPrice) {
        int afterTotalPrice = beforeDiscountPrice - everyDiscountPrice;

        return afterTotalPrice;
    }

    public String priceAfterDiscountMessage(int beforeDiscountPrice, int everyDiscountPrice) {
        int afterTotalPrice = priceAfterDiscount(beforeDiscountPrice, everyDiscountPrice);
        if (beforeDiscountPrice >= 120000) {
            afterTotalPrice += 25000;
        }
        return String.format("%,d원", afterTotalPrice);
    }


}
