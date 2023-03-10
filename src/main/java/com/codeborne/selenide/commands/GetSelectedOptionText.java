package com.codeborne.selenide.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.JavaScript;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class GetSelectedOptionText implements Command<String> {
  private final JavaScript js = new JavaScript("get-selected-option-text.js");

  @Override
  @CheckReturnValue
  @Nonnull
  public String execute(SelenideElement proxy, WebElementSource selectElement, @Nullable Object[] args) {
    return execute(selectElement.driver(), selectElement.getWebElement());
  }

  @Nonnull
  @CheckReturnValue
  public String execute(Driver driver, WebElement webElement) {
    return js.executeOrFail(driver, webElement);
  }
}
