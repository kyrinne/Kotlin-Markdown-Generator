package org.kyrinne.markdowngenerator.text

import org.junit.jupiter.api.Test
import org.kyrinne.markdowngenerator.DummyObject

/**
 * Created by steppschuh on 15/12/2016.
 */
class TextTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val text = Text("I am normal text")
        println(text)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val text = Text(DummyObject())
        println(text)
    }
}