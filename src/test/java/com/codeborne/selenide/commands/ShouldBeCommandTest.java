package com.codeborne.selenide.commands;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.readonly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

final class ShouldBeCommandTest {
  private final SelenideElement proxy = mock();
  private final WebElementSource locator = mock();
  private final ShouldBe command = new ShouldBe();
  private final WebElement webElement = mock();

  @BeforeEach
  void setup() {
    when(locator.getWebElement()).thenReturn(webElement);
  }

  @Test
  void checksEveryConditionFromGivenParameters() {
    SelenideElement returnedElement = command.execute(proxy, locator, new Object[]{disabled, readonly});
    assertThat(returnedElement).isEqualTo(proxy);
    verify(locator).checkCondition("be ", disabled, false);
    verify(locator).checkCondition("be ", readonly, false);
  }

  @Test
  void noArgs() {
    command.execute(proxy, locator, new Object[0]);
    verifyNoMoreInteractions(locator);
  }
}
