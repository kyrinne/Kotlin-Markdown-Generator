package org.kyrinne.markdowngenerator.text

import org.junit.jupiter.api.Test
import org.kyrinne.markdowngenerator.text.quote.Quote

/**
 * Created by steppschuh on 15/12/2016.
 */
class QuoteTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val quote = "I am a blockquote.\nI may contain multiple lines."
        val text: Text = Quote(quote)
        println(text)
    }
}