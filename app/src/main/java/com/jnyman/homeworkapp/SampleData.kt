import com.jnyman.homeworkapp.Conversation
import com.jnyman.homeworkapp.Message

/**
 * SampleData for Jetpack Compose Tutorial 
 */
object SampleData {
    // Sample conversation data

    val conversationSample = listOf(
        Message(
            "Colleague",
            "Test...Test...Test..."
        ),
        Message(
            "Colleague",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "JeppeNyman",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Colleague",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Colleague",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Colleague",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Colleague",
            "It's available from API 21+ :)"
        ),
        Message(
            "Colleague",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Colleague",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Colleague",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Colleague",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Colleague",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Colleague",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Colleague",
            "Have you tried writing build.gradle with KTS?"
        ),
    )

    val conversationSample2 = listOf(
        Message(
            "Alice",
            "Anyone else excited about the recent breakthroughs in quantum computing?"
        ),
        Message(
            "Bob",
            """Did you know there are different types of quantum computers:
        |1. Quantum Annealers
        |2. Gate-based Quantum Computers""".trim()
        ),
        Message(
            "Charlie",
            """I've been exploring functional programming languages lately.
        |Haskell and Lisp are quite fascinating!""".trim()
        ),
        Message(
            "Dave",
            "Looking for the best algorithms to solve the traveling salesman problem efficiently."
        ),
        Message(
            "Eve",
            """Functional programming languages promote immutability and pure functions.
        |Have you ever tried writing a purely functional application?""".trim()
        ),
        Message(
            "Frank",
            "The advancements in AI and machine learning are reshaping industries rapidly."
        ),
        Message(
            "Grace",
            "Have you heard about the latest breakthroughs in natural language processing?"
        ),
        Message(
            "Hank",
            "I'm currently exploring decentralized finance (DeFi) platforms and blockchain technology."
        ),
        Message(
            "Ivy",
            "Smart contracts have the potential to revolutionize the way we conduct business transactions."
        ),
        Message(
            "Jack",
            "The intersection of technology and sustainability is a key focus for innovation."
        ),
        Message(
            "Kate",
            "Have you ever tried developing software using a serverless architecture?"
        ),
        Message(
            "Leo",
            "Quantum machine learning is an emerging field with promising potential."
        ),
        Message(
            "Mia",
            "Functional reactive programming brings a new paradigm to building responsive and scalable systems."
        )
    )

    val conversationSamples = listOf(Conversation("Sample Conversation 1", conversationSample), Conversation("Sample Conversation 2", conversationSample2))

}
