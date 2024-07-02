package org.kyrinne.markdowngenerator

import org.kyrinne.markdowngenerator.image.Image
import org.kyrinne.markdowngenerator.link.Link
import org.kyrinne.markdowngenerator.list.ListBuilder
import org.kyrinne.markdowngenerator.list.TaskList
import org.kyrinne.markdowngenerator.list.TaskListItem
import org.kyrinne.markdowngenerator.list.UnorderedList
import org.kyrinne.markdowngenerator.progress.ProgressBar
import org.kyrinne.markdowngenerator.rule.HorizontalRule
import org.kyrinne.markdowngenerator.text.Text
import org.kyrinne.markdowngenerator.text.code.Code
import org.kyrinne.markdowngenerator.text.code.CodeBlock
import org.kyrinne.markdowngenerator.text.code.CodeBlockBuilder
import org.kyrinne.markdowngenerator.text.emphasis.BoldText
import org.kyrinne.markdowngenerator.text.emphasis.ItalicText
import org.kyrinne.markdowngenerator.text.emphasis.StrikeThroughText
import org.kyrinne.markdowngenerator.text.heading.Heading
import org.kyrinne.markdowngenerator.text.quote.Quote
import org.kyrinne.markdowngenerator.text.quote.QuoteBuilder
import java.util.*

/**
 * Base class that every markdown builder extends. Basically capable of
 * appending stuff to a root [MarkdownElement].
 */
abstract class MarkdownBuilder<T : MarkdownBuilder<T, S>, S : MarkdownElement>() : MarkdownSerializable {
    /**
     * The root element that content will be appended too.
     */
    protected var markdownElement: S

    /**
     * The parent markdown builder, if available. Parent builder will be set in child builders,
     * if they have been created using the [MarkdownBuilder.begin] method.
     * If set, this will be returned in the [MarkdownBuilder.end] method.
     */
    var parentBuilder: MarkdownBuilder<*, *>? = null

    init {
        markdownElement = createMarkdownElement()
    }

    protected constructor(parentBuilder: MarkdownBuilder<*, *>?) : this() {
        this.parentBuilder = parentBuilder
    }

    protected abstract val builder: T

    /**
     * Creates the root element. Typically, without any content.
     *
     * @return the root markdown element
     */
    protected abstract fun createMarkdownElement(): S

    /**
     * All chained method calls will be called on the passed 'child' builder
     * until [MarkdownBuilder.end] will be called.
     *
     * @param markdownBuilder the new builder which should be used
     * @return the passed markdown builder
     */
    fun begin(markdownBuilder: MarkdownBuilder<*, *>): MarkdownBuilder<*, *> {
        markdownBuilder.parentBuilder = this
        return markdownBuilder
    }

    /**
     * If a [MarkdownBuilder.parentBuilder] is set (e.g. if
     * [MarkdownBuilder.begin] was called before),
     * the current [MarkdownBuilder.markdownElement] will be appended
     * to the parent builder, which will also be returned.
     *
     * @return the parent builder
     */
    fun end(): MarkdownBuilder<*, *> {
        if (parentBuilder == null) {
            return this
        }
        parentBuilder!!.append(this)
        return parentBuilder as MarkdownBuilder<*, *>
    }

    // Emphasis
    /**
     * Appends a normal [Text] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the new element
     * @return the builder instance
     * @see Text.Text
     */
    fun text(value: Any?): T {
        return append(Text(value))
    }

    /**
     * Appends a [BoldText] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the new element
     * @return the builder instance
     * @see BoldText.BoldText
     */
    fun bold(value: Any?): T {
        return append(BoldText(value))
    }

    /**
     * Appends an [ItalicText] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the new element
     * @return the builder instance
     * @see ItalicText.ItalicText
     */
    fun italic(value: Any?): T {
        return append(ItalicText(value))
    }

    /**
     * Appends a [StrikeThroughText] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the new element
     * @return the builder instance
     * @see StrikeThroughText.StrikeThroughText
     */
    fun strikeThrough(value: Any?): T {
        return append(StrikeThroughText(value))
    }

