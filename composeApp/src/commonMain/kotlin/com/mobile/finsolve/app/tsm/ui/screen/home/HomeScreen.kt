package com.mobile.finsolve.app.tsm.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.mobile.finsolve.app.tsm.domain.model.task.TaskItemDomain
import com.mobile.finsolve.app.tsm.domain.model.task.TaskItemDomain.Companion.sampleTasks
import com.mobile.finsolve.app.tsm.ui.components.bottom_bar.TBottomBar
import com.mobile.finsolve.app.tsm.ui.components.bottom_bar.tBottomItems
import com.mobile.finsolve.app.tsm.ui.components.button.TButtonIcon
import com.mobile.finsolve.app.tsm.ui.components.button.TButtonSize
import com.mobile.finsolve.app.tsm.ui.components.button.TButtonVariant
import com.mobile.finsolve.app.tsm.ui.components.stat.StatItem
import com.mobile.finsolve.app.tsm.ui.components.stat.StatVariant
import com.mobile.finsolve.app.tsm.ui.components.task.TaskItem
import com.mobile.finsolve.app.tsm.ui.components.task.TaskState
import com.mobile.finsolve.app.tsm.ui.mapper.task.toUiColor
import com.mobile.finsolve.app.tsm.ui.mapper.task.toUiGoal
import com.mobile.finsolve.app.tsm.ui.mapper.task.toUiState
import com.mobile.finsolve.app.tsm.ui.theme.TsmColor
import com.mobile.finsolve.app.tsm.ui.theme.TsmFont
import com.mobile.finsolve.app.tsm.ui.theme.tsmGradientBackground
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import tsm.composeapp.generated.resources.Res
import tsm.composeapp.generated.resources.focus
import tsm.composeapp.generated.resources.hello
import tsm.composeapp.generated.resources.plus
import tsm.composeapp.generated.resources.tasks
import tsm.composeapp.generated.resources.today

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        HomeContent(
            date            = "Понедельник, 17 февраля",
            userName        = "Илья",
            helloEmoji      = "\uD83D\uDC4B",
            tasks           = "4/7",
            focusTime       = "3h\n20m",
            progressPercent = "57%",
            helloLabel      = stringResource(Res.string.hello),
            todayLabel      = stringResource(Res.string.today),
            tasksLabel      = stringResource(Res.string.tasks),
            focusLabel      = stringResource(Res.string.focus),
            initialTasks    = sampleTasks,
            onAddTask       = { },
            onTaskStateChange = { _, _ -> },
        )
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    date: String,
    userName: String,
    helloEmoji: String,
    tasks: String,
    focusTime: String,
    progressPercent: String,
    helloLabel: String,
    todayLabel: String,
    tasksLabel: String,
    focusLabel: String,
    initialTasks: List<TaskItemDomain>,
    onAddTask: () -> Unit = {},
    onTaskStateChange: (taskId: String, completed: Boolean) -> Unit,
) {
    var userTasks by remember { mutableStateOf(initialTasks) }
    val navItems = tBottomItems()
    var selectedNav by remember { mutableIntStateOf(0) }

    // ─────────────────────────────────────────────────────────────────────────
    // Один Box — три слоя в порядке объявления (последний = поверх всего):
    //   1. Column  — скроллируемый контент
    //   2. TBottomBar — навбар прибит к низу
    //   3. TButtonIcon (FAB) — поверх навбара, объявлен последним
    // ─────────────────────────────────────────────────────────────────────────
    Box(
        modifier = modifier
            .fillMaxSize()
            .tsmGradientBackground(),
    ) {

        // ── 1. Контент ────────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 24.dp),
        ) {
            Text(
                text = date,
                fontSize = 12.sp,
                color = TsmColor.TextTertiary,
            )

            Text(
                text = "$helloLabel $userName $helloEmoji",
                fontSize = 26.sp,
                color = TsmColor.TextPrimary,
                fontFamily = TsmFont.Syne,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                StatItem(
                    modifier = Modifier.weight(1f),
                    title = tasks,
                    subTitle = tasksLabel,
                )
                StatItem(
                    modifier = Modifier.weight(1f),
                    title = focusTime,
                    subTitle = focusLabel,
                    variant = StatVariant.Success,
                )
                StatItem(
                    modifier = Modifier.weight(1f),
                    title = progressPercent,
                    subTitle = focusLabel,
                    variant = StatVariant.Warning,
                )
            }

            Text(
                text = todayLabel,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TsmColor.TextTertiary,
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    // Отступ = высота навбара + зазор, чтобы последняя задача не пряталась
                    .padding(bottom = 96.dp),
            ) {
                userTasks.forEach { task ->
                    val uiColor = task.accentColor.toUiColor()
                    TaskItem(
                        title = task.title,
                        time = task.time,
                        state = task.toUiState(),
                        accentColor = uiColor,
                        goal = task.goal?.toUiGoal(color = uiColor),
                        onStateChange = { newState ->
                            val completed = newState == TaskState.Completed
                            userTasks = userTasks.map {
                                if (it.id == task.id) it.copy(isCompleted = completed) else it
                            }
                            onTaskStateChange(task.id, completed)
                        },
                    )
                    HorizontalDivider(
                        color = TsmColor.BorderSubtle,
                        thickness = 1.dp,
                    )
                }
            }
        }

        // ── 2. Bottom nav ─────────────────────────────────────────────────────
        TBottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
            items = navItems,
            selectedIndex = selectedNav,
            onItemSelected = { selectedNav = it },
        )

        // ── 3. FAB — объявлен последним → всегда поверх навбара ──────────────
        TButtonIcon(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                // bottom = высота TBottomBar чтобы FAB висел над ним
                .padding(end = 24.dp, bottom = 80.dp),
            onClick = onAddTask,
            icon = { tint ->
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    painter = painterResource(Res.drawable.plus),
                    contentDescription = "Add task",
                    tint = tint
                )
            },
            variant = TButtonVariant.Primary,
            size = TButtonSize.Large,
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Preview
// ─────────────────────────────────────────────────────────────────────────────

@Preview(
    name = "HomeContent · iPhone 14 Pro",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    device = "spec:width=390dp,height=844dp,dpi=460",
    showSystemUi = true,
)
@Composable
private fun HomeContentPreview() {
    HomeContent(
        date            = "Понедельник, 17 февраля",
        userName        = "Илья",
        helloEmoji      = "\uD83D\uDC4B",
        tasks           = "4/7",
        focusTime       = "3h\n20m",
        progressPercent = "57%",
        helloLabel      = "Привет,",
        todayLabel      = "Сегодня",
        tasksLabel      = "Задачи",
        focusLabel      = "Фокус",
        initialTasks    = sampleTasks,
        onTaskStateChange = { _, _ -> },
    )
}

@Preview(
    name = "HomeContent · Small (360×780)",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    device = "spec:width=360dp,height=780dp,dpi=420",
    showSystemUi = true,
)
@Composable
private fun HomeContentSmallPreview() {
    HomeContent(
        date            = "Понедельник, 17 февраля",
        userName        = "Илья",
        helloEmoji      = "\uD83D\uDC4B",
        tasks           = "4/7",
        focusTime       = "3h\n20m",
        progressPercent = "57%",
        helloLabel      = "Привет,",
        todayLabel      = "Сегодня",
        tasksLabel      = "Задачи",
        focusLabel      = "Фокус",
        initialTasks    = sampleTasks,
        onTaskStateChange = { _, _ -> },
    )
}