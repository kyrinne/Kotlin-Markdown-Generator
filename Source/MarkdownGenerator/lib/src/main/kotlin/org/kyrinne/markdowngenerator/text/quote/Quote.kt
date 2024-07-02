package org.kyrinne.markdowngenerator.text.quote

import org.kyrinne.markdowngenerator.MarkdownSerializationException
import org.kyrinne.markdowngenerator.text.Text

/**
 * Created by steppschuh on 15/12/2016.
 */
class Quote(value: Any?) : Text(value) {
    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        if (value == null) {
            throw MarkdownSerializationException("Value is null")
        }
        val sb = StringBuilder()
        val lines = value.toString().split("\\r?\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (lineIndex in lines.indices) {
            sb.append("> ").append(lines[lineIndex])
            if (lineIndex < lines.size - 1) {
                sb.append(System.lineSeparator())
            }
        }
        return sb.toString()
    }
}
