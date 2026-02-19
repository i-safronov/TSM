package com.mobile.finsolve.app.tsm.ui.components.bottom_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.finsolve.app.tsm.ui.theme.TsmColor
import org.jetbrains.compose.resources.stringResource
import tsm.composeapp.generated.resources.Res
import tsm.composeapp.generated.resources.aims
import tsm.composeapp.generated.resources.control
import tsm.composeapp.generated.resources.home
import tsm.composeapp.generated.resources.pomodoro

data class TBottomBarItem(
    val icon: ImageVector,
    val label: String,
)

@Composable
fun tBottomItems(): List<TBottomBarItem> {
    return listOf(
        TBottomBarItem(icon = PreviewIcons.Home, label = stringResource(Res.string.home)),
        TBottomBarItem(icon = PreviewIcons.List, label = stringResource(Res.string.aims)),
        TBottomBarItem(icon = PreviewIcons.Tomato, label = stringResource(Res.string.pomodoro)),
        TBottomBarItem(icon = PreviewIcons.Control, label = stringResource(Res.string.control)),
    )
}

@Composable
fun TBottomBar(
    items: List<TBottomBarItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(TsmColor.Surface)
            .navigationBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            val selected = index == selectedIndex

            val bgColor by animateColorAsState(
                targetValue = if (selected) TsmColor.Accent.copy(alpha = 0.08f) else TsmColor.Transparent,
                animationSpec = tween(200),
            )
            val contentColor by animateColorAsState(
                targetValue = if (selected) TsmColor.Accent else TsmColor.TextTertiary,
                animationSpec = tween(200),
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(bgColor)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { onItemSelected(index) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp),
                        tint = contentColor,
                    )
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        color = contentColor,
                    )
                }
            }
        }
    }
}

// ── Preview ─────────────────────────────────────────────────────────────────

@Preview
@Composable
private fun TBottomBarPreview() {
    var selected by remember { mutableIntStateOf(0) }
    val items = tBottomItems()

    Column {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(TsmColor.BackgroundDeep),
        )
        TBottomBar(
            items = items,
            selectedIndex = selected,
            onItemSelected = { selected = it },
        )
    }
}

@Preview
@Composable
private fun TBottomBarSelectedPreview() {
    val items = listOf(
        TBottomBarItem(icon = PreviewIcons.Home, label = "Главная"),
        TBottomBarItem(icon = PreviewIcons.List, label = "Задачи"),
        TBottomBarItem(icon = PreviewIcons.Chart, label = "Отчёты"),
    )

    TBottomBar(
        items = items,
        selectedIndex = 1,
        onItemSelected = {},
    )
}