    // Heading
    /**
     * Appends a [Heading] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the new element
     * @param level the heading level
     * @return the builder instance
     * @see Heading.Heading
     */
    /**
     * Appends a [Heading] element with level 1 to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the new element
     * @return the builder instance
     * @see Heading.Heading
     */
    @JvmOverloads
    fun heading(value: String?, level: Int = 1): T {
        newParagraphIfRequired()
        append(Heading(value, level))
        return newParagraph()
    }

    /**
     * Appends a [Heading] element with level 2 to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the new element
     * @return the builder instance
     * @see Heading.Heading
     */
    fun subHeading(value: String?): T {
        return heading(value, 2)
    }

    // Rule
    /**
     * Appends a [HorizontalRule] element to the root [MarkdownBuilder.markdownElement].
     *
     * @return the builder instance
     * @see HorizontalRule.HorizontalRule
     */
    fun rule(): T {
        newLinesIfRequired(1)
        append(HorizontalRule())
        return newLine()
    }

    /**
     * Appends a [HorizontalRule] element to the root [MarkdownBuilder.markdownElement].
     *
     * @return the builder instance
     * @see HorizontalRule.HorizontalRule
     */
    fun rule(length: Int): T {
        newLinesIfRequired(1)
        append(HorizontalRule(length))
        return newLine()
    }

    // Link
    /**
     * Appends a [Link] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param text text for the link
     * @param url  url for the link
     * @return the builder instance
     * @see Link.Link
     */
    fun link(text: String?, url: String?): T {
        return append(Link(text!!, url!!))
    }

    /**
     * Appends a [Link] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param url url for the link
     * @return the builder instance
     * @see Link.Link
     */
    fun link(url: String?): T {
        return append(Link(url!!))
    }

    // Image
    /**
     * Appends an [Image] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param text text for the image
     * @param url  url to the image
     * @return the builder instance
     * @see Image.Image
     */
    fun image(text: String?, url: String?): T {
        return append(Image(text, url))
    }

    /**
     * Appends an [Image] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param url url to the image
     * @return the builder instance
     * @see Image.Image
     */
    fun image(url: String?): T {
        return append(Image(url))
    }

    // Progress
    /**
     * Appends a [ProgressBar] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param progress progress value ranging from 0 to 1
     * @return the builder instance
     * @see ProgressBar.ProgressBar
     */
    fun progress(progress: Double): T {
        return append(ProgressBar(progress))
    }

    /**
     * Appends a [ProgressBar] element with a value label to the root [MarkdownBuilder.markdownElement].
     *
     * @param progress progress value ranging from 0 to 1
     * @return the builder instance
     * @see ProgressBar.ProgressBar
     */
    fun progressWithLabel(progress: Double): T {
        val progressBar = ProgressBar(progress)
        progressBar.setAppendPercentage(true)
        return append(progressBar)
    }

    // Quote
    /**
     * Creates a new [QuoteBuilder] instance.
     *
     * @return a new child builder instance
     * @see QuoteBuilder.QuoteBuilder
     */
    fun beginQuote(): QuoteBuilder {
        newParagraphIfRequired()
        return QuoteBuilder(this)
    }

    /**
     * Appends a [Quote] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the element
     * @return the builder instance
     * @see Quote.Quote
     */
    fun quote(value: String?): T {
        newParagraphIfRequired()
        append(Quote(value))
        return newParagraph()
    }

    // Code
    /**
     * Creates a new [CodeBlockBuilder] instance and sets the language.
     *
     * @param language the code language for syntax highlighting
     * @return a new child builder instance
     * @see CodeBlockBuilder.CodeBlockBuilder
     */
    fun beginCodeBlock(language: String?): CodeBlockBuilder {
        newParagraphIfRequired()
        return CodeBlockBuilder(this, language!!)
    }

    /**
     * Creates a new [CodeBlockBuilder] instance.
     *
     * @return a new child builder instance
     * @see CodeBlockBuilder.CodeBlockBuilder
     */
    fun beginCodeBlock(): CodeBlockBuilder {
        newParagraphIfRequired()
        return beginCodeBlock(CodeBlock.LANGUAGE_UNKNOWN)
    }

