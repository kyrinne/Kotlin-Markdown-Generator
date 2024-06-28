package org.kyrinne.markdowngenerator.text.emphasis

import org.junit.jupiter.api.Test
import org.kyrinne.markdowngenerator.DummyObject

/**
 * Created by steppschuh on 15/12/2016.
 */
class StrikeThroughTextTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val text: org.kyrinne.markdowngenerator.text.Text = StrikeThroughText("I am strike-through text")
        println(text)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val text: org.kyrinne.markdowngenerator.text.Text = StrikeThroughText(DummyObject())
        println(text)
    }
}