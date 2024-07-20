package org.kyrinne.markdowngenerator.text.code

import org.kyrinne.markdowngenerator.MarkdownBuilder

/**
 * Created by Stephan on 12/25/2016.
 */
class CodeBlockBuilder(
    language: String = CodeBlock.LANGUAGE_UNKNOWN
) : MarkdownBuilder<CodeBlockBuilder?, CodeBlock>() {

    override val builder = this

    init {
        markdownElement.language = language
    }

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
