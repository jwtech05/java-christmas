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
    public int dDayDiscountEvent(int date){
        int originDiscount = 1000;
        originDiscount += ((date-1) * 100);

        return originDiscount;
    }
    // 크리스마스 디데이 할인 금액 메세지 반환
    public String dDayDiscountEventMessage(int date){
        int discount = dDayDiscountEvent(date);
        String message =  String.format("크리스마스 디데이 할인 : -%,d원",discount);
        if(discount == 0){
            return " ";
        }
        return message;
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
    public int weekDayDiscount(Map<String ,Integer> guestMenu){
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
    public int weekEndDiscount(Map<String ,Integer> guestMenu){
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
    public int starDiscount(int date){
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
    // 증정 메뉴 금액 메세지 반환
    public String overPricePresentationEventMessage(int price){
        String presentation = overPricePresentationEvent(price);
        if(presentation.equals("없음")){
            return " ";
        }
        return String.format("증정 이벤트 : -25,000원");
    }
    // 모든 할인 메세지 반환
    public String totalDiscountEventMessage(int date, int price, Map<String ,Integer> guestMenu){
        StringBuilder stringBuilder = new StringBuilder();

        if(!dDayDiscountEventMessage(date).equals(" ")) stringBuilder.append(dDayDiscountEventMessage(date)).append("\n");
        if(!weekendOrNot(date,guestMenu).equals(" ")) stringBuilder.append(weekendOrNot(date,guestMenu)).append("\n");
        if(!starDiscountMessage(date).equals(" ")) stringBuilder.append(starDiscountMessage(date)).append("\n");
        if(!overPricePresentationEventMessage(price).equals(" ")) stringBuilder.append(overPricePresentationEventMessage(price)).append("\n");
        String totalMessage = stringBuilder.toString();
        if(totalMessage.isBlank()){
            return "없음\n";
        }
        return totalMessage;
    }
}
