package org.kyrinne.markdowngenerator.text.code

import net.steppschuh.markdowngenerator.text.Text

class CodeBlock @JvmOverloads constructor(value: Any?, language: String = "") : Text(value) {
    private var language = LANGUAGE_UNKNOWN

    init {
        this.language = language
    }

    override fun getPredecessor(): String {
        return "```" + language + System.lineSeparator()
    }

    override fun getSuccessor(): String {
        return System.lineSeparator() + "```"
    }

    fun getLanguage(): String {
        return language
    }

    fun setLanguage(language: String) {
        this.language = language
        invalidateSerialized()
    }

    companion object {
        const val LANGUAGE_UNKNOWN: String = ""
        const val LANGUAGE_JAVA: String = "java"
        const val LANGUAGE_MARKDOWN: String = "markdown"
    }
}
