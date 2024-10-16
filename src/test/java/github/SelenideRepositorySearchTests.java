package github;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearchTests {

    @Test
    void shouldFindSelenideRepositoryAtTheTop() {
        open("https://github.com/");
        $("[placeholder='Search or jump to...']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();

        // sleep(600_000);
    }
}
