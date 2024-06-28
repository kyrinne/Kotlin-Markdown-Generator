package org.kyrinne.markdowngenerator.image

import net.steppschuh.markdowngenerator.MarkdownSerializationException
import net.steppschuh.markdowngenerator.link.Link

class Image(text: Any?, url: String?) : Link(text, url) {
    constructor(url: String?) : this(url, url)

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        return "!" + super.serialize()
    }
}
