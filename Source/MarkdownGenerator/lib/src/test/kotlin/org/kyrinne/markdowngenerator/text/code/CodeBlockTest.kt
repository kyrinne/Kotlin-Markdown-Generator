package org.kyrinne.markdowngenerator.text.code

import org.junit.jupiter.api.Test
import org.kyrinne.markdowngenerator.text.Text

/**
 * Created by steppschuh on 15/12/2016.
 */
class CodeBlockTest {
    @Test
    @Throws(Exception::class)
    fun example1() {
        val code = "System.out.println(\"I am a code block\");"
        val text: Text = CodeBlock(code)
        println(text)
    }

    @Test
    @Throws(Exception::class)
    fun example2() {
        val code = """
            // notice this new line
            System.out.println("Hello");
            """.trimIndent()
        val text: Text = CodeBlock(code, "Java")
        println(text)
    }
}