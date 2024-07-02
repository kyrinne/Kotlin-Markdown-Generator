package org.kyrinne.markdowngenerator.text

import org.kyrinne.markdowngenerator.MarkdownCascadable
import org.kyrinne.markdowngenerator.MarkdownElement
import org.kyrinne.markdowngenerator.MarkdownSerializationException

open class Text(
    protected var value: Any?,
    override val predecessor: String = "",
    override val successor: String = "",
) : MarkdownElement(), MarkdownCascadable {
    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        if (value == null) {
            throw MarkdownSerializationException("Value is null")
        }
        return predecessor + value.toString() + successor
    }

    open fun getSuccessor(): String {
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
