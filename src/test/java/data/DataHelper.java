package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    // Номер карты

    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    public static String getInvalidCardNumber() {
        return ("4444 4444 4444 4443");
    }

    public static String getNullCardNumber() {
        return ("0000 0000 0000 0000");
    }

    public static String getEmptyCardNumber() {
        return ("");
    }

    public static String getNotFormatCardNumber() {
        return ("4444 4444 4444 444");
    }

    // Месяц

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getInvalidMonth() {
        return "00";
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getNotFormatMonth() {
        return "1";
    }

    // Год

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getNotFormatYear() {
        return "1";
    }

    // Владелец

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getOwnerRus() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getOwnerMin() {
        return "Y";
    }

    public static String getOwnerSpecialSymbols() {
        return "!@#$%^&*()_+<>";
    }

    public static String getOwnerNumbers() {
        return "0123";
    }
    public static String getEmptyOwner() {
        return "";
    }

    // CVC/CVV

    public static String getValidCVCCVV() {
        return "987";
    }

    public static String getNotFormatCVCCVV() {
        return "98";
    }

    public static String getEmptyCVCCVV() {
        return "";
    }
}


