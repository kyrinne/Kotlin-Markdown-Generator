package org.kyrinne.markdowngenerator.link

import org.kyrinne.markdowngenerator.MarkdownElement
import org.kyrinne.markdowngenerator.MarkdownSerializationException

open class Link(private var text: Any, private var url: String) : MarkdownElement() {
    constructor(url: String) : this(url, url)

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        val sb = StringBuilder()
        sb.append("[").append(text).append("]")
        sb.append("(").append(url).append(")")
        return sb.toString()
    }

    fun getText(): Any {
        return text
    }

    fun setText(text: Any) {
        this.text = text
        invalidateSerialized()
    }

    fun getUrl(): String {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
        invalidateSerialized()
    }
}
