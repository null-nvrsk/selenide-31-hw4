package github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WikiPageTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserPosition = "0x0";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void softAccertionsPageHaveJUnit5() {
        // Откройте страницу Selenide в Github
        open("/selenide/selenide");

        // Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".wiki-more-pages-link button").click(); // нажать "Show 3 more pages..."
        $$("#wiki-pages-box summary a").findBy(text("SoftAssertions")).shouldBe(visible)
                                                                                .click();

        // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        // Проверка, что есть заголовок "Using JUnit5 extend test class"
        SelenideElement h4JUnit5Element = $$("h4.heading-element").findBy(text("Using JUnit5"));
        h4JUnit5Element.shouldBe(visible);

        // Проверка, что после заголовка идет блок с java-кодом JUnit5
        h4JUnit5Element.parent().sibling(0)
                .$(".highlight-source-java pre").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})"));
    }
}