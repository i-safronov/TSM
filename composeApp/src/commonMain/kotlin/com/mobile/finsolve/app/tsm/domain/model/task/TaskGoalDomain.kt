package com.mobile.finsolve.app.tsm.domain.model.task

enum class TaskAccentColor {
    Accent, Success, Warning, Danger
}

data class TaskGoalDomain(
    val emoji: String,
    val title: String,
)

data class TaskItemDomain(
    val id: String,
    val title: String,
    val isCompleted: Boolean,
    val accentColor: TaskAccentColor,
    val time: String? = null,
    val goal: TaskGoalDomain? = null,
) {
    companion object {
        val sampleTasks = listOf(
            TaskItemDomain(
                id = "1",
                title = "Утренняя тренировка",
                isCompleted = true,
                accentColor = TaskAccentColor.Success,
                time = "07:00 – 08:00",
                goal = TaskGoalDomain(emoji = "💪", title = "Похудеть -5кг"),
            ),
            TaskItemDomain(
                id = "2",
                title = "Читать книгу 30 мин",
                isCompleted = true,
                accentColor = TaskAccentColor.Accent,
                time = "08:30",
                goal = TaskGoalDomain(emoji = "📚", title = "24 книги"),
            ),
            TaskItemDomain(
                id = "3",
                title = "Написать квартальный отчёт",
                isCompleted = false,
                accentColor = TaskAccentColor.Accent,
                time = "10:00 – 12:00",
                goal = TaskGoalDomain(emoji = "💼", title = "Работа"),
            ),
            TaskItemDomain(
                id = "4",
                title = "Созвон с командой",
                isCompleted = false,
                accentColor = TaskAccentColor.Danger,
                time = "14:00",
                goal = null,
            ),
            TaskItemDomain(
                id = "5",
                title = "Медитация",
                isCompleted = false,
                accentColor = TaskAccentColor.Warning,
                time = "18:00",
                goal = TaskGoalDomain(emoji = "🧘", title = "Здоровье"),
            ),
            TaskItemDomain(
                id = "6",
                title = "Пробежка 5 км",
                isCompleted = false,
                accentColor = TaskAccentColor.Success,
                time = null,
                goal = TaskGoalDomain(emoji = "🏃", title = "Марафон"),
            ),
            TaskItemDomain(
                id = "7",
                title = "Купить продукты",
                isCompleted = false,
                accentColor = TaskAccentColor.Accent,
                time = null,
                goal = null,
            )
        )
    }
}