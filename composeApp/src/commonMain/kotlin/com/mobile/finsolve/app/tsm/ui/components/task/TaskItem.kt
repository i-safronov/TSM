package com.mobile.finsolve.app.tsm.ui.components.task

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.finsolve.app.tsm.ui.components.task.PreviewTaskSection
import com.mobile.finsolve.app.tsm.ui.theme.TsmColor
import com.mobile.finsolve.app.tsm.ui.theme.TsmFont
import com.mobile.finsolve.app.tsm.ui.theme.tsmGradientBackground

// ─────────────────────────────────────────────────────────────────────────────
// TaskItem
//
// Состояния:
//   TaskState.Completed    — чекбокс заполнен, текст зачёркнут, приглушённые цвета
//   TaskState.NotCompleted — чекбокс пустой, текст обычный, яркие цвета
//
// Параметры:
//   title        — название задачи
//   time         — время: "07:00 – 08:00" или "10:00" (null = не показывать)
//   state        — TaskState
//   onStateChange — клик по чекбоксу
//   accentColor  — цвет левой полоски (по умолчанию Accent)
//   goal         — прикреплённая цель (null = не показывать чип)
//   onClick      — клик по всей карточке (null = не кликабельна)
//
// Usage:
//
//   // Выполненная задача с целью
//   TaskItem(
//       title = "Утренняя тренировка",
//       time = "07:00 – 08:00",
//       state = TaskState.Completed,
//       onStateChange = { viewModel.toggle(task) },
//       accentColor = TsmColor.Success,
//       goal = TTaskGoal(emoji = "💪", title = "Похудеть -5кг"),
//   )
//
//   // Невыполненная без цели
//   TaskItem(
//       title = "Созвон с командой",
//       time = "14:00",
//       state = TaskState.NotCompleted,
//       onStateChange = { viewModel.toggle(task) },
//       accentColor = TsmColor.Danger,
//   )
// ─────────────────────────────────────────────────────────────────────────────

enum class TaskState { Completed, NotCompleted }

data class TaskGoal(
    val emoji: String,
    val title: String,
    /** Цвет чипа — по умолчанию Success (бирюзовый как на скриншоте) */
    val color: Color = TsmColor.Success,
)

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    title: String,
    state: TaskState,
    onStateChange: (TaskState) -> Unit,
    time: String? = null,
    accentColor: Color = TsmColor.Accent,
    goal: TaskGoal? = null,
    onClick: (() -> Unit)? = null,
) {
    val isCompleted = state == TaskState.Completed

    // ── Анимации ─────────────────────────────────────────────────────────────
    val checkboxBg by animateColorAsState(
        targetValue = if (isCompleted) accentColor else TsmColor.Transparent,
        animationSpec = tween(200),
        label = "checkboxBg",
    )
    val checkboxBorder by animateColorAsState(
        targetValue = if (isCompleted) accentColor else TsmColor.BorderStrong,
        animationSpec = tween(200),
        label = "checkboxBorder",
    )
    val titleColor by animateColorAsState(
        targetValue = if (isCompleted) TsmColor.TextTertiary else TsmColor.TextPrimary,
        animationSpec = tween(200),
        label = "titleColor",
    )
    val metaColor by animateColorAsState(
        targetValue = if (isCompleted) TsmColor.TextTertiary else TsmColor.TextSecondary,
        animationSpec = tween(200),
        label = "metaColor",
    )
    val accentLineColor by animateColorAsState(
        targetValue = if (isCompleted) accentColor.copy(alpha = 0.35f) else accentColor,
        animationSpec = tween(200),
        label = "accentLine",
    )
    val rowAlpha by animateFloatAsState(
        targetValue = if (isCompleted) 0.7f else 1f,
        animationSpec = tween(200),
        label = "rowAlpha",
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null)
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick,
                    )
                else Modifier
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        // ── Левая акцентная полоска ───────────────────────────────────────────
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(accentLineColor)
        )

        // ── Чекбокс ───────────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(checkboxBg)
                .then(
                    if (!isCompleted)
                        Modifier.background(
                            color = TsmColor.Transparent,
                        )
                    else Modifier
                )
                // Border через outline modifier — рисуем вручную через background + inner padding
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onStateChange(
                            if (isCompleted) TaskState.NotCompleted else TaskState.Completed
                        )
                    }
                ),
            contentAlignment = Alignment.Center,
        ) {
            // Outer border когда не выполнено
            if (!isCompleted) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(TsmColor.SurfaceVariant)
                )
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .background(TsmColor.Surface)
                )
            }

            // Галочка
            if (isCompleted) {
                Text(
                    text = "✓",
                    color = TsmColor.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W800,
                )
            }
        }

        // ── Контент ───────────────────────────────────────────────────────────
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            // Заголовок
            Text(
                text = title,
                color = titleColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.3).sp,
                textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            // Мета-строка: время + чип цели
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                // Время
                if (time != null) {
                    Text(
                        text = time,
                        color = metaColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        fontFamily = TsmFont.JetBrainsMono,
                    )
                }

                // Чип цели
                if (goal != null) {
                    GoalChip(goal = goal, isCompleted = isCompleted)
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// GoalChip — бирюзовый чип с emoji и названием цели
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun GoalChip(
    goal: TaskGoal,
    isCompleted: Boolean,
) {
    val chipBg by animateColorAsState(
        targetValue = if (isCompleted)
            goal.color.copy(alpha = 0.08f)
        else
            goal.color.copy(alpha = 0.15f),
        animationSpec = tween(200),
        label = "chipBg",
    )
    val chipContent by animateColorAsState(
        targetValue = if (isCompleted)
            goal.color.copy(alpha = 0.5f)
        else
            goal.color,
        animationSpec = tween(200),
        label = "chipContent",
    )

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(chipBg)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = goal.emoji,
            fontSize = 12.sp,
        )
        Text(
            text = goal.title,
            color = chipContent,
            fontSize = 12.sp,
            fontWeight = FontWeight.W600,
            letterSpacing = (-0.1).sp,
        )
    }
}

