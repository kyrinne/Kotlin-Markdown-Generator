package org.kyrinne.markdowngenerator.text.code

import net.steppschuh.markdowngenerator.text.Text
import org.junit.jupiter.api.Test

/**
 * Created by steppschuh on 15/12/2016.
 */
class CodeTest {
    @Test
    @Throws(Exception::class)
    fun example1() {
        val text: Text = Code("System.out.println(\"I am code\");")
        println(text)
    }
}