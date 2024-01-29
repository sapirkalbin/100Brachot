package com.axppress.hundredblessings.ui.activity

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.axppress.hundredblessings.R
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.domain.remote.FirebaseDatabaseService
import com.axppress.hundredblessings.ui.fragment.blessingfragment.BlessingScreen
import com.axppress.hundredblessings.ui.fragment.generalfragment.GeneralBlessingsScreen
import com.axppress.hundredblessings.ui.fragment.mainfragment.KeysViewModel
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainScreen
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import com.axppress.hundredblessings.utils.BLESSING_FRAGMENT
import com.axppress.hundredblessings.utils.GENERAL_FRAGMENT
import com.axppress.hundredblessings.utils.MAIN_FRAGMENT
import org.koin.androidx.compose.koinViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<KeysViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseDatabaseService.instance.initializeMyBlessings(this)
        FirebaseDatabaseService.instance.getBlessingsToday()

        setContent {
            AppTheme {
                val navController = rememberNavController()
                val viewModel = koinViewModel<MainViewModel>()
                val keysViewModel = koinViewModel<KeysViewModel>()

                Surface {
                    NavHost(navController = navController, startDestination = MAIN_FRAGMENT) {
                        composable(MAIN_FRAGMENT) {
                            MainScreen(navController, viewModel) {
                                addBlessings(1)
                            }
                        }

                        composable(GENERAL_FRAGMENT) {
                            GeneralBlessingsScreen(viewModel, navController)
                        }

                        composable(
                            route = "$BLESSING_FRAGMENT?blessing={blessing}?type={type}?noSubList={noSubList}?isLong={isLong}",
                            arguments = listOf(
                                navArgument("blessing") {
                                    type = NavType.StringType
                                },
                                navArgument("type") {
                                    type = NavType.IntType
                                },
                                navArgument("noSubList") {
                                    type = NavType.BoolType
                                },
                                navArgument("isLong") {
                                    type = NavType.BoolType
                                },
                            )
                        )
                        {
                            val blessing = it.arguments?.getString("blessing", "") ?: ""
                            val type = it.arguments?.getInt("type") ?: 0
                            val noSubList = it.arguments?.getBoolean("noSubList") ?: false
                            val isLong = it.arguments?.getBoolean("isLong") ?: false

                            val listOfButtons = if (noSubList)
                                listOf()
                            else
                                viewModel.getBlessingListButtons()

                            BlessingScreen(
                                blessing,
                                type,
                                isLong,
                                listOfButtons,
                                viewModel,
                                keysViewModel,
                                navController, {
                                    val browserIntent =
                                        Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                    startActivity(browserIntent)
                                }, {
                                    addBlessings(it)
                                })
                        }
                    }
                }
            }
        }
    }

    private fun addBlessings(blessings: Int) {
        playSound()
        FirebaseDatabaseService.instance.addBlessings(blessings, this)
    }

    private fun playSound() {
        val mp = MediaPlayer.create(this, R.raw.harp_sound)
        mp.start()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                viewModel.volumeDown()
                return true
            }

            KeyEvent.KEYCODE_VOLUME_UP -> {
                viewModel.volumeUp()
                return true
            }

            KeyEvent.KEYCODE_BACK -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }

            else -> return false
        }
    }
}
