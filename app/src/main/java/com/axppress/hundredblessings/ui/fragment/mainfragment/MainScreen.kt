package com.axppress.hundredblessings.ui.fragment.mainfragment

import android.content.res.Configuration
import android.view.MotionEvent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.axppress.hundredblessings.R
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.utils.DefaultAnnotatedText
import com.axppress.hundredblessings.utils.DefaultText
import com.axppress.hundredblessings.utils.GENERAL_FRAGMENT
import com.axppress.hundredblessings.utils.TextFormatter
import com.axppress.hundredblessings.utils.getFragmentNameByNum
import com.axppress.hundredblessings.utils.getNumberOfBlessingsToday
import com.axppress.hundredblessings.utils.noRippleClick
import com.axppress.hundredblessings.utils.textMultiStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    onBlessedButtonClick: () -> Unit,
) {
    ScreenContent(navController, viewModel)
    { onBlessedButtonClick() }
}

fun navigate(
    navController: NavHostController,
    name: String,
    onCategoryClicked: (String) -> Unit,
) {
    onCategoryClicked(name)
    navController.navigate(name)
}

@Composable
private fun ScreenContent(
    navController: NavHostController,
    viewModel: MainViewModel,
    onBlessedButtonClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
    ) {
        TopTexts()
        BottomPanel(onBlessedButtonClick, navController, viewModel)
    }
}

@Composable
private fun BottomPanel(
    onBlessedButtonClick: () -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    val states = remember {
        SnapshotStateList<Boolean>().also {
            for (day in 0..9) {
                it.add(false)
            }
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .background(
                    shape = RoundedCornerShape(32.dp),
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
            ) {
                Row(Modifier.padding(top = 96.dp, start = 8.dp, end = 8.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(end = 8.dp)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = if (states[3]) MaterialTheme.colorScheme.background else
                                    MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 3)
                            },
                    ) {
                        DefaultText(
                            "על ראייה",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp,
                                    bottom = 4.dp,
                                    top = 16.dp,
                                    end = 8.dp,
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                        )
                        Image(
                            painterResource(id = R.drawable.eye),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = if (states[2]) MaterialTheme.colorScheme.background else
                                    MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 2)
                            },
                    ) {
                        DefaultText(
                            "על מזון",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp, end = 8.dp, top = 16.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                        )
                        Image(
                            painterResource(id = R.drawable.food),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(end = 8.dp)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = if (states[1]) MaterialTheme.colorScheme.background else
                                    MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 1)
                            },
                    ) {
                        DefaultText(
                            "שמיעה",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp, end = 8.dp, top = 16.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,

                            )
                        Image(
                            painterResource(id = R.drawable.ear),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = if (states[0]) MaterialTheme.colorScheme.background else
                                    MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 0)
                            },
                    ) {
                        DefaultText(
                            "על הריח",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp, end = 8.dp, top = 16.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,

                            )
                        Image(
                            painterResource(id = R.drawable.nose),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                }
                Row(Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(end = 8.dp)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = if (states[6]) MaterialTheme.colorScheme.background else
                                    MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 6)
                            },
                    ) {
                        DefaultText(
                            "ברכות שונות",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp, end = 8.dp, top = 16.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,

                            )
                        Image(
                            painterResource(id = R.drawable.flash),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = if (states[5]) MaterialTheme.colorScheme.background else
                                    MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 5)
                            },
                    ) {
                        DefaultText(
                            "קריאת שמע",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp, end = 8.dp, top = 16.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                        )
                        Image(
                            painterResource(id = R.drawable.sleeping),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                            .weight(1f)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = if (states[4]) MaterialTheme.colorScheme.background else
                                    MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 4)
                            },
                    ) {
                        DefaultText(
                            "הנהגות בוקר",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp,
                                    bottom = 4.dp,
                                    top = 16.dp,
                                    end = 8.dp,
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                        )
                        Image(
                            painterResource(id = R.drawable.sunrise),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = if (states[4]) MaterialTheme.colorScheme.background else
                                    MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 9)
                            },
                    ) {
                        DefaultText(
                            "דברים חדשים",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp,
                                    bottom = 4.dp,
                                    top = 16.dp,
                                    end = 8.dp,
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                        )
                        Image(
                            painterResource(id = R.drawable.tshirt),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                                .align(Alignment.CenterHorizontally),
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(
                            width = 3.dp,
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(16.dp),
                        )
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = if (states[7]) MaterialTheme.colorScheme.background else
                                MaterialTheme.colorScheme.secondaryContainer,
                        )
                        .noRippleClick {
                            onButtonClick(states, navController, viewModel, 7)
                        },
                ) {
                    DefaultText(
                        "תהילים",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                start = 8.dp,
                                bottom = 4.dp,
                                top = 16.dp,
                                end = 8.dp,
                            ),
                        textStyleAndSize = MaterialTheme.typography.bodyLarge,
                    )
                    Image(
                        painterResource(id = R.drawable.king),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .border(
                            width = 3.dp,
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(16.dp),
                        )
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = if (states[8]) MaterialTheme.colorScheme.background else
                                MaterialTheme.colorScheme.secondaryContainer,
                        )
                        .noRippleClick {
                            onButtonClick(states, navController, viewModel, 8)
                        },
                ) {
                    DefaultText(
                        "תפילות מיוחדות",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                start = 8.dp,
                                bottom = 4.dp,
                                top = 16.dp,
                                end = 8.dp,
                            ),
                        textStyleAndSize = MaterialTheme.typography.bodyLarge,
                    )
                    Image(
                        painterResource(id = R.drawable.synagogue),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                }
            }
        }
        BlessedButton {
            onBlessedButtonClick()
        }
    }
}

