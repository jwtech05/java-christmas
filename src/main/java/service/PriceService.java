package service;

import java.util.List;

public class PriceService {

    //할인 전 총주문 금액 계산
    public int priceBeforeDiscount(List<Integer> prices, List<Integer> cnt){
        int totalPrice = 0;
        for(int i=0; i<prices.size(); i++){
            totalPrice += (prices.get(i) * cnt.get(i));
        }

        return totalPrice;
    }

    //계산 내용 문자열로 변환
    public String priceBeforeDiscountMessage(List<Integer> prices, List<Integer> cnt){

        int totalPrice = priceBeforeDiscount(prices, cnt);

        return totalPrice + "원";
    }


}
