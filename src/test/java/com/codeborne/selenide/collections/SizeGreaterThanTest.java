package com.codeborne.selenide.collections;

import com.codeborne.selenide.Mocks;
import com.codeborne.selenide.ex.ListSizeMismatch;
import com.codeborne.selenide.impl.CollectionSource;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

final class SizeGreaterThanTest {
  @Test
  void applyWithEmptyList() {
    assertThat(new SizeGreaterThan(10).test(emptyList()))
      .isFalse();
  }

  @Test
  void applyWithWrongSizeList() {
    assertThat(new SizeGreaterThan(10).test(singletonList(mock())))
      .isFalse();
  }

  @Test
  void applyWithCorrectSizeGreaterThan() {
    assertThat(new SizeGreaterThan(1).test(asList(mock(), mock())))
      .isTrue();
  }

  @Test
  void failMethod() {
    CollectionSource collection = Mocks.mockCollection("Collection description");

    assertThatThrownBy(() ->
      new SizeGreaterThan(10).fail(collection,
        emptyList(),
        new Exception("Exception message"),
        10000))
      .isInstanceOf(ListSizeMismatch.class)
      .hasMessageStartingWith(
        String.format("List size mismatch: expected: > 10, actual: 0, collection: Collection description%nElements: []"));
  }

  @Test
  void testToString() {
    assertThat(new SizeGreaterThan(10))
      .hasToString("size > 10");
  }
}
