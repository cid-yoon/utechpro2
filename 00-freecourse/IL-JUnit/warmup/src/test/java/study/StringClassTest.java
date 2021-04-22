package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;


public class StringClassTest {

    // (1,2) => 괄호를 제거하는 코드
    private String removeBrace(String src) {
        int startIndex = src.indexOf("(");
        int endIndex = src.lastIndexOf(")");

        if (startIndex == -1)
            throw new IllegalArgumentException("not found " + "(");

        if (endIndex == -1)
            throw new IllegalArgumentException("not found " + ")");

        int correctStartIndex = startIndex + 1;
        return src.substring(correctStartIndex, endIndex);
    }

    /**
     * 1,2를 ,로 split 했을 때 1과 2로 잘 분리되는지
     */
    @Test
    @DisplayName("콤마로 구분된 문자열을 문자열 배열을 생성하여 반환한다")
    void Should_ContainsStringElement_When_SplitCommaString() {

        // Given
        String src = "1,2";

        // When
        String[] dest = src.split(",");

        // Then
        assertThat(dest).contains("1", "2");
    }

    /**
     * 1을 ,로 split 했을때 1만 포함하는 배열이 반환되는지 학습
     */
    @Test
    @DisplayName("콤마가 없는 문자열이 제공되는 경우 하나의 문자열 배열만 반환한다")
    public void Should_ContainSingleStringElement_When_NotExistCommaString() {

        // Given
        String src = "1";

        // When
        String[] dest = src.split(",");

        // Then
        assertThat(dest).hasSize(1);
        assertThat(dest).containsExactly("1");
    }

    @Test
    @DisplayName("브레이스()로 감싼 문자열이 제공 되는 경우 브레이스를 제거한 문자열을 반환한다")
    public void Should_ContainsElementString_When_SplitWithRemoveBrace() {

        // Given
        String src = "(1,2)";

        // When
        String removeBraceString = removeBrace(src);
        String[] dest = removeBraceString.split(",");

        // Then
        assertThat(dest).contains("1", "2");
        assertThat(dest).doesNotContain("(", ")");
    }

    @Test
    @DisplayName("문자열의 특정 위치의 값을 가져온다")
    public void Should_GetCharacter_When_PickStringPosition() {

        // Given
        String src = "abc";
        int pickPosition = 1;

        // When
        char pickChar = src.charAt(pickPosition);

        // Then
        assertThat('b').isEqualTo(pickChar);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    @DisplayName("문자열의 위치를 벗어난 값을 입력하면 IndexOutOfBound 예외를 전달한다")
    public void Should_ThrowIndexOutOfBound_When_IndexOutOfBoundPickStringPosition() {

        // Given
        String src = "abc";
        int pickPosition = 10;

        // When & Then
        assertThatThrownBy(() -> src.charAt(pickPosition))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: " + pickPosition);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    @DisplayName("문자열의 위치를 벗어난 값을 입력하면 IndexOutOfBound 예외를 전달한다")
    public void Should_ThrowIndexOutOfBoundMatch_When_IndexOutOfBoundPickStringPosition() {

        // Given
        String src = "abc";
        int pickPosition = 10;

        // When & Then
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() ->
                        src.charAt(pickPosition)
                )
                .withMessageMatching("String index out of range: \\d+");
    }


}
