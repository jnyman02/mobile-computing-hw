package com.jnyman.homeworkapp

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnyman.homeworkapp.database.Conversation
import com.jnyman.homeworkapp.database.ProfileDao
import com.jnyman.homeworkapp.ui.theme.HomeworkAppTheme

@Composable
fun ConversationCard(conversation: Conversation, onNavigateToHomeScreen: () -> Unit, dao: ProfileDao) {
    Column {
        Button(onClick = { onNavigateToHomeScreen() }, modifier = Modifier.padding(vertical = 14.dp, horizontal = 8.dp)) {
            Text(text = "Home")
        }
        LazyColumn {
            items(conversation.messages) { message ->
                MessageCard(
                    msg = message,
                    dao = dao
                )
            }
        }
    }
    
}

//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    name = "Light Mode"
//)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    name = "Dark Mode"
//)
//@Composable
//fun PreviewConversationCard() {
//    HomeworkAppTheme {
//        ConversationCard(
//            Conversation("Sample Conversation", SampleData.conversationSample),
//            onNavigateToHomeScreen = { Log.d("NAVIGATION", "Navigating to home")
//            })
//    }
//}