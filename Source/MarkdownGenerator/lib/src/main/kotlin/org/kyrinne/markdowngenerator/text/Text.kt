package org.kyrinne.markdowngenerator.text

import net.steppschuh.markdowngenerator.MarkdownCascadable
import net.steppschuh.markdowngenerator.MarkdownElement
import net.steppschuh.markdowngenerator.MarkdownSerializationException

open class Text(protected var value: Any?) : MarkdownElement(), MarkdownCascadable {
    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        if (value == null) {
            throw MarkdownSerializationException("Value is null")
        }
        return predecessor + value.toString() + successor
    }

    override fun getPredecessor(): String {
        return ""
    }

    override fun getSuccessor(): String {
        return predecessor
    }

    fun getValue(): Any? {
        return value
    }

    fun setValue(value: Any?) {
        this.value = value
        invalidateSerialized()
    }
}
