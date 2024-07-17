package org.kyrinne.markdowngenerator.rule

import org.kyrinne.markdowngenerator.MarkdownElement
import org.kyrinne.markdowngenerator.MarkdownSerializationException
import org.kyrinne.markdowngenerator.util.StringUtil
import kotlin.math.max

/**
 * Created by steppschuh on 16/12/2016.
 */
class HorizontalRule : MarkdownElement {
    private var length: Int
    private var character = HYPHEN

    constructor() {
        this.length = MINIMUM_LENGTH
    }

    constructor(length: Int) {
        this.length = max(MINIMUM_LENGTH.toDouble(), length.toDouble())
            .toInt()
    }

    constructor(length: Int, character: Char) : this(length) {
        this.character = character
    }

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        return StringUtil.fillUpLeftAligned("", "" + character, length)
    }

    companion object {
        const val HYPHEN: Char = '-'
        const val ASTERISK: Char = '*'
        const val UNDERSCORE: Char = '_'

        const val MINIMUM_LENGTH: Int = 3
    }
}
