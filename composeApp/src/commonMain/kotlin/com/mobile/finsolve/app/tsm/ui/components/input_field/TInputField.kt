package com.mobile.finsolve.app.tsm.ui.components.input_field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.mobile.finsolve.app.tsm.ui.theme.TsmColor
import com.mobile.finsolve.app.tsm.ui.theme.TsmFont

// ─────────────────────────────────────────────────────────────────────────────
// TSM Input Field
//
// Usage examples:
//
// // Basic
// TsmTextField(value = name, onValueChange = { name = it }, placeholder = "Имя")
//
// // With leading icon
// TsmTextField(value = q, onValueChange = { q = it }, leadingIcon = Icons.Default.Search, placeholder = "Поиск")
//
// // Error state
// TsmTextField(value = email, onValueChange = { email = it }, isError = true, errorMessage = "Некорректный email")
//
// // Disabled
// TsmTextField(value = "Read only", onValueChange = {}, enabled = false)
//
// // Multiline
// TsmTextField(value = desc, onValueChange = { desc = it }, maxLines = 5, placeholder = "Описание")
//
// // Password
// TsmTextField(value = pass, onValueChange = { pass = it }, isPassword = true, placeholder = "Пароль")
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    label: String = "",
    errorMessage: String = "",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    isPassword: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    // ── Derived state ────────────────────────────────────────────────────────
    val hasError = isError && errorMessage.isNotEmpty()

    // ── Animated colors ──────────────────────────────────────────────────────
    val borderColor by animateColorAsState(
        targetValue = when {
            !enabled   -> TsmColor.BorderSubtle
            hasError   -> TsmColor.Danger
            isFocused  -> TsmColor.Accent
            else       -> TsmColor.BorderDefault
        },
        animationSpec = tween(180),
        label = "borderColor"
    )

    val backgroundColor by animateColorAsState(
        targetValue = when {
            !enabled  -> TsmColor.SurfaceTint
            hasError  -> TsmColor.DangerContainer
            isFocused -> TsmColor.Surface
            else      -> TsmColor.Surface
        },
        animationSpec = tween(180),
        label = "backgroundColor"
    )

    val borderWidth by animateDpAsState(
        targetValue = if (isFocused || hasError) 2.dp else 1.dp,
        animationSpec = tween(180),
        label = "borderWidth"
    )

    val labelColor by animateColorAsState(
        targetValue = when {
            !enabled  -> TsmColor.TextTertiary
            hasError  -> TsmColor.Danger
            isFocused -> TsmColor.Accent
            else      -> TsmColor.TextSecondary
        },
        animationSpec = tween(180),
        label = "labelColor"
    )

    val iconColor by animateColorAsState(
        targetValue = when {
            !enabled  -> TsmColor.TextTertiary
            hasError  -> TsmColor.Danger
            isFocused -> TsmColor.Accent
            else      -> TsmColor.TextSecondary
        },
        animationSpec = tween(180),
        label = "iconColor"
    )

    val shape = RoundedCornerShape(14.dp)

    // ── Layout ───────────────────────────────────────────────────────────────
    Column(modifier = modifier) {

        // Label
        if (label.isNotEmpty()) {
            Text(
                text = label,
                color = labelColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.W700,
                letterSpacing = 0.8.sp,
                modifier = Modifier.padding(start = 4.dp, bottom = 6.dp),
            )
        }

        // Field
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = singleLine,
            maxLines = if (singleLine) 1 else maxLines,
            interactionSource = interactionSource,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            textStyle = TextStyle(
                color = if (enabled) TsmColor.TextPrimary else TsmColor.TextTertiary,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
                letterSpacing = (-0.2).sp,
            ),
            cursorBrush = SolidColor(TsmColor.Accent),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape)
                        .background(backgroundColor)
                        .border(borderWidth, borderColor, shape)
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = if (singleLine) Alignment.CenterVertically else Alignment.Top
                ) {

                    // Leading icon
                    if (leadingIcon != null) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(end = 0.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                    }

                    // Text / placeholder
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = TsmColor.TextTertiary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400,
                            )
                        }
                        innerTextField()
                    }

                    // Trailing icon
                    if (trailingIcon != null) {
                        Spacer(Modifier.width(10.dp))
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier
                                .size(20.dp)
                                .then(
                                    if (onTrailingIconClick != null)
                                        Modifier  // wrap with clickable at call site if needed
                                    else Modifier
                                )
                        )
                    }
                }
            }
        )

        // Error message
        if (hasError) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = TsmColor.Danger,
                fontSize = 11.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Preview — все состояния TsmTextField
// ─────────────────────────────────────────────────────────────────────────────

