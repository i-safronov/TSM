package com.mobile.finsolve.app.tsm.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import tsm.composeapp.generated.resources.*

object TsmFont {

    val JetBrainsMono: FontFamily
        @Composable get() = FontFamily(
            Font(Res.font.jetbrains_mono_light, FontWeight.Light),
            Font(Res.font.jetbrains_mono_regular, FontWeight.Normal),
            Font(Res.font.jetbrains_mono_medium, FontWeight.Medium),
            Font(Res.font.jetbrains_mono_semibold, FontWeight.SemiBold),
            Font(Res.font.jetbrains_mono_bold, FontWeight.Bold),
            Font(Res.font.jetbrains_mono_italic, FontWeight.Normal, FontStyle.Italic),
            Font(Res.font.jetbrains_mono_bold_italic, FontWeight.Bold, FontStyle.Italic),
        )

    val Syne: FontFamily
        @Composable get() = FontFamily(
            Font(Res.font.syne_regular, FontWeight.Normal),
            Font(Res.font.syne_medium, FontWeight.Medium),
            Font(Res.font.syne_semibold, FontWeight.SemiBold),
            Font(Res.font.syne_bold, FontWeight.Bold),
            Font(Res.font.syne_extrabold, FontWeight.ExtraBold),
        )
}
