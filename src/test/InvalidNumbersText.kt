package test

import Day2Solver
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse


class InvalidNumbersText {

    @Test
    fun numbersInvalid() {
        assert(Day2Solver().isInvalid(123123))
        assert(Day2Solver().isInvalid(12341234))
        assert(Day2Solver().isInvalid(11))
        assert(Day2Solver().isInvalid(121212121212121212))
        assertFalse(Day2Solver().isInvalid(1))
    }

    @Test
    fun numbersValid() {
        assertFalse(Day2Solver().isInvalid(98798))
        assertFalse(Day2Solver().isInvalid(1231234))
        assertFalse(Day2Solver().isInvalid(123441234))
    }
}