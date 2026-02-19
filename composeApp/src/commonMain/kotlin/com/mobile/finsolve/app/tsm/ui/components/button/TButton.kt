package com.mobile.finsolve.app.tsm.ui.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.finsolve.app.tsm.ui.theme.TsmColor
import com.mobile.finsolve.app.tsm.ui.theme.TsmFont

// ─────────────────────────────────────────────────────────────────────────────
// TButton
//
// Variants:
//   TButtonVariant.Primary    — filled accent (default)
//   TButtonVariant.Secondary  — outlined accent
//   TButtonVariant.Ghost      — text only, no bg/border
//   TButtonVariant.Danger     — filled red
//   TButtonVariant.Success    — filled teal
//
// Sizes:
//   TButtonSize.Large   — h:54dp, font:16sp  (full-width CTA)
//   TButtonSize.Medium  — h:46dp, font:14sp  (default)
//   TButtonSize.Small   — h:36dp, font:12sp  (inline actions)
//
// Usage:
//
//   // Primary CTA
//   TButton(text = "Начать работу", onClick = { })
//
//   // Loading state
//   TButton(text = "Сохранить", onClick = { }, isLoading = true)
//
//   // Disabled
//   TButton(text = "Недоступно", onClick = { }, enabled = false)
//
//   // With icons
//   TButton(
//       text = "Добавить задачу",
//       onClick = { },
//       leadingIcon = { tint -> Icon(painter = ..., tint = tint) },
//   )
//
//   // Secondary outlined
//   TButton(text = "Отмена", onClick = { }, variant = TButtonVariant.Secondary)
//
//   // Small ghost
//   TButton(text = "Подробнее", onClick = { },
//       variant = TButtonVariant.Ghost, size = TButtonSize.Small)
//
//   // Full width
//   TButton(text = "Войти", onClick = { }, modifier = Modifier.fillMaxWidth())
// ─────────────────────────────────────────────────────────────────────────────

enum class TButtonVariant { Primary, Secondary, Ghost, Danger, Success }
enum class TButtonSize    { Large, Medium, Small }

