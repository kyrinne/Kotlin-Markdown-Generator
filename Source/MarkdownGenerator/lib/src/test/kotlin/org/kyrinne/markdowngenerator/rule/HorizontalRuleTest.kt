package org.kyrinne.markdowngenerator.rule

import org.junit.jupiter.api.Test

/**
 * Created by steppschuh on 16/12/2016.
 */
class HorizontalRuleTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val horizontalRule = HorizontalRule()
        println(horizontalRule)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val horizontalRule = HorizontalRule(20, HorizontalRule.ASTERISK)
        println(horizontalRule)
    }
}