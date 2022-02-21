package org.pinkcrazyunicorn.quickie.adapters.mappers.profile;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.pinkcrazyunicorn.quickie.domain.profile.Opinion;

import java.util.stream.Stream;

public class OpinionMapperTest {
    @ParameterizedTest
    @ValueSource(strings = {"foodgasm", "ReallyLike", "Nothing", "Null", "Dont'tKnow"})
    public void shouldNotGetOpinionFromInvalidString(String from) {
        OpinionMapper codeUnderTest = new OpinionMapper();

        Opinion actual = codeUnderTest.fromString(from);

        assertThat(actual).isNull();
    }

    @ParameterizedTest
    @MethodSource("getSuccessfulPairs")
    public void shouldGetCorrectOpinionFromString(String from, Opinion shouldBe) {
        OpinionMapper codeUnderTest = new OpinionMapper();

        Opinion actual = codeUnderTest.fromString(from);

        assertThat(actual).isEqualTo(shouldBe);
    }

    private static Stream<Arguments> getSuccessfulPairs() {
        return Stream.of(
                Arguments.of("Foodgasm", Opinion.Foodgasm),
                Arguments.of("Love", Opinion.Love),
                Arguments.of("Like", Opinion.Like),
                Arguments.of("Indifferent", Opinion.Indifferent),
                Arguments.of("Dislike", Opinion.Dislike),
                Arguments.of("Hate", Opinion.Hate),
                Arguments.of("Dealbreaker", Opinion.Dealbreaker)
        );
    }
}
