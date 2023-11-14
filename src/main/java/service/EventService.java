package service;

import domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventService {

    Menu menu = new Menu();
    // 증정 메뉴 여부 판단하에 메세지 반환
    public String overPricePresentationEvent(int totalPrice){
        if(totalPrice >= 120000) {
            return "샴페인 1개";
        }
        return "없음";
    }
    // 크리스마스 디데이 할인 금액 반환
    private int dDayDiscount(int date){
        if(date > 25) return 0;

        int originDiscount = 1000;
        originDiscount += ((date-1) * 100);

        return originDiscount;
    }
    // 크리스마스 디데이 할인 금액 메세지 반환
    public String dDayDiscountMessage(int date){
        int discount = dDayDiscount(date);
        String message =  String.format("크리스마스 디데이 할인 : -%,d원",discount);
        if(discount == 0){
            return " ";
        }
        return message;
    }
    // 주말인지 평일인지 구별하여 해당 금액 반환
    private int weekendOrNotPrice(int date, Map<String ,Integer> guestMenu){
        int[] weekend = {1,2,8,9,15,16,22,23,29,30};
        for(int i=0; i<weekend.length; i++){
            if(weekend[i] == date){
                return weekEndDiscount(guestMenu);
            }
        }
        return weekDayDiscount(guestMenu);
    }
    //주말인지 평일인지 구별하여 해당 메세지 반환
    public String weekendOrNot(int date, Map<String ,Integer> guestMenu){
        int[] weekend = {1,2,8,9,15,16,22,23,29,30};
        for(int i=0; i<weekend.length; i++){
            if(weekend[i] == date){
                return weekEndDiscountMessage(guestMenu);
            }
        }
        return weekDayDiscountMessage(guestMenu);
    }
    // 평일 할인 금액 반환
    private int weekDayDiscount(Map<String ,Integer> guestMenu){
        int cnt = 0;
        List<String> guestMenuName =  new ArrayList<>(guestMenu.keySet());
        List<Integer> guestMenuQuantity = new ArrayList<>(guestMenu.values());
        for(int i=0; i<guestMenuName.size(); i++){
            if(menu.getDesserts().containsKey(guestMenuName.get(i))) cnt += guestMenuQuantity.get(i);
        }

        return cnt * 2023;
    }
    // 평일 할인 금액 메세지 반환
    public String weekDayDiscountMessage(Map<String ,Integer> guestMenu){
        int discount = weekDayDiscount(guestMenu);
        String message = String.format("평일 할인 : -%,d원",discount);
        if(discount == 0){
            return " ";
        }
        return message;
    }
    // 주말 할인 금액 반환
    private int weekEndDiscount(Map<String ,Integer> guestMenu){
        int cnt = 0;
        List<String> guestMenuName =  new ArrayList<>(guestMenu.keySet());
        List<Integer> guestMenuQuantity = new ArrayList<>(guestMenu.values());
        for(int i=0; i<guestMenuName.size(); i++){
            if(menu.getMains().containsKey(guestMenuName.get(i))) cnt += guestMenuQuantity.get(i);
        }

        return cnt * 2023;
    }
    // 주말 할인 금액 메세지 반환
    public String weekEndDiscountMessage(Map<String ,Integer> guestMenu){
        int discount = weekEndDiscount(guestMenu);
        String message = String.format("주말 할인 : -%,d원",discount);
        if(discount == 0){
            return " ";
        }
        return message;
    }
    // 특별 할인 금액 반환
    private int starDiscount(int date){
        int[] starDay = {3,10,17,24,25,31};
        for(int i=0; i<starDay.length; i++){
            if(starDay[i] == date) return 1000;
        }
        return 0;
    }
    // 특별 할인 금액 메세지 반환
    public String starDiscountMessage(int date){
        int discount = starDiscount(date);
        String message = String.format("특별 할인 : -%,d원",discount);
        if(discount == 0){
            return " ";
        }
        return message;
    }
    // 증정 할인 금액 반환
    private int overPricePresentationDiscount(int price){
        if(price >= 120000) {
            return 25000;
        }
        return 0;
    }
    // 증정 할인 금액 메세지 반환
    public String overPricePresentationDiscountMessage(int price){
        int discount = overPricePresentationDiscount(price);
        if(discount == 0){
            return " ";
        }
        return String.format("증정 이벤트 : -%,d원",discount);
    }
    // 모든 할인 메세지 반환
    public String totalDiscountEventMessage(int date, int price, Map<String ,Integer> guestMenu){
        StringBuilder stringBuilder = new StringBuilder();

        if(!dDayDiscountMessage(date).equals(" ")) stringBuilder.append(dDayDiscountMessage(date)).append("\n");
        if(!weekendOrNot(date,guestMenu).equals(" ")) stringBuilder.append(weekendOrNot(date,guestMenu)).append("\n");
        if(!starDiscountMessage(date).equals(" ")) stringBuilder.append(starDiscountMessage(date)).append("\n");
        if(!overPricePresentationDiscountMessage(price).equals(" ")) stringBuilder.append(overPricePresentationDiscountMessage(price)).append("\n");
        String totalMessage = stringBuilder.toString();
        if(totalMessage.isBlank()){
            return "없음\n";
        }
        return totalMessage;
    }
    //모든 할인 금액 반환
    public int totalDiscountPrice(int date, int price, Map<String ,Integer> guestMenu){
        int totalDiscount = 0;

        totalDiscount += dDayDiscount(date);
        totalDiscount += weekendOrNotPrice(date, guestMenu);
        totalDiscount += starDiscount(date);
        totalDiscount += overPricePresentationDiscount(price);

        return totalDiscount;
    }

    public String totalDiscountPriceMessage(int date, int price, Map<String ,Integer> guestMenu){
        int totalDiscount = totalDiscountPrice(date, price, guestMenu);
        if(totalDiscount == 0) {
            return "0원";
        }
        return String.format("-%,d원",totalDiscount);
    }

    public String eventBadgeMessage(int date, int price, Map<String ,Integer> guestMenu){
        int totalPrice = totalDiscountPrice(date,price,guestMenu);

        if(totalPrice >= 20000) return "산타";
        if(totalPrice >= 10000) return "트리";
        if(totalPrice >= 5000) return "별";

        return "없음";
    }
}
