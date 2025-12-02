package test

import Day2InvalidIdFinder
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse


class InvalidNumbersText {

    @Test
    fun numbersInvalid() {
        assert(Day2InvalidIdFinder().isInvalid(123123))
        assert(Day2InvalidIdFinder().isInvalid(12341234))
        assert(Day2InvalidIdFinder().isInvalid(11))
        assert(Day2InvalidIdFinder().isInvalid(121212121212121212))
        assertFalse(Day2InvalidIdFinder().isInvalid(1))
    }

    @Test
    fun numbersValid() {
        assertFalse(Day2InvalidIdFinder().isInvalid(98798))
        assertFalse(Day2InvalidIdFinder().isInvalid(1231234))
        assertFalse(Day2InvalidIdFinder().isInvalid(123441234))
    }
}