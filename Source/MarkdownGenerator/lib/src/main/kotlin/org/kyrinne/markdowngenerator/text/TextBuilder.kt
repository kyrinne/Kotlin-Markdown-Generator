package org.kyrinne.markdowngenerator.text

import org.kyrinne.markdowngenerator.MarkdownBuilder

/**
 * Created by steppschuh on 23/12/2016.
 */
class TextBuilder(override val builder: TextBuilder?) : MarkdownBuilder<TextBuilder?, Text>() {

//    fun getBuilder(): TextBuilder {
//        return this
//    }

    override fun createMarkdownElement(): Text {
        return Text("")
    }

    override fun append(value: Any?): TextBuilder {
        val sb = StringBuilder()
        if (markdownElement.getValue() != null) {
            sb.append(markdownElement.getValue())
        }
        sb.append(value)
        markdownElement.setValue(sb)
        return this
    }
}
