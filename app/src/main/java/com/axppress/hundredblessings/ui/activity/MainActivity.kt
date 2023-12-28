package com.axppress.hundredblessings.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axppress.hundredblessings.R
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.fragment.blessingfragment.BlessingScreen
import com.axppress.hundredblessings.ui.fragment.blessingfragment.BlessingScreenHtml
import com.axppress.hundredblessings.ui.fragment.generalfragment.GeneralBlessingsScreen
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainScreen
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import com.axppress.hundredblessings.utils.BLESSING_FRAGMENT
import com.axppress.hundredblessings.utils.BLESSING_HTML_FRAGMENT
import com.axppress.hundredblessings.utils.GENERAL_FRAGMENT
import com.axppress.hundredblessings.utils.MAIN_FRAGMENT
import org.koin.androidx.compose.koinViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val navController = rememberNavController()
                val viewModel = koinViewModel<MainViewModel>()

                Surface {
                    NavHost(navController = navController, startDestination = MAIN_FRAGMENT) {
                        composable(MAIN_FRAGMENT) {
                            MainScreen(navController, viewModel) {
                                playSound()
                            }
                        }

                        composable(GENERAL_FRAGMENT) {
                            GeneralBlessingsScreen(viewModel, navController)
                        }


                        composable(BLESSING_FRAGMENT) {
                            BlessingScreen(viewModel, navController)
                        }

                        composable(BLESSING_HTML_FRAGMENT) {
                            BlessingScreenHtml(viewModel)
                        }
                    }
                }
            }
        }
    }

    private fun playSound() {
        val mp = MediaPlayer.create(this, R.raw.harp_sound)
        mp.start()
    }
}
