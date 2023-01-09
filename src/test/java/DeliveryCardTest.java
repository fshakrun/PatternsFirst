import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.UserInformation;
import ru.netology.data.UserInformation;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void shouldRegisterTheCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        UserInformation info = DataGenerator.Registration.generateByCard("ru");
        String firstDate = DataGenerator.generateTheDate(4);
        String secondDate = DataGenerator.generateTheDate(7);

        $("[data-test-id=city] input").setValue(info.getCity()); //город
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);//дата
        $("[data-test-id=date] input").setValue(firstDate);
        $("[data-test-id=name] input").setValue(info.getName());//фамилия и имя
        $("[data-test-id=phone] input").setValue(info.getPhone());//моб телефон
        $("[data-test-id=agreement] span").click();//галочка
        $(withText("Запланировать")).click();//забронировать
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно! Встреча успешно запланирована на "
                + firstDate), Duration.ofSeconds(15));

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);//дата
        $("[data-test-id=date] input").setValue(secondDate);
        $(withText("Запланировать")).click();
        $("[data-test-id=replan-notification]").shouldBe(Condition.visible).shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$(".button__text").find(exactText("Перепланировать")).click();
        $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Встреча успешно запланирована на " + secondDate),
                Duration.ofSeconds(15));

    }
}