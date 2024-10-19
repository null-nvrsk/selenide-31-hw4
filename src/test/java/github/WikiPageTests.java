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
    void softAssertionsPageHaveJUnit5Test() {
        open("/selenide/selenide");
        $("#wiki-tab").click();

        $(".wiki-more-pages-link button").click();
        $$("#wiki-pages-box summary a").findBy(text("SoftAssertions")).shouldBe(visible).click();

        SelenideElement h4JUnit5Element = $$("h4.heading-element").findBy(text("Using JUnit5"));
        h4JUnit5Element.shouldBe(visible);
        h4JUnit5Element.parent().sibling(0) // Проверка, что после заголовка JUnit5 идет блок с java-кодом JUnit5
                .$(".highlight-source-java pre").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})"));
    }
}