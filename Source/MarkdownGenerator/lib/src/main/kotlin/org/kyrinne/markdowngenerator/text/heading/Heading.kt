package org.kyrinne.markdowngenerator.text.heading

import net.steppschuh.markdowngenerator.text.Text
import net.steppschuh.markdowngenerator.util.StringUtil
import kotlin.math.max
import kotlin.math.min

/**
 * Created by steppschuh on 16/12/2016.
 */
class Heading : Text {
    private var level: Int
    var underlineStyle: Boolean = true

    constructor(value: Any?) : super(value) {
        this.level = MINIMUM_LEVEL
    }

    constructor(value: Any?, level: Int) : super(value) {
        this.level = level
        trimLevel()
    }

    override fun getPredecessor(): String {
        if (underlineStyle && level < 3) {
            return ""
        }
        return StringUtil.fillUpRightAligned("", "#", level) + " "
    }

    override fun getSuccessor(): String {
        if (underlineStyle && level < 3) {
            val underlineChar = if ((level == 1)) UNDERLINE_CHAR_1 else UNDERLINE_CHAR_2
            return System.lineSeparator() + StringUtil.fillUpLeftAligned(
                "",
                "" + underlineChar,
                value.toString().length
            )
        }
        return ""
    }

    private fun trimLevel() {
        level = min(
            MAXIMUM_LEVEL.toDouble(),
            max(MINIMUM_LEVEL.toDouble(), level.toDouble())
        ).toInt()
    }

    fun getLevel(): Int {
        return level
    }

    fun setLevel(level: Int) {
        this.level = level
        trimLevel()
        invalidateSerialized()
    }

    fun isUnderlineStyle(): Boolean {
        return underlineStyle
    }

    fun setUnderlineStyle(underlineStyle: Boolean) {
        this.underlineStyle = underlineStyle
        invalidateSerialized()
    }

    companion object {
        const val MINIMUM_LEVEL: Int = 1
        const val MAXIMUM_LEVEL: Int = 6

        const val UNDERLINE_CHAR_1: Char = '='
        const val UNDERLINE_CHAR_2: Char = '-'
    }
}
