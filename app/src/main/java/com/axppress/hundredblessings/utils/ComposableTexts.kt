package com.axppress.hundredblessings.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.axppress.hundredblessings.ui.fragment.mainfragment.TextWithStyle

@Composable
fun textMultiStyle(
    originalText: String,
    customTextList: List<TextWithStyle>,
): AnnotatedString {
    var annotatedString = buildAnnotatedString { append(originalText) }

    customTextList.forEach { textExcerpt ->
        annotatedString = buildAnnotatedString {
            val startIndex = annotatedString.indexOf(textExcerpt.customText)
            if (startIndex != -1) {
                val endIndex = startIndex + textExcerpt.customText.length
                append(annotatedString)
                addStyle(
                    style = textExcerpt.style.toSpanStyle(),
                    start = startIndex,
                    end = endIndex,
                )
            }
        }
    }
    return annotatedString
}

@Composable
fun DefaultText(
    text: String,
    modifier: Modifier = Modifier,
    textStyleAndSize: TextStyle = MaterialTheme.typography.titleLarge,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    lineHeight: TextUnit = TextUnit.Unspecified,
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = TextAlign.Center,
        color = textColor,
        lineHeight = lineHeight,
        style = TextStyle(textDirection = TextDirection.Rtl).merge(
            textStyleAndSize,
        ),
    )
}

@Composable
fun DefaultAnnotatedText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    textSize: TextStyle = MaterialTheme.typography.titleLarge,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        style = TextStyle(textDirection = TextDirection.Rtl).merge(
            textSize,
        ),
    )
}
