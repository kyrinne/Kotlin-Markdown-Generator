package org.kyrinne.markdowngenerator

/**
 * [MarkdownElement]s that can be wrapped around other [MarkdownElement]s should
 * implement this interface.
 */
interface MarkdownCascadable {
    /**
     * @return the string that should be added before the value
     */
    val predecessor: String?

    /**
     * @return the string that should be added after the value
     */
    val successor: String?
}