@Composable
fun TButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    variant: TButtonVariant = TButtonVariant.Primary,
    size: TButtonSize = TButtonSize.Large,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon:  (@Composable (tint: Color) -> Unit)? = null,
    trailingIcon: (@Composable (tint: Color) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // ── Size tokens ──────────────────────────────────────────────────────────
    val height: Dp = when (size) {
        TButtonSize.Large  -> 54.dp
        TButtonSize.Medium -> 46.dp
        TButtonSize.Small  -> 36.dp
    }
    val horizontalPadding: Dp = when (size) {
        TButtonSize.Large  -> 24.dp
        TButtonSize.Medium -> 20.dp
        TButtonSize.Small  -> 14.dp
    }
    val fontSize = when (size) {
        TButtonSize.Large  -> 16.sp
        TButtonSize.Medium -> 14.sp
        TButtonSize.Small  -> 12.sp
    }
    val iconSize: Dp = when (size) {
        TButtonSize.Large  -> 20.dp
        TButtonSize.Medium -> 18.dp
        TButtonSize.Small  -> 14.dp
    }
    val cornerRadius = when (size) {
        TButtonSize.Large  -> 16.dp
        TButtonSize.Medium -> 14.dp
        TButtonSize.Small  -> 10.dp
    }

    // ── Color tokens by variant ──────────────────────────────────────────────
    val activeBg: Color = when (variant) {
        TButtonVariant.Primary   -> TsmColor.Accent
        TButtonVariant.Secondary -> TsmColor.Transparent
        TButtonVariant.Ghost     -> TsmColor.Transparent
        TButtonVariant.Danger    -> TsmColor.Danger
        TButtonVariant.Success   -> TsmColor.Success
    }
    val pressedBg: Color = when (variant) {
        TButtonVariant.Primary   -> TsmColor.AccentLight
        TButtonVariant.Secondary -> TsmColor.AccentContainer
        TButtonVariant.Ghost     -> TsmColor.AccentContainer
        TButtonVariant.Danger    -> TsmColor.DangerContainer
        TButtonVariant.Success   -> TsmColor.SuccessContainer
    }
    val disabledBg: Color = when (variant) {
        TButtonVariant.Secondary, TButtonVariant.Ghost -> TsmColor.Transparent
        else -> TsmColor.SurfaceTint
    }

    val activeContentColor: Color = when (variant) {
        TButtonVariant.Primary   -> TsmColor.White
        TButtonVariant.Secondary -> TsmColor.Accent
        TButtonVariant.Ghost     -> TsmColor.Accent
        TButtonVariant.Danger    -> TsmColor.White
        TButtonVariant.Success   -> TsmColor.White
    }
    val disabledContentColor = TsmColor.TextTertiary

    val activeBorderColor: Color = when (variant) {
        TButtonVariant.Secondary -> TsmColor.Accent
        TButtonVariant.Ghost     -> TsmColor.Transparent
        else                       -> TsmColor.Transparent
    }
    val disabledBorderColor: Color = when (variant) {
        TButtonVariant.Secondary -> TsmColor.BorderDefault
        else                       -> TsmColor.Transparent
    }

    // ── Animated values ──────────────────────────────────────────────────────
    val isActive = enabled && !isLoading

    val bgColor by animateColorAsState(
        targetValue = when {
            !isActive -> disabledBg
            isPressed -> pressedBg
            else      -> activeBg
        },
        animationSpec = tween(150),
        label = "bgColor"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isActive) activeContentColor else disabledContentColor,
        animationSpec = tween(150),
        label = "contentColor"
    )

    val borderColor by animateColorAsState(
        targetValue = if (isActive) activeBorderColor else disabledBorderColor,
        animationSpec = tween(150),
        label = "borderColor"
    )

    val scale by animateFloatAsState(
        targetValue = if (isPressed && isActive) 0.97f else 1f,
        animationSpec = tween(120),
        label = "scale"
    )

    val elevation by animateDpAsState(
        targetValue = when {
            !isActive -> 0.dp
            isPressed -> 1.dp
            variant == TButtonVariant.Primary || variant == TButtonVariant.Danger
                    || variant == TButtonVariant.Success -> 4.dp
            else      -> 0.dp
        },
        animationSpec = tween(150),
        label = "elevation"
    )

    val shape = RoundedCornerShape(cornerRadius)

    // ── Layout ───────────────────────────────────────────────────────────────
    Box(
        modifier = modifier
            .scale(scale)
            .height(height)
            .clip(shape)
            .background(bgColor)
            .then(
                if (borderColor != TsmColor.Transparent)
                    Modifier.border(1.5.dp, borderColor, shape)
                else Modifier
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = isActive,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        // Subtle gradient overlay for Primary/Danger/Success (depth effect)
        if (variant == TButtonVariant.Primary ||
            variant == TButtonVariant.Danger  ||
            variant == TButtonVariant.Success
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = if (isActive) 0.10f else 0f),
                                Color.Transparent,
                            )
                        )
                    )
            )
        }

        // Content row
        Row(
            modifier = Modifier.padding(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(iconSize),
                    color = contentColor,
                    strokeWidth = 2.dp,
                )
            } else {
                // Leading icon
                if (leadingIcon != null) {
                    Box(Modifier.size(iconSize)) { leadingIcon(contentColor) }
                    Spacer(Modifier.width(8.dp))
                }

                // Label
                Text(
                    text = text,
                    color = contentColor,
                    fontSize = fontSize,
                    fontWeight = FontWeight.W700,
                    letterSpacing = 0.1.sp,
                    maxLines = 1,
                    fontFamily = TsmFont.Syne
                )

                // Trailing icon
                if (trailingIcon != null) {
                    Spacer(Modifier.width(8.dp))
                    Box(Modifier.size(iconSize)) { trailingIcon(contentColor) }
                }
            }
        }
    }
}

