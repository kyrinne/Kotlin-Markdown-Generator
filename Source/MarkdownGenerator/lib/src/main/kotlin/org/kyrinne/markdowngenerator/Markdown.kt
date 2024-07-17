package org.kyrinne.markdowngenerator

import org.kyrinne.markdowngenerator.image.Image
import org.kyrinne.markdowngenerator.link.Link
import org.kyrinne.markdowngenerator.list.TaskList
import org.kyrinne.markdowngenerator.list.TaskListItem
import org.kyrinne.markdowngenerator.list.UnorderedList
import org.kyrinne.markdowngenerator.progress.ProgressBar
import org.kyrinne.markdowngenerator.rule.HorizontalRule
import org.kyrinne.markdowngenerator.text.Text
import org.kyrinne.markdowngenerator.text.code.Code
import org.kyrinne.markdowngenerator.text.code.CodeBlock
import org.kyrinne.markdowngenerator.text.emphasis.BoldText
import org.kyrinne.markdowngenerator.text.emphasis.ItalicText
import org.kyrinne.markdowngenerator.text.emphasis.StrikeThroughText
import org.kyrinne.markdowngenerator.text.heading.Heading
import org.kyrinne.markdowngenerator.text.quote.Quote
import java.util.*

/**
 * Created by steppschuh on 23/12/2016.
 */
object Markdown {
    // Heading
    @JvmOverloads
    fun heading(value: String?, level: Int = 1): Heading {
        return Heading(value, level)
    }

    fun subHeading(value: String?): Heading {
        return heading(value, 2)
    }

    // Rule
    fun rule(): HorizontalRule {
        return HorizontalRule()
    }

    fun rule(length: Int): HorizontalRule {
        return HorizontalRule(length)
    }

    // Emphasis
    fun text(value: String?): Text {
        return Text(value)
    }

    @JvmStatic
    fun bold(value: String?): BoldText {
        return BoldText(value)
    }

    @JvmStatic
    fun italic(value: String?): ItalicText {
        return ItalicText(value)
    }

    fun strikeThrough(value: String?): StrikeThroughText {
        return StrikeThroughText(value)
    }

    // Link
    fun link(text: String, url: String): Link {
        return Link(text, url)
    }

    fun link(url: String): Link {
        return Link(url)
    }

    // Image
    fun image(text: String, url: String): Image {
        return Image(text, url)
    }

    fun image(url: String): Image {
        return Image(url)
    }

    // Progress
    fun progress(progress: Double): ProgressBar {
        return ProgressBar(progress)
    }

    fun progressWithLabel(progress: Double): ProgressBar {
        val progressBar = ProgressBar(progress)
        progressBar.setAppendPercentage(true)
        return progressBar
    }

    // Quote
    fun quote(value: String?): Quote {
        return Quote(value)
    }

    // Code
    fun code(value: String?): Code {
        return Code(value)
    }

    fun codeBlock(value: String?, language: String?): CodeBlock {
        return CodeBlock(value, language!!)
    }

    fun codeBlock(language: String?): CodeBlock {
        return codeBlock(null, language)
    }

    // List
    fun unorderedList(vararg items: Any?): UnorderedList<*> {
        return UnorderedList(Arrays.asList(*items))
    }

    fun taskList(vararg items: TaskListItem?): TaskList {
        return TaskList(Arrays.asList(*items))
    }

    @JvmStatic
    fun task(value: String?): TaskListItem {
        return TaskListItem(value)
    }

    @JvmStatic
    fun task(value: String?, checked: Boolean): TaskListItem {
        return TaskListItem(value, checked)
    }
}
