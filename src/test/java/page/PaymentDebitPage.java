package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentDebitPage {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement ownerField = $("fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private final SelenideElement cvccvvField = $("[placeholder='999']");
    private final SelenideElement continueButton  = $(byText("Продолжить"));

    private final SelenideElement successNotification  = $(".notification_status_ok");
    private final SelenideElement errorNotification  = $(".notification_status_error");
    private final SelenideElement emptyFieldMessage  = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement notFormatMessage  = $(byText("Неверный формат"));
    private final SelenideElement invalidExpirationMessage  = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement expirationMessage  = $(byText("Истёк срок действия карты"));

    public void fillingCardInfo (String cardNumber, String month, String year, String owner, String cvccvv) {
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvccvvField.setValue(cvccvv);
        continueButton.click();
    }

    public void checkSuccessNotification(){
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkErrorNotification(){
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkEmptyFieldMessage(){
        emptyFieldMessage.shouldBe(visible);
    }

    public void checkNotFormatMessage(){
        notFormatMessage.shouldBe(visible);
    }

    public void checkInvalidExpirationMessage(){
        invalidExpirationMessage.shouldBe(visible);
    }

    public void checkExpirationMessage(){
        expirationMessage.shouldBe(visible);
    }
}
