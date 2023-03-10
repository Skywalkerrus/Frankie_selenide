package com.codeborne.selenide.impl;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

final class FilteringCollectionTest {
  @Test
  void getActualElement() {
    WebElement mockedWebElement1 = mock();
    WebElement mockedWebElement2 = mock();

    when(mockedWebElement1.isDisplayed()).thenReturn(false);
    when(mockedWebElement2.isDisplayed()).thenReturn(true);

    CollectionSource mockedCollection = mock();
    when(mockedCollection.getElements()).thenReturn(asList(mockedWebElement1, mockedWebElement2));
    FilteringCollection filteringCollection = new FilteringCollection(mockedCollection, Condition.visible);

    List<WebElement> actualElements = filteringCollection.getElements();
    assertThat(actualElements)
      .hasSize(1);
    assertThat(actualElements.get(0))
      .isEqualTo(mockedWebElement2);
  }

  @Test
  void description() {
    CollectionSource mockedCollection = mock();
    when(mockedCollection.description()).thenReturn("Collection description");
    FilteringCollection filteringCollection = new FilteringCollection(mockedCollection, Condition.visible);
    assertThat(filteringCollection.description())
      .isEqualTo("Collection description.filter(visible)");
  }
}
