package org.kyrinne.markdowngenerator.text.emphasis

import org.junit.jupiter.api.Test
import org.kyrinne.markdowngenerator.DummyObject

/**
 * Created by steppschuh on 15/12/2016.
 */
class BoldTextTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val text: org.kyrinne.markdowngenerator.text.Text = BoldText("I am bold text")
        println(text)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val text: org.kyrinne.markdowngenerator.text.Text = BoldText(DummyObject())
        println(text)
    }
}