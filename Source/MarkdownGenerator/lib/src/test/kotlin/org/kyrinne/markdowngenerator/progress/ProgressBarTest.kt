package org.kyrinne.markdowngenerator.progress

import org.junit.jupiter.api.Test
import kotlin.math.pow

/**
 * Created by Stephan on 12/18/2016.
 */
class ProgressBarTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val progressBar = ProgressBar(0.75)
        println(progressBar)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val progressBar = ProgressBar(0.0)
        progressBar.setMaximumValue(100.0)
        progressBar.setAppendPercentage(true)

        var i = 0
        while (i <= 100) {
            progressBar.setValue(i.toDouble())
            println(progressBar)
            i += 5
        }
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example3() {
        val tableBuilder = org.kyrinne.markdowngenerator.table.Table.Builder()
            .withAlignments(
                org.kyrinne.markdowngenerator.table.Table.ALIGN_RIGHT,
                org.kyrinne.markdowngenerator.table.Table.ALIGN_RIGHT,
                org.kyrinne.markdowngenerator.table.Table.ALIGN_CENTER
            )
            .addRow("Day", "Growth", "Bars")

        val progressBar = ProgressBar(0.0, ProgressBar.LENGTH_LARGE)
        progressBar.setFillChar('#')
        progressBar.setEmptyChar(' ')
        progressBar.setOpeningChar(' ')
        progressBar.setClosingChar(' ')

        for (i in 0..20) {
            progressBar.setValue(0.1 * (kotlin.math.sin(i.toDouble()) + i.toDouble().pow(1.3) - (1.3 * i.toDouble().pow(1.2))) + 0.3)
            val percentage = Math.round(progressBar.getValue() * 100).toString() + "%"
            val visualization = progressBar.toString().replace(progressBar.getEmptyChar().toString() + "", "")
            tableBuilder.addRow((i + 1), percentage, visualization)
        }

        println(tableBuilder.build())
    }
}