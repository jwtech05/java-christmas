package domain;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Integer> appetizers;
    private Map<String, Integer> mains;
    private Map<String, Integer> desserts;
    private Map<String, Integer> beverages;

    public Menu() {
        this.appetizers = new HashMap<>();
        this.mains = new HashMap<>();
        this.desserts = new HashMap<>();
        this.beverages = new HashMap<>();

        appetizersMenu();
        MainsMenu();
        dessertsMenu();
        beveragesMenu();
    }

    private void appetizersMenu() {
        appetizers.put("양송이수프", 6000);
        appetizers.put("타파스", 5500);
        appetizers.put("시저샐러드", 8000);
    }

    private void MainsMenu() {
        mains.put("티본스테이크", 55000);
        mains.put("바비큐립", 54000);
        mains.put("해산물파스타", 35000);
        mains.put("크리스마스파스타", 25000);
    }

    private void dessertsMenu() {
        desserts.put("초코케이크", 15000);
        desserts.put("아이스크림", 5000);
    }

    private void beveragesMenu() {
        beverages.put("제로콜라", 3000);
        beverages.put("레드와인", 60000);
        beverages.put("샴페인", 25000);
    }

    public Map<String, Integer> getAppetizers() {
        return appetizers;
    }

    public Map<String, Integer> getMains() {
        return mains;
    }

    public Map<String, Integer> getDesserts() {
        return desserts;
    }

    public Map<String, Integer> getBeverages() {
        return beverages;
    }


}
