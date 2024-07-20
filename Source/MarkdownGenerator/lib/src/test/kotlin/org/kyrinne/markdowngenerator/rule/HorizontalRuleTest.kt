package org.kyrinne.markdowngenerator.rule

import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.IntRange
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.test.assertEquals

/**
 * Created by steppschuh on 16/12/2016.
 */
class HorizontalRuleTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun `default horizontal rule consists of three hyphens`() {
        val defaultHorizontalRule = HorizontalRule()
        assertEquals(
            expected = "-".repeat(3),
            actual = defaultHorizontalRule.toString(),
            message = "Default horizontal rule should be three hyphens"
        )
    }

    // TODO: add reasonable constraints to HorizontalRule and add them here
    @Property
    fun `custom horizontal rules are formatted as expected`(@ForAll @IntRange(min = -1, max = 1000) length: Int, @ForAll character: Character) {
        val expectedLength = max(length, HorizontalRule.MINIMUM_LENGTH)
        assertEquals(
            expected = character.value.toString().repeat(expectedLength),
            actual = HorizontalRule(length = expectedLength, character = character).toString(),
        )
    }
}