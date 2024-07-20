package org.kyrinne.markdowngenerator.rule

import org.kyrinne.markdowngenerator.MarkdownElement
import org.kyrinne.markdowngenerator.MarkdownSerializationException
import org.kyrinne.markdowngenerator.util.StringUtil
import kotlin.math.max

/**
 * Created by steppschuh on 16/12/2016.
 */
class HorizontalRule : MarkdownElement {
    private val length: Int
    private var character = Character.HYPHEN

    constructor() {
        this.length = MINIMUM_LENGTH
    }

    constructor(length: Int) {
        this.length = max(MINIMUM_LENGTH, length)
    }

    constructor(length: Int, character: Character) : this(length) {
        this.character = character
    }

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        return StringUtil.fillUpLeftAligned("", "" + character.value, length)
    }

    companion object {
        const val MINIMUM_LENGTH: Int = 3
    }
}

enum class Character(val value: Char) {
    HYPHEN('-'),
    ASTERISK('*'),
    UNDERSCORE('_'),
}
