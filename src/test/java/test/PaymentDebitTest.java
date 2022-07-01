package test;

import data.DBHelper;
import data.DataHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.PaymentDebitPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentDebitTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
        var dashboardPage = new DashboardPage();
        dashboardPage.getPaymentDebit();
    }

    @AfterEach
    void tearDown() {
        DBHelper.cleanTables();
    }

    //Номер карты

    @Test
    void shouldPaymentDebitApprovedCardTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkSuccessNotification();
        var paymentStatus = DBHelper.getPaymentDebitStatus();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPaymentDebitDeclinedCardTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getDeclinedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkErrorNotification();
        var paymentStatus = DBHelper.getPaymentDebitStatus();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPaymentDebitInvalidCardTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getInvalidCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkErrorNotification();
    }

    @Test
    void shouldPaymentDebitNullCardNumberTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getNullCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkErrorNotification();
    }

    @Test
    void shouldPaymentDebitEmptyCardNumberTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getEmptyCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkEmptyFieldMessage();
    }

    @Test
    void shouldPaymentDebitNotFormatCardNumberTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getNotFormatCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkNotFormatMessage();
    }

    // Месяц

    @Test
    void shouldPaymentDebitInvalidMonthTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getInvalidMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkInvalidExpirationMessage();
    }

    @Test
    void shouldPaymentDebitEmptyMonthTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getEmptyMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkEmptyFieldMessage();
    }

    @Test
    void shouldPaymentDebitNotFormatMonthTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getNotFormatMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkNotFormatMessage();
    }

    // Год

    @Test
    void shouldPaymentDebitLastYearTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getLastYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkExpirationMessage();
    }

    @Test
    void shouldPaymentDebitEmptyYearTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getEmptyYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkEmptyFieldMessage();
    }

    @Test
    void shouldPaymentDebitNotFormatYearTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getNotFormatYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkNotFormatMessage();
    }

    // Владелец

    @Test
    void shouldPaymentDebitOwnerRusTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getOwnerRus();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentDebitOwnerMinTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getOwnerMin();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentDebitOwnerSpecialSymbolsTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getOwnerSpecialSymbols();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentDebitOwnerNumbersTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getOwnerNumbers();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentDebitEmptyOwnerTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getEmptyOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkEmptyFieldMessage();
    }

    // CVC/CVV

    @Test
    void shouldPaymentDebitNotFormatCVCCVVTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getNotFormatCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentDebitEmptyCVCCVVTest() {
        var paymentDebitPage = new PaymentDebitPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getEmptyCVCCVV();
        paymentDebitPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentDebitPage.checkEmptyFieldMessage();
    }
}
