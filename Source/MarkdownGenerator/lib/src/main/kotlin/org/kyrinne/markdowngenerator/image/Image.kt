package org.kyrinne.markdowngenerator.image

import org.kyrinne.markdowngenerator.MarkdownSerializationException
import org.kyrinne.markdowngenerator.link.Link

class Image(text: Any, url: String) : Link(text, url) {

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        return "!" + super.serialize()
    }
}
