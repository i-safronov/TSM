package com.mobile.finsolve.app.tsm.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object TsmColor {

    // ── Background ───────────────────────────────────────────────────────────
    val BackgroundDeep    = Color(0xFFF0F2F8)  // canvas / page
    val Background        = Color(0xFFF7F8FC)  // scaffold
    val Surface           = Color(0xFFFFFFFF)  // cards, sheets
    val SurfaceVariant    = Color(0xFFEEF0F7)  // elevated cards
    val SurfaceTint       = Color(0xFFE4E7F2)  // pressed / hover

    // ── Accent ───────────────────────────────────────────────────────────────
    val Accent            = Color(0xFF5B52E8)  // buttons, FAB, active nav
    val AccentLight       = Color(0xFF7B74EE)  // hover / icon on accent
    val AccentContainer   = Color(0x1A5B52E8)  // pill bg, tinted fills (~10%)

    // ── Semantic ─────────────────────────────────────────────────────────────
    val Success           = Color(0xFF1AAD9C)  // done, positive
    val SuccessContainer  = Color(0x1A1AAD9C)

    val Warning           = Color(0xFFD4920A)  // deadline near
    val WarningContainer  = Color(0x1AD4920A)

    val Danger            = Color(0xFFE03E3E)  // overdue, error
    val DangerContainer   = Color(0x1AE03E3E)

    // ── Text ─────────────────────────────────────────────────────────────────
    val TextPrimary       = Color(0xFF0F0F1A)
    val TextSecondary     = Color(0x990F0F1A)  // ~60% alpha
    val TextTertiary      = Color(0x4D0F0F1A)  // ~30% alpha — hints, placeholders

    // ── Border ───────────────────────────────────────────────────────────────
    val BorderSubtle      = Color(0x0F0F0F1A)  // dividers     (~6%)
    val BorderDefault     = Color(0x1A0F0F1A)  // card stroke  (~10%)
    val BorderStrong      = Color(0x330F0F1A)  // selected / focused (~20%)

    // ── Static ───────────────────────────────────────────────────────────────
    val White             = Color(0xFFFFFFFF)
    val Transparent       = Color(0x00000000)
}

// ─────────────────────────────────────────────────────────────────────────────
// tsmAmbientBackground()
//
// Воспроизводит фон:
//   · Светло-серая база  (#F0F2F8 = BackgroundDeep)
//   · Мягкое лиловое пятно  — сверху-слева  (Accent ~13%)
//   · Мягкое бирюзовое пятно — снизу-справа (Success ~10%)
//
// Возвращает Modifier — используется так:
//
//   Box(modifier = Modifier.fillMaxSize().tsmAmbientBackground())
//
//   Scaffold(
//       containerColor = Color.Transparent,
//       modifier = Modifier.fillMaxSize().tsmAmbientBackground()
//   ) { ... }
// ─────────────────────────────────────────────────────────────────────────────

fun Modifier.tsmAmbientBackground(
    base: Color = TsmColor.Background,
    topBlobColor: Color = TsmColor.Accent.copy(alpha = 0.13f),
    topBlobX: Float = 0.15f,
    topBlobY: Float = 0.10f,
    topBlobRadius: Float = 0.75f,
    bottomBlobColor: Color = TsmColor.Success.copy(alpha = 0.10f),
    bottomBlobX: Float = 0.85f,
    bottomBlobY: Float = 0.78f,
    bottomBlobRadius: Float = 0.55f,
): Modifier = this.drawBehind {
    val w = size.width
    val h = size.height

    // Base
    drawRect(color = base)

    // Top blob
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(topBlobColor, base.copy(alpha = 0f)),
            center = Offset(w * topBlobX, h * topBlobY),
            radius = w * topBlobRadius,
        ),
        center = Offset(w * topBlobX, h * topBlobY),
        radius = w * topBlobRadius,
    )

    // Bottom blob
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(bottomBlobColor, base.copy(alpha = 0f)),
            center = Offset(w * bottomBlobX, h * bottomBlobY),
            radius = w * bottomBlobRadius,
        ),
        center = Offset(w * bottomBlobX, h * bottomBlobY),
        radius = w * bottomBlobRadius,
    )
}

/** Login / Onboarding */
fun Modifier.tsmGradientBackground() = tsmAmbientBackground(
    base            = TsmColor.Surface,               // чистый белый вместо BackgroundDeep
    topBlobColor    = TsmColor.Accent.copy(alpha = 0.07f),   // было 0.13f
    topBlobX        = 0.15f,
    topBlobY        = 0.10f,
    topBlobRadius   = 0.70f,
    bottomBlobColor = TsmColor.Success.copy(alpha = 0.05f),  // было 0.10f
    bottomBlobX     = 0.85f,
    bottomBlobY     = 0.78f,
    bottomBlobRadius = 0.50f,
)

/** Dashboard — приглушённее, пятно сверху по центру */
fun Modifier.tsmDashboardBackground() = tsmAmbientBackground(
    topBlobColor    = TsmColor.Accent.copy(alpha = 0.08f),
    topBlobX        = 0.50f,
    topBlobY        = 0.05f,
    topBlobRadius   = 0.65f,
    bottomBlobColor = TsmColor.Success.copy(alpha = 0.06f),
    bottomBlobRadius = 0.45f,
)

/** Success / Completion — акцент бирюзовый */
fun Modifier.tsmSuccessBackground() = tsmAmbientBackground(
    topBlobColor    = TsmColor.Success.copy(alpha = 0.11f),
    topBlobX        = 0.20f,
    topBlobY        = 0.08f,
    bottomBlobColor = TsmColor.Accent.copy(alpha = 0.07f),
    bottomBlobX     = 0.80f,
    bottomBlobY     = 0.80f,
)

/** Danger / Error экран */
fun Modifier.tsmDangerBackground() = tsmAmbientBackground(
    topBlobColor    = TsmColor.Danger.copy(alpha = 0.09f),
    bottomBlobColor = TsmColor.Warning.copy(alpha = 0.07f),
    bottomBlobX     = 0.90f,
    bottomBlobY     = 0.85f,
)
