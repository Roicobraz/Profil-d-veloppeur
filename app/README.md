# Profil développeur Compose
TP jetpack compose de création d'un profil développeur, avec nom, prénom, le poste occupé, 
les compétences techniques, ainsi qu'une rapide description de la personne, la possiblité d'ajouter 
en ami (non fonctionnel). 

## Notions utilisées
- Composables
- Previews
- Modifiers
- Text
- Buttons
- Column
- Padding

## Explication courte
1. Les composables créés sont : 
   - ProfilScreen
   - ProfilCard
   - ProfilCardContent
   - ProfilNameRole
   - ProfilDescription
   - ProfilSkills
   - ProfilContact
   - FriendButton
2. Le Composable représentant l'écran principal est `ProfilScreen`.
3. J'utilise le padding externe dans le composable `ProfilCard`.
4. J'utilise le padding interne dans le composable `ProfilSkills`.
5. Le Composant `ProfilContact` reçoit un callback, pour proposer ou notifier que l'on a le profil en ami.
6. L'information d'accessibilité est le bouton `ajouter en ami`/`ProfilContact` le profil de la personne.
7. Mes composant peuvent être considéré comme réutilisables car il suffit d'importer le fichier et 
d'appeler les fonctions. 

![preview](preview.png)