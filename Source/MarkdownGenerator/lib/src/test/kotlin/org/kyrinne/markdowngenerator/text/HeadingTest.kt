package org.kyrinne.markdowngenerator.text

import org.junit.jupiter.api.Test
import org.kyrinne.markdowngenerator.text.heading.Heading

/**
 * Created by steppschuh on 16/12/2016.
 */
class HeadingTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val text: Text = Heading("I am a heading")
        println(text)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        for (level in Heading.MINIMUM_LEVEL..Heading.MAXIMUM_LEVEL) {
            val text: Text = Heading("I am a heading with level $level", level)
            println(text)
        }
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example3() {
        val sb: java.lang.StringBuilder = java.lang.StringBuilder()
            .append(Heading("Heading with level 1", 1)).append(System.lineSeparator())
            .append(Heading("Heading with level 2", 2)).append(System.lineSeparator())
            .append(Heading("Heading with level 3", 3)).append(System.lineSeparator())
            .append(Heading("Heading with level 4", 4)).append(System.lineSeparator())
            .append(Heading("Heading with level 5", 5)).append(System.lineSeparator())
            .append(Heading("Heading with level 6", 6))

        println(sb)
    }
}