package com.responsi.gamescope

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun kembaliDariDetailMenampilkanHomeTanpaDelayPanjang() {
        composeRule.waitUntil(timeoutMillis = 20_000) {
            composeRule
                .onAllNodesWithText("Grand Theft Auto V")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeRule.onNodeWithText("Grand Theft Auto V").performClick()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule
                .onAllNodesWithText("Detail Game")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        val startedAt = System.nanoTime()
        composeRule.onNodeWithContentDescription("Kembali").performClick()

        composeRule.waitUntil(timeoutMillis = 1_000) {
            val detailSudahHilang = composeRule
                .onAllNodesWithText("Detail Game")
                .fetchSemanticsNodes()
                .isEmpty()
            val homeSudahTampil = composeRule
                .onAllNodesWithText("GameScope")
                .fetchSemanticsNodes()
                .isNotEmpty()

            detailSudahHilang && homeSudahTampil
        }

        val elapsedMillis = (System.nanoTime() - startedAt) / 1_000_000
        assertTrue(
            "Kembali ke Home memerlukan ${elapsedMillis}ms",
            elapsedMillis < 1_000
        )
    }
}
