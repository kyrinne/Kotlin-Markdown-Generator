package org.kyrinne.markdowngenerator.list

import org.kyrinne.markdowngenerator.MarkdownBuilder
import org.kyrinne.markdowngenerator.MarkdownSerializable

/**
 * Created by steppschuh on 23/12/2016.
 */
// TODO: think about the types - perhaps lists of text items are enough?
class ListBuilder(override val builder: ListBuilder?) : MarkdownBuilder<ListBuilder?, UnorderedList<Any?>>() {

//    fun getBuilder(): ListBuilder {
//        return this
//    }

    override fun createMarkdownElement(): UnorderedList<Any?> {
        return UnorderedList()
    }

    override fun append(value: Any?): ListBuilder {
        markdownElement.getItems().add(value)
        return this
    }

    override fun append(value: MarkdownSerializable): ListBuilder? {
        if (value is ListBuilder) {
            val unorderedList = value.markdownElement
            unorderedList.incrementIndentationLevel()
            markdownElement.getItems().add(unorderedList)
            return this
        }
        return super.append(value)
    }
}
