package com.axppress.hundredblessings.ui.fragment.mainfragment

import android.content.res.Configuration
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
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
import com.axppress.hundredblessings.domain.remote.FirebaseDatabaseService
import com.axppress.hundredblessings.utils.BLESSING_FRAGMENT
import com.axppress.hundredblessings.utils.DefaultAnnotatedText
import com.axppress.hundredblessings.utils.DefaultText
import com.axppress.hundredblessings.utils.FRAGMENT_10
import com.axppress.hundredblessings.utils.FRAGMENT_5
import com.axppress.hundredblessings.utils.GENERAL_FRAGMENT
import com.axppress.hundredblessings.utils.MAIN_FRAGMENT
import com.axppress.hundredblessings.utils.TextFormatter
import com.axppress.hundredblessings.utils.getFragmentNameByNum
import com.axppress.hundredblessings.utils.getNumberOfMyBlessingsToday
import com.axppress.hundredblessings.utils.noRippleClick
import com.axppress.hundredblessings.utils.textMultiStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

var itemClicked = false
var screenWidth = 400
var iconSize = 40.dp

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    onBlessedButtonClick: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    screenWidth = configuration.screenWidthDp

    //DISABLE DOUBLE CLICKS
    if (navController.currentDestination?.route == MAIN_FRAGMENT) {
        itemClicked = false
    }
    ScreenContent(navController, viewModel) { onBlessedButtonClick() }
}

fun navigate(
    navController: NavHostController,
    name: String,
) {
    navController.navigate(name)
}

