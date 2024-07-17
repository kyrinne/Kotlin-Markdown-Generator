package org.kyrinne.markdowngenerator.text.code

import org.kyrinne.markdowngenerator.MarkdownBuilder

/**
 * Created by Stephan on 12/25/2016.
 */
class CodeBlockBuilder(
    override val builder: CodeBlockBuilder?,
    language: String = CodeBlock.LANGUAGE_UNKNOWN
) : MarkdownBuilder<CodeBlockBuilder?, CodeBlock>() {

    init {
        markdownElement.language = language
    }

//    fun getBuilder(): CodeBlockBuilder {
//        return this
//    }

    override fun createMarkdownElement(): CodeBlock {
        return CodeBlock(CodeBlock.LANGUAGE_UNKNOWN)
    }

    override fun append(value: Any?): CodeBlockBuilder {
        markdownElement.value = StringBuilder()
            .append(markdownElement.value)
            .append(value)
            .toString()
        return this
    }
}
