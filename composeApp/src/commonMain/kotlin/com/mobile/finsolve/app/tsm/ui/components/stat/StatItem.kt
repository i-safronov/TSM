package com.mobile.finsolve.app.tsm.ui.components.stat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.finsolve.app.tsm.ui.theme.TsmColor
import com.mobile.finsolve.app.tsm.ui.theme.TsmFont
import com.mobile.finsolve.app.tsm.ui.theme.tsmGradientBackground

enum class StatVariant(val color: Color) {
    Default(TsmColor.Accent),
    Success(TsmColor.Success),
    Warning(TsmColor.Warning),
    Danger(TsmColor.Danger),
}

@Composable
fun StatItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    variant: StatVariant = StatVariant.Default,
    height: Dp = 100.dp,
) {
    Column(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(16.dp))
            .background(TsmColor.Background)
            .padding(14.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = TsmFont.Syne,
            color = variant.color,
        )

        Text(
            text = subTitle,
            fontSize = 12.sp,
            color = TsmColor.TextTertiary,
        )
    }
}

@Composable
@Preview
fun StatItemPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .tsmGradientBackground()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatItem(
            modifier = Modifier.weight(1f),
            title = "4/7",
            subTitle = "ЗАДАЧ",
        )

        StatItem(
            modifier = Modifier.weight(1f),
            title = "3h\n20m",
            subTitle = "ФОКУС",
            variant = StatVariant.Success,
        )

        StatItem(
            modifier = Modifier.weight(1f),
            title = "57%",
            subTitle = "ПРОГРЕСС",
            variant = StatVariant.Warning,
        )
    }
}