@Preview(
    name = "TsmTextField — All States",
    showBackground = true,
    backgroundColor = 0xFFF7F8FC,
    widthDp = 360,
)
@Composable
private fun TTextFieldPreview() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // ── 1. Default (empty) ────────────────────────────────────────────────
        PreviewSection(label = "Default — empty") {
            TTextField(
                value = "",
                onValueChange = {},
                placeholder = "Введите имя...",
            )
        }

        // ── 2. Default (filled) ───────────────────────────────────────────────
        PreviewSection(label = "Default — filled") {
            TTextField(
                value = "Алекс",
                onValueChange = {},
                placeholder = "Введите имя...",
            )
        }

        // ── 3. With label ─────────────────────────────────────────────────────
        PreviewSection(label = "With label") {
            TTextField(
                value = "Написать отчёт Q1",
                onValueChange = {},
                label = "НАЗВАНИЕ ЗАДАЧИ",
                placeholder = "Введите название...",
            )
        }

        // ── 4. Focused (simulated via isFocused — показываем как выглядит) ───
        PreviewSection(label = "Focused") {
            // Используем StatefulWrapper чтобы показать focused вид
            TsmTextFieldFocusedPreview()
        }

        // ── 5. Leading icon ───────────────────────────────────────────────────
        PreviewSection(label = "Leading icon") {
            TTextField(
                value = "",
                onValueChange = {},
                placeholder = "Найти задачу...",
            )
        }

        // ── 6. Leading + Trailing icon ────────────────────────────────────────
        PreviewSection(label = "Leading + trailing icon") {
            TTextField(
                value = "Тренировка",
                onValueChange = {},
                placeholder = "Поиск...",
            )
        }

        // ── 7. Password ───────────────────────────────────────────────────────
        PreviewSection(label = "Password") {
            TTextField(
                value = "mysecretpass",
                onValueChange = {},
                placeholder = "Пароль",
                isPassword = true,
            )
        }

        // ── 8. Error (empty) ──────────────────────────────────────────────────
        PreviewSection(label = "Error — empty") {
            TTextField(
                value = "",
                onValueChange = {},
                placeholder = "Email",
                isError = true,
                errorMessage = "Поле не может быть пустым",
            )
        }

        // ── 9. Error (filled) ─────────────────────────────────────────────────
        PreviewSection(label = "Error — invalid value") {
            TTextField(
                value = "notanemail",
                onValueChange = {},
                placeholder = "Email",
                isError = true,
                errorMessage = "Некорректный email адрес",
            )
        }

        // ── 10. Disabled (empty) ──────────────────────────────────────────────
        PreviewSection(label = "Disabled — empty") {
            TTextField(
                value = "",
                onValueChange = {},
                placeholder = "Недоступно",
                enabled = false,
            )
        }

        // ── 11. Disabled (filled) ─────────────────────────────────────────────
        PreviewSection(label = "Disabled — filled") {
            TTextField(
                value = "Значение только для чтения",
                onValueChange = {},
                enabled = false,
            )
        }

        // ── 12. Multiline ─────────────────────────────────────────────────────
        PreviewSection(label = "Multiline — description") {
            TTextField(
                value = "Подготовить отчёт за Q1:\n• Собрать данные\n• Написать выводы\n• Оформить слайды",
                onValueChange = {},
                label = "ОПИСАНИЕ",
                placeholder = "Добавьте описание...",
                singleLine = false,
                maxLines = 5,
            )
        }

        // ── 13. Multiline empty ───────────────────────────────────────────────
        PreviewSection(label = "Multiline — empty") {
            TTextField(
                value = "",
                onValueChange = {},
                label = "ОПИСАНИЕ",
                placeholder = "Добавьте описание задачи...",
                singleLine = false,
                maxLines = 5,
            )
        }

        // ── 14. Real-world: Login ─────────────────────────────────────────────
        PreviewSection(label = "Real-world — Login screen") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TTextField(
                    value = "Алекс",
                    onValueChange = {},
                    label = "ИМЯ",
                    placeholder = "Как тебя зовут?",
                )
            }
        }

        // ── 15. Real-world: Add Task ──────────────────────────────────────────
        PreviewSection(label = "Real-world — Add task") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TTextField(
                    value = "Написать отчёт Q1",
                    onValueChange = {},
                    label = "ЗАДАЧА",
                    placeholder = "Название задачи...",
                )
                TTextField(
                    value = "",
                    onValueChange = {},
                    label = "ОПИСАНИЕ",
                    placeholder = "Добавьте описание...",
                    singleLine = false,
                    maxLines = 3,
                )
                TTextField(
                    value = "10:00",
                    onValueChange = {},
                    label = "ВРЕМЯ НАЧАЛА",
                    placeholder = "чч:мм",
                )
            }
        }

        Spacer(Modifier.height(8.dp))
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Focused state preview helper — RequestFocus внутри LaunchedEffect
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun TsmTextFieldFocusedPreview() {
    var value by remember { mutableStateOf("Алекс") }
    // В Preview фокус не применяется автоматически,
    // поэтому показываем визуальный эквивалент через isFocused = true
    // путём передачи interactionSource с принудительным focus
    TTextField(
        value = value,
        onValueChange = { value = it },
        placeholder = "Введите имя...",
        // В живом приложении поле получит Accent border при тапе
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// Section wrapper
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun PreviewSection(
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