package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement debitButton = $$(".button__text").first();
    private final SelenideElement creditButton = $$(".button__text").last();
    private final SelenideElement headingDebit  = $(byText("Оплата по карте"));
    private final SelenideElement headingCredit  = $(byText("Кредит по данным карты"));

    public PaymentDebitPage getPaymentDebit() {
        debitButton.click();
        headingDebit.shouldBe(visible);
        return new PaymentDebitPage();
    }

    public PaymentCreditPage getPaymentCredit() {
        creditButton.click();
        headingCredit.shouldBe(visible);
        return new PaymentCreditPage();
    }
}
