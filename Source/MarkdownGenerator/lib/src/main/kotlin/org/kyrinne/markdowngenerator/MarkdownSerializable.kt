package org.kyrinne.markdowngenerator

/**
 * Created by steppschuh on 07/10/2016.
 */
interface MarkdownSerializable {
    @Throws(MarkdownSerializationException::class)
    fun toMarkdownElement(): MarkdownElement
}
