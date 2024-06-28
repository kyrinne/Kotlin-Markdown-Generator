package org.kyrinne.markdowngenerator.text

import net.steppschuh.markdowngenerator.MarkdownBuilder

/**
 * Created by steppschuh on 23/12/2016.
 */
class TextBuilder : MarkdownBuilder<org.kyrinne.markdowngenerator.text.TextBuilder?, org.kyrinne.markdowngenerator.text.Text?> {
    constructor()

    constructor(parentBuilder: MarkdownBuilder<*, *>?) : super(parentBuilder)

    override fun getBuilder(): org.kyrinne.markdowngenerator.text.TextBuilder {
        return this
    }

    override fun createMarkdownElement(): org.kyrinne.markdowngenerator.text.Text {
        return org.kyrinne.markdowngenerator.text.Text("")
    }

    override fun append(value: Any): org.kyrinne.markdowngenerator.text.TextBuilder {
        val sb = StringBuilder()
        if (markdownElement!!.getValue() != null) {
            sb.append(markdownElement!!.getValue())
        }
        sb.append(value)
        markdownElement!!.setValue(sb)
        return this
    }
}
