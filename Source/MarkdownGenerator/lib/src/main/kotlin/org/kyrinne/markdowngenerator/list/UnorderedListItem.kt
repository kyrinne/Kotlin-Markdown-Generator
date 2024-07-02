package org.kyrinne.markdowngenerator.list

import org.kyrinne.markdowngenerator.text.Text

open class UnorderedListItem(value: Any?) : Text(value) {
    fun getPredecessor(): String {
        return "- "
    }

    override fun getSuccessor(): String {
        return ""
    }
}