@Preview(
    name = "TaskItem — All States",
    showBackground = true,
    backgroundColor = 0xFFF7F8FC,
    widthDp = 390,
)
@Composable
private fun TaskItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .tsmGradientBackground()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {

        // ── 1. Выполнена + цель (как на скриншоте) ────────────────────────────
        PreviewTaskSection("Completed + goal") {
            var state by remember { mutableStateOf(TaskState.Completed) }
            TaskItem(
                title = "Утренняя тренировка",
                time = "07:00 – 08:00",
                state = state,
                onStateChange = { state = it },
                accentColor = TsmColor.Success,
                goal = TaskGoal(
                    emoji = "💪",
                    title = "Похудеть -5кг",
                    color = TsmColor.Success,
                ),
            )
        }

        // ── 2. Не выполнена + цель ────────────────────────────────────────────
        PreviewTaskSection("NotCompleted + goal") {
            var state by remember { mutableStateOf(TaskState.NotCompleted) }
            TaskItem(
                title = "Утренняя тренировка",
                time = "07:00 – 08:00",
                state = state,
                onStateChange = { state = it },
                accentColor = TsmColor.Success,
                goal = TaskGoal(
                    emoji = "💪",
                    title = "Похудеть -5кг",
                    color = TsmColor.Success,
                ),
            )
        }

        // ── 3. Не выполнена без цели — Accent ─────────────────────────────────
        PreviewTaskSection("NotCompleted · Accent · no goal") {
            var state by remember { mutableStateOf(TaskState.NotCompleted) }
            TaskItem(
                title = "Написать квартальный отчёт",
                time = "10:00 – 12:00",
                state = state,
                onStateChange = { state = it },
                accentColor = TsmColor.Accent,
                goal = TaskGoal(
                    emoji = "💼",
                    title = "Работа",
                    color = TsmColor.Accent,
                ),
            )
        }

        // ── 4. Не выполнена — Danger (дедлайн) ───────────────────────────────
        PreviewTaskSection("NotCompleted · Danger") {
            var state by remember { mutableStateOf(TaskState.NotCompleted) }
            TaskItem(
                title = "Созвон с командой",
                time = "14:00",
                state = state,
                onStateChange = { state = it },
                accentColor = TsmColor.Danger,
            )
        }

        // ── 5. Не выполнена — Warning ─────────────────────────────────────────
        PreviewTaskSection("NotCompleted · Warning · no time") {
            var state by remember { mutableStateOf(TaskState.NotCompleted) }
            TaskItem(
                title = "Медитация",
                state = state,
                onStateChange = { state = it },
                accentColor = TsmColor.Warning,
                goal = TaskGoal(
                    emoji = "🧘",
                    title = "Здоровье",
                    color = TsmColor.Warning,
                ),
            )
        }

        // ── 6. Выполнена без цели ─────────────────────────────────────────────
        PreviewTaskSection("Completed · no goal") {
            var state by remember { mutableStateOf(TaskState.Completed) }
            TaskItem(
                title = "Читать книгу 30 мин",
                time = "08:30",
                state = state,
                onStateChange = { state = it },
                accentColor = TsmColor.Accent,
            )
        }

        // ── 7. Интерактивный — нажми чтобы переключить ───────────────────────
        PreviewTaskSection("Interactive — tap checkbox") {
            var state by remember { mutableStateOf(TaskState.NotCompleted) }
            TaskItem(
                title = "Нажми на чекбокс →",
                time = "09:00 – 10:00",
                state = state,
                onStateChange = { state = it },
                accentColor = TsmColor.Accent,
                goal = TaskGoal("🎯", "Цель"),
            )
        }

        // ── 8. Длинный заголовок ──────────────────────────────────────────────
        PreviewTaskSection("Long title") {
            var state by remember { mutableStateOf(TaskState.NotCompleted) }
            TaskItem(
                title = "Подготовить презентацию для квартального отчёта команды",
                time = "10:00 – 13:00",
                state = state,
                onStateChange = { state = it },
                accentColor = TsmColor.Accent,
                goal = TaskGoal("💼", "Работа", TsmColor.Accent),
            )
        }

        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun PreviewTaskSection(
    label: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column {
        Text(
            text = label,
            color = TsmColor.TextTertiary,
            fontSize = 9.sp,
            fontWeight = FontWeight.W700,
            letterSpacing = 1.sp,
            modifier = androidx.compose.ui.Modifier.padding(start = 20.dp, top = 12.dp, bottom = 2.dp),
        )
        content()
        HorizontalDivider(
            color = TsmColor.BorderSubtle,
            thickness = 1.dp,
            modifier = androidx.compose.ui.Modifier.padding(horizontal = 20.dp),
        )
    }
}






