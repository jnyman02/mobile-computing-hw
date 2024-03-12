package com.jnyman.homeworkapp.settings

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.jnyman.homeworkapp.database.Profile
import com.jnyman.homeworkapp.database.ProfileDao
import com.jnyman.homeworkapp.ui.theme.HomeworkAppTheme
import java.io.File
import java.io.FileOutputStream


@Composable
fun ProfileSetting(onNavigateToSettings: () -> Unit, onNavigateToCamera: () -> Unit, dao: ProfileDao) {

    var profile = dao.getOwnProfile()

    if (profile == null) {
        profile = Profile(
            nickName = "New Profile",
            profilePictureUri = "",
            own = true,
            id = 1
        )
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.parse(profile.profilePictureUri))
    }

    var tempImageUri by remember {
        mutableStateOf<Uri?>(selectedImageUri)
    }

    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri: Uri? ->
            selectedImageUri = uri
            uri?.let {
                tempImageUri = saveImageLocally(context, it)
            }
            dao.upsertProfile(
                Profile(
                    nickName = profile.nickName,
                    profilePictureUri = tempImageUri.toString(),
                    own = profile.own,
                    id = profile.id
                )
            )
            profile = dao.getOwnProfile()
        }
    )

    // Add padding around the message
    Column(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = selectedImageUri,
            contentDescription = null,
            modifier = Modifier
                // Set image size to 40 dp
                .size(200.dp)
                // Clip image to circle shape
                .clip(CircleShape)
                // Add a colored border around image
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .clickable {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
            contentScale = ContentScale.Crop
        )

        // Button to take new picture
        Button(
            onClick = { onNavigateToCamera() },
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 2.dp)
        ) {
            Text(
                text ="Take New Picture",
                fontSize = 12.sp,
            )
        }

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.height(40.dp))

        // We toggle the isExpanded variable when we click on this Column

        Column() {
            Text(
                text = "Nickname:",
                // Change text color
                color = MaterialTheme.colorScheme.secondary,
                // Change the typography style
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Surface(
                shape = MaterialTheme.shapes.extraSmall,
                shadowElevation = 1.dp,
            ) {
                var nickname by remember {
                    mutableStateOf(profile.nickName)
                }
                TextField(
                    value = nickname,
                    onValueChange = { newNickname ->
                        nickname = newNickname
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            dao.upsertProfile(
                                Profile(
                                    nickName = nickname,
                                    profilePictureUri = profile.profilePictureUri,
                                    own = profile.own,
                                    id = profile.id
                                )
                            )
                            profile = dao.getOwnProfile()
                        }
                    )
                )
            }

        }
    }
}

fun saveImageLocally(context: Context, uri: Uri): Uri? {

    val inputStream = context.contentResolver.openInputStream(uri)

    val localAppFolder = File(context.filesDir, "profile_images")
    if (!localAppFolder.exists()) {
        localAppFolder.mkdirs()
    }

    val localFile = File(localAppFolder, "pfp.jpg")

    if (localFile.exists()) {
        localFile.delete()
    }

    val outputStream = FileOutputStream(localFile)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }

    return localFile.toUri()
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
//fun PreviewSetting() {
//    HomeworkAppTheme {
//        ProfileSetting(
//            onNavigateToSettings = { Log.d("NAVIGATION", "Navigating to settings") }
//        )
//    }
//}