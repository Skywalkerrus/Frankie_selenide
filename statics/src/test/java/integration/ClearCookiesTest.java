package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

final class ClearCookiesTest extends IntegrationTest {
  @BeforeEach
  void addCookiesBeforeTest() throws MalformedURLException {
    open("/start_page.html");

    String domain = new URL(getWebDriver().getCurrentUrl()).getHost();
    getWebDriver().manage().addCookie(new Cookie("username", "John Doe", domain, "/", null));
    Set<Cookie> cookieSet = getWebDriver().manage().getCookies();
    assumeThat(cookieSet.isEmpty()).isFalse();
  }

  @Test
  void clearCookieTest() {
    clearBrowserCookies();
    Set<Cookie> cookieSet = getWebDriver().manage().getCookies();
    assertThat(cookieSet).isEmpty();
  }
}
