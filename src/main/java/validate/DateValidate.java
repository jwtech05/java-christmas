package validate;

import java.util.List;
import java.util.StringTokenizer;

public class DateValidate {

    String dateToCheck;

    public DateValidate(String dateToCheck) {
        dateRangeValidate(dateToCheck);
        this.dateToCheck = dateToCheck;
    }

    private void dateRangeValidate(String date) {
        int BeforeCheckDate = Integer.parseInt(date);
        if (BeforeCheckDate < 1  || BeforeCheckDate > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
