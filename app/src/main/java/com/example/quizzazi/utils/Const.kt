package com.example.newsapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.rememberImagePainter
import com.example.newsapp.utils.Constance.FourTeenDp
import com.example.newsapp.utils.Constance.SixDp
import com.example.quizzazi.R
import com.example.quizzazi.domain.model.quiz.CategoryModel
import com.example.quizzazi.utils.getBlackToWhiteColor
import com.example.quizzazi.utils.getDarkToLightColor
import java.time.LocalDateTime


object Constance {
    val MediumTwentyFourDp = 24.dp
    val MediumThirtyDp = 30.dp
    val SixDp = 6.dp
    val FourTeenDp = 14.dp
    val TwelveSp = 12.sp
    val FiftyTwoDp = 52.dp
    val NinetySixDp = 96.dp
    val FourDp = 4.dp
    val TwelveDp = 12.dp
    val ThirtyDp = 30.dp
    val SixTeenDp = 16.dp
    val TwentyDp = 20.dp
    val FourtyDp = 40.dp
    val TwentySixDp = 26.dp
    const val animationTime = 1000
    val HundredFiftyDp = 150.dp
    val ArticleImageHeight = 248.dp
    const val DURATION = 1000
    const val title = "Pump up your brain and find out how much you know with QuizAzi"
    const val title1 = "Compete with other players as you conquer mountains higher and higher"
    const val title2 = "Ready to begin? Then press the Get Started button and full speed ahead!"
    const val COLLECTION_NAME_USERS = "users"
    const val ERROR_MESSAGE_PREFIX = "Error: "
    const val INITIAL_TIME = 20000L
    const val TOTAL_QUESTIONS = 5
    const val allTimeScore = "allTimeScore"
    const val weeklyScore = "weeklyScore"
    const val monthlyScore = "monthlyScore"
    const val lastGameScore = "lastGameScore"
}


@Composable
fun Toast(message: String) {
    android.widget.Toast.makeText(LocalContext.current, message, android.widget.Toast.LENGTH_SHORT).show()
}

@Composable
fun ReusableCard(
    modifier: Modifier = Modifier,
    labelText: String,
    questionCount: String,
    circleColor: Color,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = CardDefaults.cardColors(
            containerColor = getDarkToLightColor()
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(getDarkToLightColor())
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(circleColor))
                    Text(
                        text = labelText,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

            }
            Text(
                text = questionCount,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun BaseText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
        Text(
            text = text,
            color = color,
            modifier = modifier,
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
            fontSize =  22.sp
        )
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor =  getDarkToLightColor(),
            contentColor = getBlackToWhiteColor()
        ),
        shape = RoundedCornerShape(size = SixDp),
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = FourTeenDp)
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoField(
    values: String,
    label: String,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = values,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = getDarkToLightColor(),
            unfocusedIndicatorColor = Color.Black,
            focusedIndicatorColor = getDarkToLightColor(),
            focusedLabelColor =  getDarkToLightColor(),
            unfocusedLabelColor = Color.Black,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 18.dp, vertical = 4.dp)
            .fillMaxWidth(),

        )

}

@Composable
fun GradientButton(
    modifier: Modifier,
    text: String = "",
    textColor: Color,
    iconResId: Int? = null,
    iconBitmap: ImageBitmap? = null,
    iconUrl: String? = null,
    boxModifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
           containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        onClick = { onClick() }
    ) {
        Box(
            modifier = boxModifier,
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                when {
                    iconBitmap != null -> {
                        Image(bitmap = iconBitmap, contentDescription = null, modifier = Modifier
                            .size(24.dp)
                            .padding(start = 8.dp))
                    }
                    iconResId != null -> {
                        Image(painter = painterResource(id = iconResId), contentDescription = null, modifier = Modifier
                            .size(24.dp)
                            .padding(start = 8.dp))
                    }
                    iconUrl != null -> {
                        Image(painter = rememberImagePainter(iconUrl), contentDescription = null, modifier = Modifier
                            .size(48.dp)
                            .padding(start = 0.dp))
                    }
                }
                Text(
                    text = text,
                    color = textColor,
                    fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

val phrases = listOf(
    "Today is a great day to  improve yourself !",
    "Absorbing knowledge is    the basis !",
    "Test yourself and your    versatility !",
    "Love you music? You're in the right place !",
    "Anime? This is your ninja way !",
    "The men in black will     test you",
    "2 + 2 = 4 are you sure?"
)


fun getGreeting(): String {
    return when (LocalDateTime.now().toLocalTime().hour) {
        in 6..11 -> "Good morning"
        in 12..17 -> "Guten Tag"
        in 18..22 -> "Good evening"
        else -> "Can't sleep?"
    }
}

@Composable
fun decodeHtmlString(htmlEncoded: String): String {
    return HtmlCompat.fromHtml(htmlEncoded, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}
fun getCategoryItemList():ArrayList<CategoryModel>
{
    val list = ArrayList<CategoryModel>()
    list.add(
        CategoryModel("9", R.drawable.book, "General Knowledge")
    )
    list.add(
        CategoryModel("10",R.drawable.bookkn,"Books")
    )
    list.add(
        CategoryModel("11",R.drawable.watchingmovie,"Film")
    )
    list.add(
        CategoryModel("12",R.drawable.guitar,"Music")
    )
    list.add(
        CategoryModel("13",R.drawable.theatre,"Theatres")
    )
    list.add(
        CategoryModel("14",R.drawable.movie,"Television")
    )
    list.add(
        CategoryModel("15",R.drawable.gameconsole,"Video Games")
    )
    list.add(
        CategoryModel("16",R.drawable.boardgame,"Board Games")
    )
    list.add(
        CategoryModel("17",R.drawable.datascience,"Science & Nature")
    )
    list.add(
        CategoryModel("18",R.drawable.pc,"Computer")
    )
    list.add(
        CategoryModel("19",R.drawable.calculating,"Mathematics")
    )
    list.add(
        CategoryModel("20",R.drawable.mythology,"Mythology")
    )
    list.add(
        CategoryModel("21",R.drawable.sports,"Sports")
    )
    list.add(
        CategoryModel("22",R.drawable.geography,"Geography")
    )
    list.add(
        CategoryModel("23",R.drawable.history,"History")
    )
    list.add(
        CategoryModel("24",R.drawable.politics,"Politics")
    )
    list.add(
        CategoryModel("25",R.drawable.palette,"Art")
    )
    list.add(
        CategoryModel("26",R.drawable.confetti,"Celebrities")
    )
    list.add(
        CategoryModel("27",R.drawable.livestock,"Animals")
    )
    list.add(
        CategoryModel("28",R.drawable.vehicles,"Vehicles")
    )
    list.add(
        CategoryModel("29",R.drawable.comic,"Comics")
    )
    list.add(
        CategoryModel("30",R.drawable.gadgets,"Gadgets")
    )
    list.add(
        CategoryModel("31",R.drawable.cosplayer,"Anime & Manga")
    )

    list.add(
        CategoryModel("32",R.drawable.leonardo,"Cartoons")
    )
    return list
}

// In TriviaGameScreen



