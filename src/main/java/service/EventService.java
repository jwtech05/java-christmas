package service;

import domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventService {

    Menu menu = new Menu();

    public String overPricePresentationEvent(int totalPrice){
        if(totalPrice >= 120000) {
            return "샴페인 1개";
        }
        return "없음";
    }

    public int dDayDiscountEvent(int Date){
        int originDiscount = 1000;
        originDiscount += ((Date-1) * 100);

        return originDiscount;
    }

    public String dDayDiscountEventMessage(int discountPrice){

        String message =  String.format("크리스마스 디데이 할인 : -%d원",discountPrice);

        return message;
    }
    //주말인지 평일인지 구별
    public String weekendOrNot(int date, Map<String ,Integer> guestMenu){
        int[] weekend = {1,2,8,9,15,16,22,23,29,30};
        for(int i=0; i<weekend.length; i++){
            if(weekend[i] == date){
                return weekEndDiscountMessage(guestMenu);
            }
        }
        return weekDayDiscountMessage(guestMenu);
    }

    public int weekDayDiscount(Map<String ,Integer> guestMenu){
        int cnt = 0;
        List<String> guestMenuName =  new ArrayList<>(guestMenu.keySet());
        List<Integer> guestMenuQuantity = new ArrayList<>(guestMenu.values());
        for(int i=0; i<guestMenuName.size(); i++){
            if(menu.getDesserts().containsKey(guestMenuName.get(i))) cnt += guestMenuQuantity.get(i);
        }

        return cnt * 2023;
    }

    public String weekDayDiscountMessage(Map<String ,Integer> guestMenu){
        int discount = weekDayDiscount(guestMenu);
        String message = String.format("평일 할인 : -%,d원",discount);

        return message;
    }

    public int weekEndDiscount(Map<String ,Integer> guestMenu){
        int cnt = 0;
        List<String> guestMenuName =  new ArrayList<>(guestMenu.keySet());
        List<Integer> guestMenuQuantity = new ArrayList<>(guestMenu.values());
        for(int i=0; i<guestMenuName.size(); i++){
            if(menu.getMains().containsKey(guestMenuName.get(i))) cnt += guestMenuQuantity.get(i);
        }

        return cnt * 2023;
    }

    public String weekEndDiscountMessage(Map<String ,Integer> guestMenu){
        int discount = weekEndDiscount(guestMenu);
        String message = String.format("주말 할인 : -%,d원",discount);

        return message;
    }
}
