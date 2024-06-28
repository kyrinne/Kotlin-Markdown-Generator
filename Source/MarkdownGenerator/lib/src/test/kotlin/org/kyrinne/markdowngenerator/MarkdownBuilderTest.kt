package org.kyrinne.markdowngenerator

import org.junit.jupiter.api.Test
import org.kyrinne.markdowngenerator.Markdown.bold
import org.kyrinne.markdowngenerator.Markdown.italic
import org.kyrinne.markdowngenerator.Markdown.task

/**
 * Created by steppschuh on 23/12/2016.
 */
class MarkdownBuilderTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val builder = org.kyrinne.markdowngenerator.text.TextBuilder()
            .heading("Markdown Builder")
            .append("Demonstrating: ")
            .append(bold("Bold Text"))
            .newParagraph()
            .beginList()
            .append("I should be an item")
            .append(italic("I should be an italic item"))
            .end()
            .newParagraph()
            .beginQuote()
            .append("I should be a quote").newLine()
            .append("I should still be a quote")
            .end()
            .newParagraph()
            .beginCodeBlock(org.kyrinne.markdowngenerator.text.code.CodeBlock.LANGUAGE_JAVA)
            .append("// I should be code").newLine()
            .append("dummyMethod(this);")
            .end()
            .newParagraph()
            .append("Over.")!!

        println(builder.toString())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val builder = org.kyrinne.markdowngenerator.list.ListBuilder()
            .text("Item 1")
            .bold("Item 2")
            .beginList()
            .text("Item 2.1")
            .bold("Item 2.2")
            .beginList()
            .text("Item 2.2.1")
            .bold("Item 2.2.2")
            .text("Item 2.2.3")
            .end()
            .text("Item 2.3")
            .end()
            .text("Item 3")!!

        println(builder.toString())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example3() {
        val builder = org.kyrinne.markdowngenerator.text.TextBuilder()
            .heading("Markdown Builder")
            .text("Demonstrating: ")!!.bold("Bold Text")
            .newParagraph()
            .quote("I should be a quote\nI should still be a quote")
            .beginQuote()
            .text("I should be a quote")!!.newLine()
            .text("I should still be a quote")
            .end()
            .newParagraph()
            .code("INLINE_CODE")
            .beginCodeBlock(org.kyrinne.markdowngenerator.text.code.CodeBlock.LANGUAGE_JAVA)
            .text("// some comment")!!.newLine()
            .text("dummyMethod(this);")
            .end()
            .subHeading("Lists")
            .unorderedList(
                "I should be an item",
                italic("I should be an italic item")
            )
            .beginList()
            .text("I should be an item")
            .italic("I should be an italic item")
            .end()
            .newParagraph()
            .taskList(
                task("Task 1", true),
                task("Task 2", false),
                task("Task 3")
            )!!

        println(builder.toString())
    }
}