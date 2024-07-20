package org.kyrinne.markdowngenerator.text.quote

import org.kyrinne.markdowngenerator.MarkdownBuilder


/**
 * Created by Stephan on 12/25/2016.
 */
class QuoteBuilder() : MarkdownBuilder<QuoteBuilder?, Quote>() {

    override val builder = this


    override fun createMarkdownElement(): Quote {
        return Quote("")
    }

    override fun append(value: Any?): QuoteBuilder {
        // TODO: fix nullability nonsense
        markdownElement!!.value = StringBuilder()
            .append(markdownElement!!.value)
            .append(value)
            .toString()
        return this
    }
}
