package org.kyrinne.markdowngenerator.table

import org.junit.jupiter.api.Test
import org.kyrinne.markdowngenerator.text.Text
import org.kyrinne.markdowngenerator.text.code.Code
import org.kyrinne.markdowngenerator.text.emphasis.BoldText
import java.util.*

/**
 * Created by steppschuh on 15/12/2016.
 */
class TableTest {
    @Test
    @Throws(java.lang.Exception::class)
    fun example1() {
        val rows = mutableListOf<TableRow<*>>(
            TableRow(
                mutableListOf(
                    "Left",
                    "Center",
                    "Right"
                )
            ),
            TableRow(
                mutableListOf(
                    Text("Normal Text"),
                    BoldText("Bold Text"),
                    Code("Code Text")
                )
            ),
            TableRow(
                mutableListOf(
                    1,
                    2,
                    3
                )
            )
        )

        val alignments = listOf(
            Table.ALIGN_LEFT,
            Table.ALIGN_CENTER,
            Table.ALIGN_RIGHT
        )

        val table = Table(rows, alignments)
        println(table)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun example2() {
        val tableBuilder = Table.Builder()
            .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
            .withRowLimit(7)
            .addRow("Index", "Boolean")

        for (i in 1..20) {
            tableBuilder.addRow(i, Math.random() > 0.5)
        }

        println(tableBuilder.build())
    }

    @Test
    fun example3() {
        val tableBuilder = Table.Builder()
            .addRow("Index", "Boolean")

        println(tableBuilder.build())
    }
}