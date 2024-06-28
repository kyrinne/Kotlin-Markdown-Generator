package org.kyrinne.markdowngenerator.list

import net.steppschuh.markdowngenerator.MarkdownElement
import net.steppschuh.markdowngenerator.MarkdownSerializationException
import net.steppschuh.markdowngenerator.util.StringUtil

open class UnorderedList<T : Any?> : MarkdownElement {
    protected var items: MutableList<T>
    protected var indentationLevel: Int = 0

    constructor() {
        this.items = ArrayList()
    }

    constructor(items: MutableList<T>) {
        this.items = items
    }

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        val sb = StringBuilder()
        for (itemIndex in items.indices) {
            val item = items[itemIndex]

            if (itemIndex > 0) {
                sb.append(StringUtil.fillUpLeftAligned("", "  ", indentationLevel * 2))
            } else if (indentationLevel > 0) {
                sb.append("  ")
            }

            if (item is UnorderedListItem) {
                sb.append(item)
            } else if (item is UnorderedList<*>) {
                val unorderedList = item as UnorderedList<*>
                unorderedList.setIndentationLevel(indentationLevel + 1)
                sb.append(unorderedList)
            } else {
                sb.append(UnorderedListItem(item))
            }

            if (itemIndex < items.size - 1) {
                sb.append(System.lineSeparator())
            }
        }
        return sb.toString()
    }

    fun getItems(): MutableList<T> {
        return items
    }

    fun setItems(items: MutableList<T>) {
        this.items = items
        invalidateSerialized()
    }

    fun getIndentationLevel(): Int {
        return indentationLevel
    }

    fun setIndentationLevel(indentationLevel: Int) {
        this.indentationLevel = indentationLevel
        invalidateSerialized()
    }

    fun incrementIndentationLevel() {
        indentationLevel = indentationLevel + 1
        invalidateSerialized()
    }
}
