package org.kyrinne.markdowngenerator.text

import org.kyrinne.markdowngenerator.MarkdownCascadable
import org.kyrinne.markdowngenerator.MarkdownElement
import org.kyrinne.markdowngenerator.MarkdownSerializationException

open class Text(
    @JvmField var value: Any?, // TODO: limit types to a sensible range? // TODO: remove nullability
    @JvmField val predecessor: String = "",
    @JvmField val successor: String = "",
) : MarkdownElement() {
    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        if (value == null) {
            throw MarkdownSerializationException("Value is null") // TODO fix this
        }
        return predecessor + value.toString() + successor
    }

    open fun getPredecessor(): String {
        return ""
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
