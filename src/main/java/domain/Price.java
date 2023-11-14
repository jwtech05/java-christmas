package domain;

public class Price {
    private int totalPrice;
    private int dDayDiscount;
    public Price() {
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getdDayDiscount() {
        return dDayDiscount;
    }

    public void setdDayDiscount(int dDayDiscount) {
        this.dDayDiscount = dDayDiscount;
    }
}
