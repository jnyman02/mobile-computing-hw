package com.jnyman.homeworkapp

import SampleData
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.jnyman.homeworkapp.database.Conversation
import com.jnyman.homeworkapp.database.ConversationDao
import com.jnyman.homeworkapp.database.ConversationDatabase
import com.jnyman.homeworkapp.database.Profile
import com.jnyman.homeworkapp.database.ProfileDatabase
import com.jnyman.homeworkapp.settings.ProfileSetting
import com.jnyman.homeworkapp.settings.Settings
import com.jnyman.homeworkapp.ui.theme.HomeworkAppTheme
import com.jnyman.homeworkapp.NotificationService.Companion.CHANNEL_ID

class MainActivity : ComponentActivity() {

    private val profileDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            ProfileDatabase::class.java, "profile-database"
        )
            .allowMainThreadQueries()
            .build()
    }

    private val conversationDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            ConversationDatabase::class.java, "conversation-database"
        )
            .allowMainThreadQueries()
            .build()
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "HomeworkApp Channel"
            val descriptionText = "Notification channel for HomeworkApp"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 0)
        }
        setContent {
            HomeworkAppTheme {

                createNotificationChannel()
                val service = NotificationService(this)

                val profileDao = profileDb.profileDao()
                val conversationDao = conversationDb.conversationDao()

                conversationDao.upsertConversation(SampleData.conversationSamples[0])

                var conversations = SampleData.conversationSamples
//                conversationDao.getConversationsOrderedByName()

                // send notification telling it is dark when light level sensed by light sensor is less than 60 lux
                val lightSensor = LightSensor(this)

                var prevLightLevel by remember {
                    mutableFloatStateOf(0f)
                }

                lightSensor.startListening()
                lightSensor.setOnSensorValueChangeListener { lightLevel ->
                    if (lightLevel[0] < 60f && prevLightLevel >= 60f) {
                        service.sendNotification()
                    }
                    prevLightLevel = lightLevel[0]
                }

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home-screen") {
                    composable("home-screen") {
                        HomeScreen(
                            conversations = conversations,
                            onNavigateToConversation = { conversationName -> navController.navigate(conversationName) },
                            onNavigateToSettings = { navController.navigate("settings") },
                            lightLevel = prevLightLevel
                        )
                    }
                    conversations.forEach { conversation ->
                        composable(conversation.name) {
                            ConversationCard(
                                conversation = conversation,
                                onNavigateToHomeScreen = {
                                    navController.navigate("home-screen") {
                                        popUpTo("home-screen") {
                                            inclusive = true
                                        }
                                    }
                                },
                                dao = profileDao
                            )
                        }
                    }
                    composable("settings") {
                        Settings(
                            onNavigateToSetting = { settingName -> navController.navigate(settingName) }
                        )
                    }
                    composable("profile-setting") {
                        ProfileSetting(
                            onNavigateToSettings = { navController.navigate("settings") {
                                popUpTo("settings") {
                                    inclusive = true
                                }
                            } },
                            dao = profileDao,
                            onNavigateToCamera = { navController.navigate("camera-screen") }
                        )
                    }
                    composable("camera-screen") {
                        CameraScreen(
                            onNavigateToProfileSettings = { navController.navigate("profile-setting") },
                            dao = profileDao
                        )
                    }
                }
            }
        }
    }

    private fun hasPermissions(): Boolean {
        return PERMISSIONS.all {
            ContextCompat.checkSelfPermission(applicationContext, it) == PackageManager.PERMISSION_GRANTED
        }

    }

    companion object {
        private val PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.POST_NOTIFICATIONS,
        )
    }


}
