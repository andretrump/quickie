package org.pinkcrazyunicorn.quickie.adapters.mappers.profile;

import org.junit.Test;
import org.pinkcrazyunicorn.quickie.domain.profile.Opinion;

public class OpinionMapperTest {
    @Test
    public void testFoodgasmFromString() {
        performFromStringTest("Foodgasm", Opinion.Foodgasm);
    }

    @Test
    public void testLoveFromString() {
        performFromStringTest("Love", Opinion.Love);
    }

    @Test
    public void testLikeFromString() {
        performFromStringTest("Like", Opinion.Like);
    }

    @Test
    public void testIndifferentFromString() {
        performFromStringTest("Indifferent", Opinion.Indifferent);
    }

    @Test
    public void testDislikeFromString() {
        performFromStringTest("Dislike", Opinion.Dislike);
    }

    @Test
    public void testHateFromString() {
        performFromStringTest("Hate", Opinion.Hate);
    }

    @Test
    public void testDealbreakerFromString() {
        performFromStringTest("Dealbreaker", Opinion.Dealbreaker);
    }

    @Test
    public void testNonexistentFromString() {
        OpinionMapper codeUnderTest = new OpinionMapper();

        Opinion actual = codeUnderTest.fromString("foodgasm");

        assert actual == null;
    }

    private void performFromStringTest(String from, Opinion shouldBe) {
        OpinionMapper codeUnderTest = new OpinionMapper();

        Opinion actual = codeUnderTest.fromString(from);

        assert actual.equals(shouldBe);
    }
}
