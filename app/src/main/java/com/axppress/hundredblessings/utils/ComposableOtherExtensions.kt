package com.axppress.hundredblessings.utils

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.noRippleClick(onClick: () -> Unit): Modifier {
    return this.clickable(
        interactionSource = NoRippleInteractionSource(),
        indication = null
    ) {
        onClick()
    }
}