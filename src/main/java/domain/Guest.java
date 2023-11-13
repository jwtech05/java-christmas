package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Guest {
    private int date;
    private Map<String,Integer> menu;
    public Guest(int date, Map<String,Integer> menu) {

        this.date = date;
        this.menu = menu;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Map<String,Integer> getMenu() {
        return menu;
    }

    public void setMenu(Map<String,Integer> menu) {
        this.menu = menu;
    }


}