@Composable
private fun ScreenContent(
    navController: NavHostController,
    viewModel: MainViewModel,
    onBlessedButtonClick: () -> Unit,
) {
    val num = if (LocalInspectionMode.current)
        0
    else
        getNumberOfMyBlessingsToday(LocalContext.current)
    var numberOfBlessingsToday by remember {
        mutableIntStateOf(num)
    }
    Icon(
        painter = painterResource(id = R.drawable.ic_info),
        "info button",
        tint = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .size(48.dp)
            .padding(8.dp)
            .noRippleClick {
                viewModel.setCurrentFragment(FRAGMENT_10)
                navController.navigate("$BLESSING_FRAGMENT?blessing=${viewModel.getCurrentFragment()}?type=0?noSubList=${true}?isLong=${false}")
            }
    )



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        TopTexts(numberOfBlessingsToday)
        BottomPanel({
            onBlessedButtonClick()
            numberOfBlessingsToday++
        }, navController, viewModel)
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
            for (day in 0..10) {
                it.add(false)
            }
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .background(
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(Modifier.padding(top = (screenWidth / 3).dp, start = 8.dp, end = 8.dp)) {
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
                                color = if (states[3]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 3)
                            },
                    ) {
                        DefaultText(
                            "ראייה",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp,
                                    bottom = 4.dp,
                                    top = 8.dp,
                                    end = 8.dp,
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                        )
                        Image(
                            painterResource(id = R.drawable.eye),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(iconSize)
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
                                color = if (states[2]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 2)
                            },
                    ) {
                        DefaultText(
                            "מזון",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                        )
                        Image(
                            painterResource(id = R.drawable.food),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(iconSize)
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
                                color = if (states[1]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
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
                                    start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,

                            )
                        Image(
                            painterResource(id = R.drawable.ear),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(iconSize)
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
                                color = if (states[0]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .noRippleClick {
                                onButtonClick(states, navController, viewModel, 0)
                            },
                    ) {
                        DefaultText(
                            "ריח",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,

                            )
                        Image(
                            painterResource(id = R.drawable.nose),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(iconSize)
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
                                color = if (states[6]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
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
                                    start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                           lineHeight = 16.sp,
                            )
                        Image(
                            painterResource(id = R.drawable.car),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(iconSize)
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
                                color = if (states[5]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
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
                                    start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                            lineHeight = 16.sp,
                            )
                        Image(
                            painterResource(id = R.drawable.sleeping),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(iconSize)
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
                                color = if (states[4]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
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
                                    top = 8.dp,
                                    end = 8.dp,
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                            lineHeight = 16.sp,
                            )
                        Image(
                            painterResource(id = R.drawable.sunrise),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(iconSize)
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
                                color = if (states[9]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
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
                                    top = 8.dp,
                                    end = 8.dp,
                                ),
                            textStyleAndSize = MaterialTheme.typography.bodyLarge,
                            lineHeight = 16.sp,
                            )
                        Image(
                            painterResource(id = R.drawable.tshirt),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(iconSize)
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
                            color = if (states[7]) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer,
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
                                top = 8.dp,
                                end = 8.dp,
                            ),
                        textStyleAndSize = MaterialTheme.typography.bodyLarge,
                    )
                    Image(
                        painterResource(id = R.drawable.king),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(iconSize)
                            .padding(start = 8.dp, bottom = 16.dp, end = 8.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                }/*                Column(
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
                                }*/
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
    if (!itemClicked) {
        itemClicked = true
        val fragmentName = getFragmentNameByNum(numOfFragment)
        viewModel.onCategoryClicked(fragmentName)

        animateBackgroundColor(states, numOfFragment) {
            if (fragmentName == FRAGMENT_5) {
                navController.navigate(
                    "$BLESSING_FRAGMENT?blessing=$fragmentName?type=${0}?noSubList=${false}?isLong=${
                        true
                    }"
                )
            } else navigateToGeneralFragment(
                navController, fragmentName, numOfFragment
            )
        }
    }
}

private fun navigateToGeneralFragment(
    navController: NavHostController,
    fragmentName: String,
    numOfFragment: Int,
) {
    navigate(
        navController, GENERAL_FRAGMENT
    )
}

private fun animateBackgroundColor(
    states: SnapshotStateList<Boolean>,
    numOfButton: Int,
    onAnimationEnd: () -> Unit,
) {
    states[numOfButton] = true
    CoroutineScope(Dispatchers.IO).launch {
        delay(70)
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
            .size((screenWidth.dp / 2) + 20.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = CircleShape,
            ),
    ) {

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .size(screenWidth.dp / 2)
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
                        if (screenWidth < 400)
                            MaterialTheme.typography.displaySmall
                        else
                            MaterialTheme.typography.displayMedium,
                    ),
                    textAlign = TextAlign.Center,
                )
                Image(
                    painterResource(id = R.drawable.blessing),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(screenWidth.dp / 2 / 4)
                        .align(Alignment.CenterHorizontally),
                )
            }
        }
    }
}

@Composable
private fun TopTexts(numberOfBlessingsToday: Int) {
    val blessingsToGo = if (numberOfBlessingsToday < 100) {
        100 - numberOfBlessingsToday
    } else {
        0
    }
    val isLocalInspectionMode = LocalInspectionMode.current
    var numberOfBlessingsTodayGeneral by remember {
        if (!isLocalInspectionMode)
            mutableIntStateOf(FirebaseDatabaseService.instance.numOfAllPeopleBlessingsToday.value)
        else
            mutableIntStateOf(0)
    }
    var numberOfBlessingsTodayGeneralFormatted =
        TextFormatter.numberWithCommas(numberOfBlessingsTodayGeneral)
    var blessingsToGoGeneral = 100 - (numberOfBlessingsTodayGeneral % 100)


    LaunchedEffect(key1 = Unit) {
        launch {
            FirebaseDatabaseService.instance.numOfAllPeopleBlessingsToday.collectLatest {
                numberOfBlessingsTodayGeneral = it
                numberOfBlessingsTodayGeneralFormatted =
                    TextFormatter.numberWithCommas(it)
                blessingsToGoGeneral = 100 - (it % 100)
            }
        }
    }



    TopTextsScreenContent(
        numberOfBlessingsToday,
        blessingsToGo,
        numberOfBlessingsTodayGeneralFormatted,
        blessingsToGoGeneral
    )
}

@Composable
private fun TopTextsScreenContent(
    numberOfBlessingsToday: Int,
    blessingsToGo: Int,
    numberOfBlessingsTodayGeneralFormatted: String,
    blessingsToGoGeneral: Int,
) {
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

    if (numberOfBlessingsToday < 100) {
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
            textSize = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
        )
    }

    val text5 = String.format(
        stringResource(id = R.string.main_fragment_msg4),
        numberOfBlessingsTodayGeneralFormatted,
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
        textSize = MaterialTheme.typography.bodyMedium.merge(TextStyle(fontWeight = FontWeight.Bold)),
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
