import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest extends TestBase {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 89;
    private static final String ISSUE_TITLE = "Another test issue";

    @DisplayName("Проверка названия Issue в репозитории с Selenide Listener")
    @Test
    void selenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $("[href='/" + REPOSITORY + "']").click();
        $("#issues-tab").click();
        $("#issue_" + ISSUE + "_link").should(Condition.exist);
        $("#issue_" + ISSUE + "_link").shouldHave(text(ISSUE_TITLE));
    }
}
