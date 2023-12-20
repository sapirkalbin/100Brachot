package com.axppress.hundredblessings.ui.fragment.sightFragment

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.fragment.foodfragment.FoodBlessingsScreen

@Composable
fun SightScreen() {

}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SightScreenPreview() {
    AppTheme {
        SightScreen()
    }
}
