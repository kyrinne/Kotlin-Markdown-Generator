package org.kyrinne.markdowngenerator.image

import org.kyrinne.markdowngenerator.MarkdownSerializationException
import org.kyrinne.markdowngenerator.link.Link

// TODO: use typed class for url?
class Image(text: String, url: String) : Link(text, url) {
    // TODO: think about this behavior (currently copied from Link where this probably makes more sense)
    constructor(url: String) : this(url, url)

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        return "!" + super.serialize()
    }
}
