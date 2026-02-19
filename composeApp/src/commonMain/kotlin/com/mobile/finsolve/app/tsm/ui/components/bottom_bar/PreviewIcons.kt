package com.mobile.finsolve.app.tsm.ui.components.bottom_bar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal object PreviewIcons {

    val Home: ImageVector by lazy {
        ImageVector.Builder("Home", 24.dp, 24.dp, 24f, 24f)
            .path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 3f)
                lineTo(2f, 12f)
                horizontalLineTo(5f)
                verticalLineTo(20f)
                horizontalLineTo(11f)
                verticalLineTo(14f)
                horizontalLineTo(13f)
                verticalLineTo(20f)
                horizontalLineTo(19f)
                verticalLineTo(12f)
                horizontalLineTo(22f)
                close()
            }
            .build()
    }

    val List: ImageVector by lazy {
        ImageVector.Builder("List", 24.dp, 24.dp, 24f, 24f)
            .path(fill = SolidColor(Color.Black)) {
                moveTo(3f, 6f)
                horizontalLineTo(21f)
                verticalLineTo(8f)
                horizontalLineTo(3f)
                close()
                moveTo(3f, 11f)
                horizontalLineTo(21f)
                verticalLineTo(13f)
                horizontalLineTo(3f)
                close()
                moveTo(3f, 16f)
                horizontalLineTo(21f)
                verticalLineTo(18f)
                horizontalLineTo(3f)
                close()
            }
            .build()
    }

    val Chart: ImageVector by lazy {
        ImageVector.Builder("Chart", 24.dp, 24.dp, 24f, 24f)
            .path(fill = SolidColor(Color.Black)) {
                moveTo(3f, 20f)
                verticalLineTo(14f)
                horizontalLineTo(7f)
                verticalLineTo(20f)
                close()
                moveTo(10f, 20f)
                verticalLineTo(8f)
                horizontalLineTo(14f)
                verticalLineTo(20f)
                close()
                moveTo(17f, 20f)
                verticalLineTo(4f)
                horizontalLineTo(21f)
                verticalLineTo(20f)
                close()
            }
            .build()
    }

    // Rocket: продуктивность, рост, мотивация
    val Control: ImageVector by lazy {
        ImageVector.Builder("Control", 24.dp, 24.dp, 24f, 24f)
            .path(fill = SolidColor(Color.Black)) {
                // Rocket body (fills 2..16 vertically, 6..18 horizontally)
                moveTo(12f, 1f)
                curveTo(12f, 1f, 6f, 6f, 6f, 14f)
                lineTo(8.5f, 17f)
                horizontalLineTo(15.5f)
                lineTo(18f, 14f)
                curveTo(18f, 6f, 12f, 1f, 12f, 1f)
                close()
                // Window
                moveTo(12f, 7.5f)
                arcTo(2f, 2f, 0f, true, true, 12f, 11.5f)
                arcTo(2f, 2f, 0f, true, true, 12f, 7.5f)
                // Left fin
                moveTo(6f, 14f)
                lineTo(2f, 18f)
                lineTo(2f, 21f)
                lineTo(6f, 17f)
                close()
                // Right fin
                moveTo(18f, 14f)
                lineTo(22f, 18f)
                lineTo(22f, 21f)
                lineTo(18f, 17f)
                close()
                // Flame
                moveTo(8.5f, 17f)
                lineTo(10f, 22f)
                lineTo(12f, 19f)
                lineTo(14f, 22f)
                lineTo(15.5f, 17f)
                close()
            }
            .build()
    }

    // Tomato: pomodoro timer
    val Tomato: ImageVector by lazy {
        ImageVector.Builder("Tomato", 24.dp, 24.dp, 24f, 24f)
            .path(fill = SolidColor(Color.Black)) {
                // Thick stem curving to the right
                moveTo(11f, 1f)
                curveTo(11f, 1f, 10.5f, 3f, 11f, 5f)
                lineTo(13f, 5f)
                curveTo(14f, 3f, 15f, 1.5f, 16f, 1f)
                curveTo(15f, 1.5f, 13.5f, 2.5f, 13f, 4f)
                lineTo(11.5f, 4f)
                curveTo(11.5f, 2.5f, 11.5f, 1.5f, 11f, 1f)
                close()
            }
            .path(fill = SolidColor(Color.Black)) {
                // Big calyx — 5 thick sepals spreading out
                // Far left sepal
                moveTo(12f, 6f)
                curveTo(9f, 4f, 4f, 4.5f, 3f, 6.5f)
                curveTo(5f, 6f, 8f, 5.5f, 11f, 7f)
                close()
                // Left sepal
                moveTo(12f, 6f)
                curveTo(10f, 3.5f, 6f, 3f, 4.5f, 4.5f)
                curveTo(6.5f, 4f, 9.5f, 4.5f, 11.5f, 7f)
                close()
                // Center sepal (up)
                moveTo(12f, 5.5f)
                curveTo(11f, 3f, 12f, 1.5f, 12f, 1.5f)
                curveTo(12f, 1.5f, 13f, 3f, 12f, 5.5f)
                close()
                // Right sepal
                moveTo(12f, 6f)
                curveTo(14f, 3.5f, 18f, 3f, 19.5f, 4.5f)
                curveTo(17.5f, 4f, 14.5f, 4.5f, 12.5f, 7f)
                close()
                // Far right sepal
                moveTo(12f, 6f)
                curveTo(15f, 4f, 20f, 4.5f, 21f, 6.5f)
                curveTo(19f, 6f, 16f, 5.5f, 13f, 7f)
                close()
            }
            .path(fill = SolidColor(Color.Black)) {
                // Tomato body — plump, wide, slightly flat with dip at top
                moveTo(12f, 7.5f)
                curveTo(9f, 7f, 5f, 8f, 3f, 11.5f)
                curveTo(1f, 15.5f, 3f, 20f, 7f, 22f)
                curveTo(9f, 23f, 15f, 23f, 17f, 22f)
                curveTo(21f, 20f, 23f, 15.5f, 21f, 11.5f)
                curveTo(19f, 8f, 15f, 7f, 12f, 7.5f)
                close()
            }
            .build()
    }

    val Profile: ImageVector by lazy {
        ImageVector.Builder("Profile", 24.dp, 24.dp, 24f, 24f)
            .path(fill = SolidColor(Color.Black)) {
                // Head
                moveTo(12f, 4f)
                arcTo(4f, 4f, 0f, true, true, 12f, 12f)
                arcTo(4f, 4f, 0f, true, true, 12f, 4f)
                // Body
                moveTo(4f, 20f)
                quadTo(4f, 14f, 12f, 14f)
                quadTo(20f, 14f, 20f, 20f)
                close()
            }
            .build()
    }
}
