package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.text.contains

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
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
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

@Composable
fun ProfilProject(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = {
                    Text(text = "Rechercher un projet")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ProjectCatalogScreen(
    projects: List<ProjectUi>,
    techs: List<String>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    showOnlyInStock: Boolean,
    onToggleStockFilter: () -> Unit,
    favoriteProjectIds: List<Int>,
    onFavoriteClick: (Int) -> Unit,
    onProjectClick: (ProjectUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = {
                    Text(text = "Rechercher un projet")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Button(
                onClick = onToggleStockFilter
            ) {
                Text(
                    text = if (showOnlyInStock) {
                        "Afficher tous les projets"
                    } else {
                        "Afficher uniquement les projets favoris"
                    }
                )
            }
        }
        item {
            Text(
                text = "Portfolio",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        item {
            Text(
                text = "Explorez les projets.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        item {
            techsRow(techs = techs)
        }
        items(
            items = projects,
            key = { project -> project.id }
        ) { project ->
            ProjectListItem(
                project = project,
                isFavorite = favoriteProjectIds.contains(project.id),
                onFavoriteClick = {
                    onFavoriteClick(project.id)
                },
                onClick = {
                    onProjectClick(project)
                }
            )
        }
    }
}

@Composable
fun techsRow(
    techs: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(techs) { category ->
            TechChip(label = category)
        }
    }
}

@Composable
fun TechChip(
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun ProjectListItem(
    project: ProjectUi,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = project.tech,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = project.description,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onFavoriteClick) {
                Text(
                    text = if (isFavorite) {
                        "Retirer des favoris"
                    } else {
                        "Ajouter aux favoris"
                    }
                )
            }
        }
    }
}


/**
 * Previews
 */
@Preview(
    showBackground = true,
    name = "Profile - Light Theme"
)
@Composable
fun ProfilScreenPreview() {
    ProfilDéveloppeurTheme(darkTheme = false) {
        ProfilScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Profile - Dark Theme"
)
@Composable
fun ProfilIsFriendScreenPreview() {
    ProfilDéveloppeurTheme(darkTheme = true) {
        ProfilScreen()
        ProjectCatalogScreen(
            projects = sampleProjects(),
            techs = sampleTechs(),
            searchQuery = "",
            onSearchQueryChange = {},
            showOnlyInStock = true,
            onToggleStockFilter = {},
            favoriteProjectIds = listOf(2),
            onFavoriteClick = {},
            onProjectClick = {}
        )
    }
}

/**
 * Datas
 */
data class ProjectUi(
    val id: Int,
    val title: String,
    val tech: String,
    val gitLink: String,
    val description: String
)

class ProjectCatalogViewModel : ViewModel() {
    private val allProjects: List<ProjectUi> = sampleProjects()
    private val allTech: List<String> = sampleTechs()
    private val _uiState = MutableStateFlow(
        ProjectCatalogUiState(
            projects = allProjects,
            techs = allTech
        )
    )
    val uiState: StateFlow<ProjectCatalogUiState> = _uiState.asStateFlow()
    private fun filterProjects(searchQuery: String): List<ProjectUi> {
        return allProjects.filter { project ->
            val matchesSearch =
                project.title.contains(searchQuery, ignoreCase = true) ||
                        project.tech.contains(searchQuery, ignoreCase = true)
            matchesSearch
        }
    }

    fun onSearchQueryChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = newValue,
                projects = filterProjects(
                    searchQuery = newValue
                )
            )
        }
    }

    fun onFavoriteClick(projectId: Int) {
        _uiState.update { currentState ->
            val newFavoriteIds =
                if (currentState.favoriteProjectIds.contains(projectId)) {
                    currentState.favoriteProjectIds - projectId
                } else {
                    currentState.favoriteProjectIds + projectId
                }
            currentState.copy(
                favoriteProjectIds = newFavoriteIds
            )
        }
    }
}

data class ProjectCatalogUiState(
    val projects: List<ProjectUi> = emptyList(),
    val techs: List<String> = emptyList(),
    val searchQuery: String = "",
    val favoriteProjectIds: List<Int> = emptyList()
)

fun sampleTechs(): List<String> {
    return listOf("PHP", "JavaScript", "C", "Python")
}

fun sampleProject(): ProjectUi {
    return ProjectUi(
        id = 4,
        title = "Projet C",
        tech = "C",
        gitLink = "https://github.com/Roicobraz/Profil-d-veloppeur/tree/cc3",
        description = "test",
    )
}

fun sampleProjects(): List<ProjectUi> {
    return listOf(
        ProjectUi(
            id = 1,
            title = "Projet PHP",
            tech = "PHP",
            gitLink = "https://github.com/Roicobraz/Profil-d-veloppeur/tree/cc3",
            description = "test",
        ),
        ProjectUi(
            id = 2,
            title = "Projet JS",
            tech = "JavaScript",
            gitLink = "https://github.com/Roicobraz/Profil-d-veloppeur/tree/cc3",
            description = "test",
        ),
        ProjectUi(
            id = 3,
            title = "Projet Python",
            tech = "Python",
            gitLink = "https://github.com/Roicobraz/Profil-d-veloppeur/tree/cc3",
            description = "test",
        ),
        sampleProject()
    )
}
