package org.kyrinne.markdowngenerator.list

class TaskListItem @JvmOverloads constructor(value: Any?, private val checked: Boolean = false) :
    UnorderedListItem(value) {
    override fun getPredecessor(): String {
        return "- " + getCheckedIndicator(checked) + " "
    }

    companion object {
        fun getCheckedIndicator(checked: Boolean): String {
            return if (checked) "[x]" else "[ ]"
        }
    }
}