@Preview(
    name = "TButton — All States",
    showBackground = true,
    backgroundColor = 0xFFF7F8FC,
    widthDp = 360,
)
@Composable
private fun TButtonAllStatesPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {

        // ── Primary ───────────────────────────────────────────────────────────
        PreviewBtnSection("Primary · Large (default)") {
            TButton(
                text = "Начать работу",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        }

        PreviewBtnSection("Primary · Medium") {
            TButton(
                text = "Начать работу",
                onClick = {},
                size = TButtonSize.Medium,
            )
        }

        PreviewBtnSection("Primary · Small") {
            TButton(
                text = "Начать работу",
                onClick = {},
                size = TButtonSize.Small,
            )
        }

        // ── Secondary ─────────────────────────────────────────────────────────
        PreviewBtnSection("Secondary · Large") {
            TButton(
                text = "Отмена",
                onClick = {},
                variant = TButtonVariant.Secondary,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        PreviewBtnSection("Secondary · Medium") {
            TButton(
                text = "Отмена",
                onClick = {},
                variant = TButtonVariant.Secondary,
                size = TButtonSize.Medium,
            )
        }

        // ── Ghost ─────────────────────────────────────────────────────────────
        PreviewBtnSection("Ghost · Medium") {
            TButton(
                text = "Подробнее →",
                onClick = {},
                variant = TButtonVariant.Ghost,
                size = TButtonSize.Medium,
            )
        }

        PreviewBtnSection("Ghost · Small") {
            TButton(
                text = "Показать все",
                onClick = {},
                variant = TButtonVariant.Ghost,
                size = TButtonSize.Small,
            )
        }

        // ── Danger ────────────────────────────────────────────────────────────
        PreviewBtnSection("Danger · Large") {
            TButton(
                text = "Удалить задачу",
                onClick = {},
                variant = TButtonVariant.Danger,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        PreviewBtnSection("Danger · Medium") {
            TButton(
                text = "Удалить",
                onClick = {},
                variant = TButtonVariant.Danger,
                size = TButtonSize.Medium,
            )
        }

        // ── Success ───────────────────────────────────────────────────────────
        PreviewBtnSection("Success · Large") {
            TButton(
                text = "Задача выполнена",
                onClick = {},
                variant = TButtonVariant.Success,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        // ── Loading ───────────────────────────────────────────────────────────
        PreviewBtnSection("Loading · Primary") {
            TButton(
                text = "Сохранить",
                onClick = {},
                isLoading = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        PreviewBtnSection("Loading · Secondary") {
            TButton(
                text = "Загрузка",
                onClick = {},
                variant = TButtonVariant.Secondary,
                isLoading = true,
                size = TButtonSize.Medium,
            )
        }

        // ── Disabled ──────────────────────────────────────────────────────────
        PreviewBtnSection("Disabled · Primary") {
            TButton(
                text = "Недоступно",
                onClick = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        PreviewBtnSection("Disabled · Secondary") {
            TButton(
                text = "Недоступно",
                onClick = {},
                variant = TButtonVariant.Secondary,
                enabled = false,
                size = TButtonSize.Medium,
            )
        }

        // ── Row combinations ──────────────────────────────────────────────────
        PreviewBtnSection("Row: Primary + Secondary") {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TButton(
                    text = "Сохранить",
                    onClick = {},
                    size = TButtonSize.Medium,
                    modifier = Modifier.weight(1f),
                )
                TButton(
                    text = "Отмена",
                    onClick = {},
                    variant = TButtonVariant.Secondary,
                    size = TButtonSize.Medium,
                    modifier = Modifier.weight(1f),
                )
            }
        }

        PreviewBtnSection("Row: Danger + Ghost") {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TButton(
                    text = "Удалить",
                    onClick = {},
                    variant = TButtonVariant.Danger,
                    size = TButtonSize.Medium,
                    modifier = Modifier.weight(1f),
                )
                TButton(
                    text = "Не удалять",
                    onClick = {},
                    variant = TButtonVariant.Ghost,
                    size = TButtonSize.Medium,
                    modifier = Modifier.weight(1f),
                )
            }
        }

        // ── Real-world ────────────────────────────────────────────────────────
        PreviewBtnSection("Real-world — Login screen") {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                TButton(
                    text = "Начать →",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
                TButton(
                    text = "Уже есть аккаунт",
                    onClick = {},
                    variant = TButtonVariant.Ghost,
                    size = TButtonSize.Medium,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        PreviewBtnSection("Real-world — Task actions") {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TButton(
                    text = "Готово",
                    onClick = {},
                    variant = TButtonVariant.Success,
                    size = TButtonSize.Small,
                )
                TButton(
                    text = "Изменить",
                    onClick = {},
                    variant = TButtonVariant.Secondary,
                    size = TButtonSize.Small,
                )
                TButton(
                    text = "Удалить",
                    onClick = {},
                    variant = TButtonVariant.Danger,
                    size = TButtonSize.Small,
                )
            }
        }

        Spacer(Modifier.height(8.dp))
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Section wrapper
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun PreviewBtnSection(
    label: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = label,
            color = TsmColor.TextTertiary,
            fontSize = 10.sp,
            fontWeight = FontWeight.W700,
            letterSpacing = 1.sp,
        )
        content()
        Spacer(Modifier.height(2.dp))
        HorizontalDivider(
            color = TsmColor.BorderSubtle,
            thickness = 1.dp,
        )
    }
}