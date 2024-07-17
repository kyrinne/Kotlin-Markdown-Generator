package org.kyrinne.markdowngenerator.text.quote

import org.kyrinne.markdowngenerator.MarkdownBuilder


/**
 * Created by Stephan on 12/25/2016.
 */
class QuoteBuilder(override val builder: QuoteBuilder?) : MarkdownBuilder<QuoteBuilder?, Quote>() {

//    fun getBuilder(): QuoteBuilder {
//        return this
//    }

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
