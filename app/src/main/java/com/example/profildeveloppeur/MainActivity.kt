package com.example.profildeveloppeur

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDéveloppeurTheme

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
        contact = "LinkedIn",
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
    Surface(
        modifier = modifier
            .clickable(
                role = Role.Button,
                onClick = onFriendClick
            )
            .semantics {
                contentDescription = "Ajouter $name en ami"
            }
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
    Column(
        modifier = modifier.padding(22.dp)
    ) {
        ProfilNameRole(
            name = name,
            role = role,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
    Column(
        modifier = modifier.padding(top = 50.dp, start = 8.dp, end = 8.dp)
        ) {
        ProfilDescription(
            description = description,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ProfilSkills(
            skills = skills,
            modifier = Modifier.padding(bottom = 8.dp),
            modifierElement = Modifier.padding(start = 12.dp)
        )
        ProfilContact(
            contact = contact,
            modifier = Modifier.padding(bottom = 8.dp)
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
    Text(
        text = "$name • $role",
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
    modifier: Modifier = Modifier,
    modifierElement: Modifier = Modifier
) {
    Text(
        text = "Liste des compétences : ",
        modifier = modifier
    )
    for (skill in skills)
    {
        Text(
            text = "- $skill",
            modifier = modifierElement
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
                "✅ Ami"
            } else {
                "+ Ajouter en ami"
            }
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