import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        //$("[href='/" + repo + "']").click();
        //$("[href='/" + repo + "']").click();
        $("[href*='/" + repo + "']").click();

    }

    @Step("Открываем таб Issues")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public void shouldSeeIssueWithNumber(int issue) {
        $("#issue_" + issue).should(Condition.exist);
    }

    @Step("Проверяем заголовок {issueTitle} у Issue с номером {issue}")
    public void issueShouldHaveTitle(int issue, String issueTitle) {
        $("#issue_" + issue + "_link").shouldHave(text(issueTitle));
    }
}