private fun onButtonClick(
    states: SnapshotStateList<Boolean>,
    navController: NavHostController,
    viewModel: MainViewModel, numOfFragment: Int,
) {
    animateBackgroundColor(states, numOfFragment) {
        navigateToGeneralFragment(
            navController,
            viewModel,
            numOfFragment
        )
    }
}

private fun navigateToGeneralFragment(
    navController: NavHostController,
    viewModel: MainViewModel,
    numOfFragment: Int,
) {
    val fragmentName = getFragmentNameByNum(numOfFragment)

    navigate(
        navController,
        GENERAL_FRAGMENT,
        onCategoryClicked = { viewModel.onCategoryClicked(fragmentName) })
}

private fun animateBackgroundColor(
    states: SnapshotStateList<Boolean>,
    numOfButton: Int,
    onAnimationEnd: () -> Unit,
) {
    states[numOfButton] = true
    CoroutineScope(Dispatchers.IO).launch {
        delay(500)
        CoroutineScope(Dispatchers.Main).launch {
            onAnimationEnd()
        }
        states[numOfButton] = false
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun BlessedButton(onBlessedButtonClick: () -> Unit) {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 1.5f else 1f)

    Box(
        modifier = Modifier
            .size(220.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = CircleShape,
            ),
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .size(200.dp)
                .scale(scale.value)
                .align(Alignment.Center)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = CircleShape,
                )
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            selected.value = true
                            onBlessedButtonClick()
                        }

                        MotionEvent.ACTION_UP -> {
                            CoroutineScope(Dispatchers.IO).launch {
                                delay(50)
                                selected.value = false
                            }
                        }
                    }
                    true
                },
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = stringResource(id = R.string.blessing_button_text),
                    lineHeight = 35.sp,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.ExtraBold,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false,
                        ),
                    ).merge(
                        MaterialTheme.typography.displayMedium,
                    ),
                    textAlign = TextAlign.Center,
                )
                Image(
                    painterResource(id = R.drawable.blessing),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }
        }
    }
}

@Composable
private fun TopTexts() {
    val numberOfBlessingsToday = LocalContext.current.getNumberOfBlessingsToday()
    val blessingsToGo = if (numberOfBlessingsToday < 100) {
        100 - numberOfBlessingsToday
    } else {
        0
    }

    val numberOfBlessingsTodayGeneral = TextFormatter.numberWithCommas(10203)
    val blessingsToGoGeneral = 100 - (10203 % 100)

    DefaultAnnotatedText(
        textMultiStyle(
            String.format(
                stringResource(id = R.string.main_fragment_msg1),
                numberOfBlessingsToday,
            ),
            listOf(
                TextWithStyle(
                    customText = numberOfBlessingsToday.toString(),
                    style = TextStyle(fontWeight = FontWeight.ExtraBold),
                ),
            ),
        ),
    )
    if (numberOfBlessingsToday > 10) {
        DefaultText(
            stringResource(id = R.string.main_fragment_msg2),
        )
    }

    DefaultAnnotatedText(
        textMultiStyle(
            String.format(
                stringResource(id = R.string.main_fragment_msg3),
                blessingsToGo.toString(),
            ),
            listOf(
                TextWithStyle(
                    customText = blessingsToGo.toString(),
                    style = TextStyle(fontWeight = FontWeight.ExtraBold),
                ),
            ),
        ),
    )

    val text5 = String.format(
        stringResource(id = R.string.main_fragment_msg4),
        numberOfBlessingsTodayGeneral,
    )
    DefaultAnnotatedText(
        textMultiStyle(
            text5,
            listOf(
                TextWithStyle(
                    customText = text5,
                    style = TextStyle(fontWeight = FontWeight.Bold).merge(
                        TextStyle(
                            fontStyle = FontStyle.Italic,
                        ),
                    ),
                ),
            ),
        ),
        modifier = Modifier.padding(top = 16.dp),
        textSize = MaterialTheme.typography.bodyMedium,
        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )
    DefaultAnnotatedText(
        textMultiStyle(
            String.format(
                stringResource(id = R.string.main_fragment_msg5),
                blessingsToGoGeneral,
            ),
            listOf(
                TextWithStyle(
                    customText = blessingsToGoGeneral.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                ),
            ),
        ),
        textSize = MaterialTheme.typography.titleMedium.merge(TextStyle(fontWeight = FontWeight.Bold)),
    )
}

data class TextWithStyle(
    val customText: String,
    val style: TextStyle,
)

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    AppTheme {
        ScreenContent(rememberNavController(), MainViewModel(), onBlessedButtonClick = {})
    }
}
