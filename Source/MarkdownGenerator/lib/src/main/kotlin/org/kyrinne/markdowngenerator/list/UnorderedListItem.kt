package org.kyrinne.markdowngenerator.list

import net.steppschuh.markdowngenerator.text.Text

open class UnorderedListItem(value: Any?) : Text(value) {
    override fun getPredecessor(): String {
        return "- "
    }

    override fun getSuccessor(): String {
        return ""
    }
}
