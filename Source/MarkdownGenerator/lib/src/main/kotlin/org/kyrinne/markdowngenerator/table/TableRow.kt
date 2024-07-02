package org.kyrinne.markdowngenerator.table

import org.kyrinne.markdowngenerator.MarkdownElement
import org.kyrinne.markdowngenerator.MarkdownSerializationException
import org.kyrinne.markdowngenerator.util.StringUtil

class TableRow<T : Any?> : MarkdownElement {
    private var columns: MutableList<T>

    constructor() {
        this.columns = ArrayList()
    }

    constructor(columns: MutableList<T>) {
        this.columns = columns
    }

    @Throws(MarkdownSerializationException::class)
    override fun serialize(): String {
        val sb = StringBuilder()
        for (item in columns) {
            if (item == null) {
                throw MarkdownSerializationException("Column is null")
            }
            if (item.toString().contains(Table.Companion.SEPARATOR)) {
                throw MarkdownSerializationException("Column contains seperator char \"" + Table.Companion.SEPARATOR + "\"")
            }
            sb.append(Table.Companion.SEPARATOR)
            sb.append(StringUtil.surroundValueWith(item.toString(), " "))
            if (columns.indexOf(item) == columns.size - 1) {
                sb.append(Table.Companion.SEPARATOR)
            }
        }
        return sb.toString()
    }

    fun getColumns(): MutableList<T> {
        return columns
    }

    fun setColumns(columns: MutableList<T>) {
        this.columns = columns
        invalidateSerialized()
    }
}
