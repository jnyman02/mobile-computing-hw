package com.jnyman.homeworkapp.settings

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnyman.homeworkapp.ui.theme.HomeworkAppTheme

@Composable
fun Settings(onNavigateToSetting: (settingName: String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(all = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Settings",
            // Change text color
            color = MaterialTheme.colorScheme.secondary,
            // Change the typography style
            style = MaterialTheme.typography.titleLarge,
            fontSize = 26.sp
        )
        LazyColumn {
            items(SettingList.settings) { setting ->
                SettingPreview(name = setting.name, text = setting.text, onNavigateToSetting = onNavigateToSetting)
            }
        }
    }
}

@Composable
fun SettingPreview(name: String, text: String, onNavigateToSetting: (settingName: String) -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxSize()
            .clickable { onNavigateToSetting(name) },

    ) {
        Text(
            text = text,
            modifier = Modifier.padding(all = 8.dp),
            fontSize = 18.sp
        )
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun PreviewSettings() {
    HomeworkAppTheme {
        Settings(
            onNavigateToSetting = { settingName ->
                Log.d("NAVIGATION", "Navigating to $settingName")
            }
        )
    }
}