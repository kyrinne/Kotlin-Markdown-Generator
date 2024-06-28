package org.kyrinne.markdowngenerator.progress

import net.steppschuh.markdowngenerator.MarkdownElement
import net.steppschuh.markdowngenerator.MarkdownSerializationException
import net.steppschuh.markdowngenerator.util.StringUtil
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Stephan on 12/18/2016.
 */
class ProgressBar : MarkdownElement {
    private var openingChar = OPENING_CHAR_DEFAULT
    private var closingChar = CLOSING_CHAR_DEFAULT

    private var fillChar = FILL_CHAR_DEFAULT
    private var emptyChar = EMPTY_CHAR_DEFAULT

    private var length = LENGTH_NORMAL
    var isAppendingValue: Boolean = false
        private set
    var isAppendingPercentage: Boolean = false
        private set

    private var value: Double
    private var minimumValue = 0.0
    private var maximumValue = 1.0

    constructor(value: Double) {
        this.value = value
    }

    constructor(value: Double, length: Int) {
        this.value = value
        this.length = length
    }

    private fun trimValue() {
        value = max(minimumValue, min(maximumValue, value))
    }

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        trimValue()
        val sb = StringBuilder().append(openingChar)
        val filledCharsCount = calculateFilledCharsCount(value, minimumValue, maximumValue, length)
        for (charIndex in 0 until length) {
            sb.append(if ((charIndex < filledCharsCount)) fillChar else emptyChar)
        }
        sb.append(closingChar)
        if (isAppendingValue) {
            var readableValue: String? = getReadableValue(value)
            readableValue = StringUtil.fillUpRightAligned(readableValue, " ", 7)
            sb.append(" ").append(readableValue)
        }
        if (isAppendingPercentage) {
            var readablePercentage: String? = getReadablePercentage(value, minimumValue, maximumValue)
            readablePercentage = StringUtil.fillUpRightAligned(readablePercentage, " ", 4)
            sb.append(" (").append(readablePercentage).append(")")
        }
        return sb.toString()
    }

    fun getOpeningChar(): Char {
        return openingChar
    }

    fun setOpeningChar(openingChar: Char) {
        this.openingChar = openingChar
        invalidateSerialized()
    }

    fun getClosingChar(): Char {
        return closingChar
    }

    fun setClosingChar(closingChar: Char) {
        this.closingChar = closingChar
        invalidateSerialized()
    }

    fun getFillChar(): Char {
        return fillChar
    }

    fun setFillChar(fillChar: Char) {
        this.fillChar = fillChar
        invalidateSerialized()
    }

    fun getEmptyChar(): Char {
        return emptyChar
    }

    fun setEmptyChar(emptyChar: Char) {
        this.emptyChar = emptyChar
        invalidateSerialized()
    }

    fun getLength(): Int {
        return length
    }

    fun setLength(length: Int) {
        this.length = length
        invalidateSerialized()
    }

    fun getValue(): Double {
        return value
    }

    fun setValue(value: Double) {
        this.value = value
        invalidateSerialized()
    }

    fun getMinimumValue(): Double {
        return minimumValue
    }

    fun setMinimumValue(minimumValue: Double) {
        this.minimumValue = minimumValue
        invalidateSerialized()
    }

    fun getMaximumValue(): Double {
        return maximumValue
    }

    fun setMaximumValue(maximumValue: Double) {
        this.maximumValue = maximumValue
        invalidateSerialized()
    }

    fun setAppendValue(appendValue: Boolean) {
        this.isAppendingValue = appendValue
    }

    fun setAppendPercentage(appendPercentage: Boolean) {
        this.isAppendingPercentage = appendPercentage
        invalidateSerialized()
    }

    companion object {
        const val LENGTH_SMALL: Int = 15
        const val LENGTH_NORMAL: Int = 20
        const val LENGTH_LARGE: Int = 50

        const val OPENING_CHAR_DEFAULT: Char = '['
        const val CLOSING_CHAR_DEFAULT: Char = ']'

        const val FILL_CHAR_DEFAULT: Char = '='
        const val EMPTY_CHAR_DEFAULT: Char = '-'

        fun calculateFilledCharsCount(value: Double, minimumValue: Double, maximumValue: Double, length: Int): Int {
            return try {
                Math.round((length * value) / (maximumValue - minimumValue)).toInt()
            } catch (e: ArithmeticException) {
                0
            }
        }

        fun getReadableValue(value: Double): String {
            return if (value >= 1 || value == 0.0) {
                value.toString()
            } else {
                String.format("%.4f", value)
            }
        }

        fun getReadablePercentage(value: Double, minimumValue: Double, maximumValue: Double): String {
            val percentage = calculateFilledCharsCount(value, minimumValue, maximumValue, 100)
            return "$percentage%"
        }
    }
}
