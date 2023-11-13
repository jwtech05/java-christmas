package service;

public class EventService {

    public String overPricePresentationEvent(int totalPrice){
        if(totalPrice >= 120000) {
            return "샴페인 1개";
        }
        return "없음";
    }
}
