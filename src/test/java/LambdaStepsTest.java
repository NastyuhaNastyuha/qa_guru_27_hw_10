import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest extends TestBase {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 89;
    private static final String ISSUE_TITLE = "Another test issue";

    @DisplayName("Проверка названия Issue в репозитории с Lambda шагами")
    @Test
    void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $("[href='/" + REPOSITORY + "']").click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            //String issueSelector = "#issue" + ISSUE;
            $("#issue_" + ISSUE).should(Condition.exist);
        });
        step("Проверяем заголовок \"" + ISSUE_TITLE + "\" у Issue с номером " + ISSUE, () -> {
            $("#issue_" + ISSUE + "_link").shouldHave(text(ISSUE_TITLE));
        });
    }
}
