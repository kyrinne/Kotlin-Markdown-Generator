package org.kyrinne.markdowngenerator.link

import org.kyrinne.markdowngenerator.MarkdownElement
import org.kyrinne.markdowngenerator.MarkdownSerializationException

open class Link(private var text: String, private var url: String) : MarkdownElement() {
    constructor(url: String) : this(url, url)

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        val sb = StringBuilder()
        sb.append("[").append(text).append("]")
        sb.append("(").append(url).append(")")
        return sb.toString()
    }

    fun setText(text: String) {
        this.text = text
        invalidateSerialized()
    }

    fun setUrl(url: String) {
        this.url = url
        invalidateSerialized()
    }
}
