package org.kyrinne.markdowngenerator.text.code

import net.steppschuh.markdowngenerator.MarkdownBuilder

/**
 * Created by Stephan on 12/25/2016.
 */
class CodeBlockBuilder : MarkdownBuilder<CodeBlockBuilder?, CodeBlock?> {
    @JvmOverloads
    constructor(language: String = CodeBlock.Companion.LANGUAGE_UNKNOWN) : super() {
        markdownElement!!.language = language
    }

    @JvmOverloads
    constructor(parentBuilder: MarkdownBuilder<*, *>?, language: String = CodeBlock.Companion.LANGUAGE_UNKNOWN) : super(
        parentBuilder
    ) {
        markdownElement!!.language = language
    }

    override fun getBuilder(): CodeBlockBuilder {
        return this
    }

    override fun createMarkdownElement(): CodeBlock {
        return CodeBlock(CodeBlock.Companion.LANGUAGE_UNKNOWN)
    }

    override fun append(value: Any): CodeBlockBuilder {
        markdownElement!!.value = StringBuilder()
            .append(markdownElement!!.value)
            .append(value)
            .toString()
        return this
    }
}
