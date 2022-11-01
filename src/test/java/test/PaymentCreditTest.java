package test;

import data.DBHelper;
import data.DataHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.PaymentCreditPage;
import page.PaymentDebitPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCreditTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
        var dashboardPage = new DashboardPage();
        dashboardPage.getPaymentCredit();
    }

    @AfterEach
    void tearDown() {
        DBHelper.cleanTables();
    }

    //Номер карты

    @Test
    void shouldPaymentCreditApprovedCardTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkSuccessNotification();
        var paymentStatus = DBHelper.getPaymentCreditStatus();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPaymentCreditDeclinedCardTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getDeclinedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkErrorNotification();
        var paymentStatus = DBHelper.getPaymentCreditStatus();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPaymentCreditInvalidCardTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getInvalidCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkErrorNotification();
    }

    @Test
    void shouldPaymentCreditNullCardNumberTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getNullCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkErrorNotification();
    }

    @Test
    void shouldPaymentCreditEmptyCardNumberTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getEmptyCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkEmptyFieldMessage();
    }

    @Test
    void shouldPaymentCreditNotFormatCardNumberTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getNotFormatCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkNotFormatMessage();
    }

    // Месяц

    @Test
    void shouldPaymentCreditInvalidMonthTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getInvalidMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkInvalidExpirationMessage();
    }

    @Test
    void shouldPaymentCreditEmptyMonthTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getEmptyMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkEmptyFieldMessage();
    }

    @Test
    void shouldPaymentCreditNotFormatMonthTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getNotFormatMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkNotFormatMessage();
    }

    // Год

    @Test
    void shouldPaymentCreditLastYearTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getLastYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkExpirationMessage();
    }

    @Test
    void shouldPaymentCreditEmptyYearTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getEmptyYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkEmptyFieldMessage();
    }

    @Test
    void shouldPaymentCreditNotFormatYearTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getNotFormatYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkNotFormatMessage();
    }

    // Владелец

    @Test
    void shouldPaymentCreditOwnerRusTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getOwnerRus();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentCreditOwnerMinTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getOwnerMin();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentCreditOwnerSpecialSymbolsTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getOwnerSpecialSymbols();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentCreditOwnerNumbersTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getOwnerNumbers();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentCreditEmptyOwnerTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getEmptyOwner();
        var cvccvv = DataHelper.getValidCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkEmptyFieldMessage();
    }

    // CVC/CVV

    @Test
    void shouldPaymentCreditNotFormatCVCCVVTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getNotFormatCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkNotFormatMessage();
    }

    @Test
    void shouldPaymentCreditEmptyCVCCVVTest() {
        var paymentCreditPage = new PaymentCreditPage();
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getCurrentMonth();
        var year = DataHelper.getCurrentYear();
        var owner = DataHelper.getValidOwner();
        var cvccvv = DataHelper.getEmptyCVCCVV();
        paymentCreditPage.fillingCardInfo(cardNumber, month, year, owner, cvccvv);
        paymentCreditPage.checkEmptyFieldMessage();
    }
}
