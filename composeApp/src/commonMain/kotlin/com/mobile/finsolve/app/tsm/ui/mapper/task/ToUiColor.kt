package com.mobile.finsolve.app.tsm.ui.mapper.task

import androidx.compose.ui.graphics.Color
import com.mobile.finsolve.app.tsm.domain.model.task.TaskAccentColor
import com.mobile.finsolve.app.tsm.domain.model.task.TaskGoalDomain
import com.mobile.finsolve.app.tsm.domain.model.task.TaskItemDomain
import com.mobile.finsolve.app.tsm.ui.components.task.TaskGoal
import com.mobile.finsolve.app.tsm.ui.components.task.TaskState
import com.mobile.finsolve.app.tsm.ui.theme.TsmColor

fun TaskAccentColor.toUiColor(): Color = when (this) {
    TaskAccentColor.Accent  -> TsmColor.Accent
    TaskAccentColor.Success -> TsmColor.Success
    TaskAccentColor.Warning -> TsmColor.Warning
    TaskAccentColor.Danger  -> TsmColor.Danger
}

fun TaskItemDomain.toUiState(): TaskState =
    if (isCompleted) TaskState.Completed else TaskState.NotCompleted

fun TaskGoalDomain.toUiGoal(color: Color): TaskGoal =
    TaskGoal(emoji = emoji, title = title, color = color)