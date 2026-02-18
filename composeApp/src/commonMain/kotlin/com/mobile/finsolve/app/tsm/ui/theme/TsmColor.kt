package com.mobile.finsolve.app.tsm.ui.theme

import androidx.compose.ui.graphics.Color

object TsmColor {

    // ── Background ───────────────────────────────────────────────────────────
    val BackgroundDeep    = Color(0xFF070710)  // canvas / page
    val Background        = Color(0xFF0A0A0F)  // scaffold
    val Surface           = Color(0xFF111118)  // cards, sheets
    val SurfaceVariant    = Color(0xFF16161F)  // elevated cards
    val SurfaceTint       = Color(0xFF1E1E2A)  // pressed / hover

    // ── Accent ───────────────────────────────────────────────────────────────
    val Accent            = Color(0xFF6C63FF)  // buttons, FAB, active nav
    val AccentLight       = Color(0xFF8A84FF)  // hover / icon on accent
    val AccentContainer   = Color(0x266C63FF)  // pill bg, tinted fills

    // ── Semantic ─────────────────────────────────────────────────────────────
    val Success           = Color(0xFF4ECDC4)  // done, positive
    val SuccessContainer  = Color(0x264ECDC4)

    val Warning           = Color(0xFFFFE66D)  // deadline near
    val WarningContainer  = Color(0x26FFE66D)

    val Danger            = Color(0xFFFF6B6B)  // overdue, error
    val DangerContainer   = Color(0x26FF6B6B)

    // ── Text ─────────────────────────────────────────────────────────────────
    val TextPrimary       = Color(0xFFF0F0F8)
    val TextSecondary     = Color(0x99F0F0F8)  // ~60% alpha
    val TextTertiary      = Color(0x4DF0F0F8)  // ~30% alpha — hints, placeholders

    // ── Border ───────────────────────────────────────────────────────────────
    val BorderSubtle      = Color(0x0FFFFFFF)  // dividers
    val BorderDefault     = Color(0x1AFFFFFF)  // card stroke
    val BorderStrong      = Color(0x33FFFFFF)  // selected / focused

    // ── Static ───────────────────────────────────────────────────────────────
    val White             = Color(0xFFFFFFFF)
    val Transparent       = Color(0x00000000)
}