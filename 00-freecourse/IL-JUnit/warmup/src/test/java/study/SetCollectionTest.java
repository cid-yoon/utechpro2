package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SetCollectionTest {

    private Set<Integer> numbers;

    @BeforeEach
    void setup() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
    }

    @Test
    @DisplayName("컬랙션의 크기를 확인한다")
    void Should_Success_Then_CallSizeMethod() {

        // Given
        HashSet<Integer> setCollection = new HashSet<>(numbers);

        // When
        setCollection.add(100);

        // Then
        assertThat(numbers).isNotExactlyInstanceOf(Set.class);
        assertThat(numbers.size()).isEqualTo(4);

        assertThat(setCollection).isNotExactlyInstanceOf(Set.class);
        assertThat(setCollection.size()).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("컬랙션의 내용이 모두 일치하는지 확인한다")
    void Should_AllContains_When_Compare(int num) {

        // Given
        HashSet<Integer> setCollection = new HashSet<>(numbers);

        // When & Then
        assertThat(setCollection.contains(num)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3:4,5"}, delimiter = ':')
    @DisplayName("컬랙션의 내용의 일부 일치를 판단한다")
    void Should_AllContains_When_Compare(String correctValue, String incorrectValue) {

        assertThat(allContains(correctValue)).isTrue();
        assertThat(notContains(incorrectValue)).isTrue();

    }

    private boolean notContains(String stringValues) {

        Set<Integer> numSet = toIntSet(stringValues);
        return !numbers.containsAll(numSet);

    }

    private boolean allContains(String stringValues) {

        Set<Integer> numSet = toIntSet(stringValues);

        return numbers.containsAll(numSet);
    }

    private Set<Integer> toIntSet(String correctValue) {
        return Arrays.stream(correctValue.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    @DisplayName("컬랙션의 내용의 일부 일치를 판단한다")
    @ParameterizedTest(name = "{index} ==> {0} : {1}")
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    void toLowerCase_ShouldGenerateTheExpectedLowercaseValue(String input, String expected) {

        String actualValue = input.toLowerCase();
        assertThat(expected).isEqualTo(actualValue);

    }


}
