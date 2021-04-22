package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloTddTest {

    @Test
    @DisplayName("안녕 TDD")
    public void hello(){

        String hello = "hello TDD";
        assertThat(hello).isEqualTo("hello TDD");
    }
}