    /**
     * Appends a [Code] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param value value for the new element
     * @return the builder instance
     * @see Code.Code
     */
    fun code(value: Any?): T {
        return append(Code(value))
    }

    // List
    /**
     * Creates a new [ListBuilder] instance.
     *
     * @return a new child builder instance
     * @see ListBuilder.ListBuilder
     */
    fun beginList(): ListBuilder {
        return ListBuilder(this)
    }

    /**
     * Appends a [UnorderedList] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param items elements that should be list items
     * @return the builder instance
     * @see UnorderedList.UnorderedList
     */
    fun unorderedList(vararg items: Any?): T {
        newLinesIfRequired(1)
        append(UnorderedList(mutableListOf(*items)))
        return newParagraph()
    }

    /**
     * Appends a [TaskList] element to the root [MarkdownBuilder.markdownElement].
     *
     * @param items elements that should be task items
     * @return the builder instance
     * @see TaskList.TaskList
     */
    fun taskList(vararg items: TaskListItem?): T {
        newLinesIfRequired(1)
        append(TaskList(Arrays.asList(*items)))
        return newParagraph()
    }

    /**
     * Attempts to append the specified value to the existing root
     * [MarkdownBuilder.markdownElement].
     *
     * @param value value to be appended
     * @return the builder instance
     */
    abstract fun append(value: Any?): T

    /**
     * Attempts to serialize the specified value to markdown and appends
     * it to the existing root [MarkdownBuilder.markdownElement].
     *
     * @param value value to be appended
     * @return the builder instance
     */
    open fun append(value: MarkdownSerializable): T {
        return try {
            // serialize object to markdown element
            append(value.toMarkdownElement().serialized)
        } catch (e: MarkdownSerializationException) {
            // use default string representation
            append(value)
        }
    }

    /**
     * Appends two new lines to the existing root [MarkdownBuilder.markdownElement].
     *
     * @return the builder instance
     */
    fun newParagraph(): T {
        return newLines(2)
    }

    /**
     * Appends two new lines to the existing root [MarkdownBuilder.markdownElement]
     * if it not already ends with two new lines.
     *
     * @return the builder instance
     */
    protected fun newParagraphIfRequired(): T {
        if (!endsWithLineSeparators(2)) {
            newParagraph()
        }
        return builder
    }

    /**
     * Appends a new line to the existing root [MarkdownBuilder.markdownElement].
     *
     * @return the builder instance
     */
    fun newLine(): T {
        return newLines(1)
    }

    /**
     * Appends a new line to the existing root [MarkdownBuilder.markdownElement]
     * if it not already ends with a new line.
     *
     * @return the builder instance
     */
    fun newLines(count: Int): T {
        for (i in 0 until count) {
            append(System.lineSeparator())
        }
        return builder
    }

    /**
     * Appends the specified number of new lines to the existing root
     * [MarkdownBuilder.markdownElement] if it not already ends with a new line.
     *
     * @param count number of new lines to be appended
     * @return the builder instance
     */
    protected fun newLinesIfRequired(count: Int): T {
        if (!endsWithLineSeparators(1)) {
            newLines(count)
        }
        return builder
    }

    /**
     * Checks if the root [MarkdownBuilder.markdownElement] ends with
     * the specified number of new lines
     *
     * @param count number of new lines
     * @return true if it ends with the specified number of new lines
     */
    protected fun endsWithLineSeparators(count: Int): Boolean {
        var separators: String? = ""
        for (i in 0 until count) {
            separators += System.lineSeparator()
        }
        return markdownElement.getSerialized("").endsWith(separators!!)
    }

    override fun toString(): String {
        return build().getSerialized(this.javaClass.simpleName)
    }

    @Throws(MarkdownSerializationException::class)
    override fun toMarkdownElement(): MarkdownElement {
        return build()
    }

    /**
     * Returns the root [MarkdownBuilder.markdownElement]
     * @return [MarkdownBuilder.markdownElement]
     */
    private fun build(): S {
        return markdownElement
    }
}
