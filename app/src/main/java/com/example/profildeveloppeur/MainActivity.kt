package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDéveloppeurTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDéveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfilScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfilScreen(modifier: Modifier = Modifier) {
    ProfilCard(
        name = "Justin VAVASSORI",
        role = "Architecte Logiciel",
        description = "Je suis apprenti architecte logiciel en alternance chez iQera Services.",
        skills = listOf("PHP", "JavaScript", "C", "Python"),
        contact = "j.vavassori@myskolae.fr",
        isFriend = false,
        onFriendClick = {},
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    )
}

@Composable
fun ProfilCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    contact: String,
    isFriend: Boolean,
    onFriendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProfilCardContent(
                name = name,
                role = role,
                description = description,
                skills = skills,
                contact = contact,
                onFriendClick = {},
                isFriend = isFriend
            )
        }
    }
}

@Composable
fun ProfilCardContent(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    contact: String,
    isFriend: Boolean,
    onFriendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ProfilNameRole(
        name = name,
        role = role,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = modifier.padding(start = 8.dp, end = 8.dp)
        ) {
        ProfilDescription(
            description = description,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ProfilSkills(
            skills = skills,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ProfilEmailField(
            emailInformation = contact,
            modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
        )
        FriendButton(
            isFriend = isFriend,
            onFriendClick = onFriendClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ProfilNameRole(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(top = 10.dp, start = 8.dp, end = 8.dp)
    ) {
        ProfilImage(
            profilTitle = name,
            modifier = Modifier
                .padding(end = 16.dp)
        )
        Text(
            text = "$name • $role",
            modifier = modifier
        )
    }
}

@Composable
fun ProfilEmailField(
    emailInformation: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = emailInformation,
        onValueChange = {},
        readOnly = true,
        label = {
            Text(text = "E-mail")
        },
        modifier = modifier
    )
}


@Composable
fun ProfilDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = description,
        modifier = modifier
    )
}

@Composable
fun ProfilSkills(
    skills: List<String>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Liste des compétences : ",
        modifier = modifier
    )
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(skills) { category ->
            SkillsItem(label = category)
        }
    }
}

@Composable
fun SkillsItem(
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
        )
    }
}

@Composable
fun ProfilContact(
    contact: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = contact,
        modifier = modifier
    )
}

@Composable
fun FriendButton(
    isFriend: Boolean,
    onFriendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onFriendClick,
        modifier = modifier
    ) {
        Text(
            text = if (isFriend) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Note du produit"
                )
                " Ami"
            } else {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Note du produit"
                )
                " Ajouter en ami"
            }
        )
    }
}

@Composable
fun ProfilImage(
    profilTitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(0.dp)
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = profilTitle,
            modifier = modifier
        )
    }
}

/**
 * Previews
 */
@Preview(showBackground = true)
@Composable
fun ProfilScreenPreview() {
    ProfilDéveloppeurTheme {
        ProfilScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilIsFriendScreenPreview() {
    ProfilDéveloppeurTheme {
        ProfilCard(
            name = "Justin VAVASSORI",
            role = "Architecte Logiciel",
            description = "Je suis apprenti architecte logiciel en alternance chez iQera Services.",
            skills = listOf("PHP", "JavaScript", "C", "Python"),
            contact = "LinkedIn",
            isFriend = true,
            onFriendClick = {},
        )
    }
}