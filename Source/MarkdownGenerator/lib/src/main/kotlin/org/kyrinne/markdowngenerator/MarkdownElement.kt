package org.kyrinne.markdowngenerator

/**
 * Base class that every markdown element extends.
 */
abstract class MarkdownElement : MarkdownSerializable {
    private var serialized: String? = null

    /**
     * Attempts to generate a String representing this markdown element.
     *
     * @return Markdown as String
     * @throws MarkdownSerializationException If unable to generate a markdown String
     */
    @Throws(MarkdownSerializationException::class)
    abstract fun serialize(): String?

    /**
     * Returns the result of [MarkdownElement.getSerialized] or the specified fallback if a
     * [MarkdownSerializationException] occurred.
     *
     * @param fallback String to return if serialization fails
     * @return Markdown as String or specified fallback
     */
    fun getSerialized(fallback: String): String {
        return try {
            getSerialized()!!
        } catch (e: MarkdownSerializationException) {
            fallback
        }
    }

    /**
     * Calls [MarkdownElement.serialize] or directly returns its last result from [ ][MarkdownElement.serialized].
     *
     * @return Markdown as String
     * @throws MarkdownSerializationException If unable to generate a markdown String
     */
    @Throws(MarkdownSerializationException::class)
    fun getSerialized(): String? {
        if (serialized == null) {
            serialized = serialize()
        }
        return serialized
    }

    fun setSerialized(serialized: String?) {
        this.serialized = serialized
    }

    /**
     * Sets [MarkdownElement.serialized] to null. The next call to [ ][MarkdownElement.getSerialized] fill invoke a fresh serialization.
     */
    fun invalidateSerialized() {
        setSerialized(null)
    }

    @Throws(MarkdownSerializationException::class)
    override fun toMarkdownElement(): MarkdownElement {
        return this
    }

    override fun toString(): String {
        return getSerialized(this.javaClass.simpleName)
    }
}
