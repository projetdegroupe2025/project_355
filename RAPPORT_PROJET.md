# RAPPORT DE PROJET

## Application de Gestion de Contacts Android

---

### Informations du Projet

**Nom du Projet:** Application de Gestion de Contacts  
**Plateforme:** Android  
**Langage:** Kotlin  
**Framework UI:** Jetpack Compose  
**Architecture:** MVVM (Model-View-ViewModel)

---

## 1. INTRODUCTION

Ce projet consiste en une application Android moderne de gestion de contacts, développée en utilisant les dernières technologies et pratiques recommandées par Google. L'application permet aux utilisateurs de créer, visualiser, modifier, supprimer et appeler des contacts de manière intuitive et efficace.

### 1.1 Objectifs du Projet

- Créer une application de gestion de contacts complète et fonctionnelle
- Implémenter l'architecture MVVM pour une séparation claire des responsabilités
- Utiliser Jetpack Compose pour une interface utilisateur moderne et déclarative
- Intégrer une base de données locale avec Room pour la persistance des données
- Appliquer l'injection de dépendances avec Hilt pour un code maintenable

---

## 2. ARCHITECTURE MVVM

L'application suit rigoureusement le pattern architectural **MVVM (Model-View-ViewModel)**, qui offre une séparation claire des responsabilités et facilite la testabilité et la maintenabilité du code.

### 2.1 Structure Architecturale

```
┌─────────────────────────────────────────┐
│              VIEW (UI)                  │
│  - Composables (Jetpack Compose)        │
│  - Écrans (Screens)                     │
│  - Composants réutilisables             │
└──────────────┬──────────────────────────┘
               │ observe
               ▼
┌─────────────────────────────────────────┐
│           VIEWMODEL                     │
│  - Logique de présentation              │
│  - Gestion d'état                       │
│  - Exposition des données via Flow      │
└──────────────┬──────────────────────────┘
               │ utilise
               ▼
┌─────────────────────────────────────────┐
│            MODEL                        │
│  - Entités de données (Contact)         │
│  - DAO (Data Access Objects)            │
│  - Base de données Room                 │
└─────────────────────────────────────────┘
```

### 2.2 Couche Model

**Composants:**

- **Entités:** `Contact.kt` - Représente un contact avec ses attributs (nom, téléphone, favoris, etc.)
- **DAO:** `ContactDao.kt` - Interface définissant les opérations de base de données
- **Database:** Configuration Room pour la persistance locale

**Exemple d'entité:**

```kotlin
@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val contactId: Int = 0,
    val firstName: String,
    val secondName: String,
    val phone: String,
    val createdAt: String,
    val lastModified: String,
    val isFavorite: Boolean = false
)
```

### 2.3 Couche ViewModel

**ViewModels implémentés:**

- `ContactsViewModel` - Gestion de la liste des contacts et recherche
- `ContactDetailsViewModel` - Gestion des détails et actions sur un contact
- `NewContactViewModel` - Gestion de la création de nouveaux contacts
- `EditContactViewModel` - Gestion de la modification des contacts
- `HomeViewModel` - Gestion de l'écran d'accueil

**Caractéristiques:**

- Utilisation de `@HiltViewModel` pour l'injection de dépendances
- Exposition des données via `StateFlow` et `Flow`
- Gestion des opérations asynchrones avec Coroutines
- Séparation de la logique métier de l'interface utilisateur

### 2.4 Couche View

**Technologies:**

- **Jetpack Compose** - Framework déclaratif pour l'interface utilisateur
- **Material Design 3** - Système de design moderne
- **Navigation Compose** - Gestion de la navigation entre écrans

**Écrans principaux:**

- `HomeScreen` - Écran d'accueil avec contacts favoris et récents
- `ContactScreen` - Liste complète des contacts avec recherche et filtres
- `ContactDetailScreen` - Détails d'un contact avec actions
- `NewContactScreen` - Formulaire de création de contact
- `EditContactScreen` - Formulaire de modification de contact

---

## 3. FONCTIONNALITÉS IMPLÉMENTÉES

### 3.1 Gestion des Contacts

#### Création de Contact

- Formulaire avec validation des champs
- Champs: Prénom (obligatoire), Nom (optionnel), Téléphone (obligatoire)
- Code pays fixe (+237)
- Navigation automatique vers les détails après création

#### Affichage des Contacts

- Liste alphabétique avec groupement par lettre
- Recherche en temps réel
- Filtrage (Tous / Favoris)
- Cartes de contact avec avatar, nom et téléphone

#### Modification de Contact

- Formulaire pré-rempli avec les données existantes
- Mise à jour de la date de modification
- Retour automatique après sauvegarde

#### Suppression de Contact

- Dialogue de confirmation avant suppression
- Message toast de confirmation
- Navigation automatique après suppression

### 3.2 Fonctionnalités Avancées

#### Système de Favoris

- Bouton toggle pour marquer/démarquer comme favori
- Icône visuelle (cœur rouge/gris)
- Filtrage par favoris
- Affichage des favoris sur l'écran d'accueil

#### Recherche

- Recherche en temps réel
- Recherche par prénom, nom ou numéro de téléphone
- Résultats instantanés avec debouncing

#### Appels Téléphoniques

- Bouton d'appel sur chaque carte de contact
- Ouverture du composeur avec numéro pré-rempli
- Utilisation de `ACTION_DIAL` (pas de permissions requises)

#### Partage de Contact

- Partage via Intent Android
- Format: "Contact: [Nom]\nPhone: [Numéro]"
- Compatible avec toutes les applications de partage

### 3.3 Interface Utilisateur

#### Navigation

- Barre de navigation inférieure avec 3 onglets:
  - Accueil
  - Contacts
  - Nouveau Contact
- Navigation fluide entre les écrans
- Gestion du back stack

#### Composants Réutilisables

- `ContactCard` - Carte de contact avec actions
- `FavoriteContactCard` - Carte pour les favoris
- `ConfirmationDialog` - Dialogue de confirmation personnalisé
- `Input` - Champ de saisie personnalisé
- `Group` - Groupe de boutons de filtre
- `Square` - Conteneur carré avec élévation

#### Design

- Material Design 3
- Couleurs cohérentes et modernes
- Animations et transitions fluides
- Interface responsive

---

## 4. TECHNOLOGIES ET BIBLIOTHÈQUES

### 4.1 Technologies Principales

| Technologie        | Version | Utilisation              |
| ------------------ | ------- | ------------------------ |
| Kotlin             | Latest  | Langage de programmation |
| Jetpack Compose    | Latest  | Framework UI déclaratif  |
| Room               | Latest  | Base de données locale   |
| Hilt               | Latest  | Injection de dépendances |
| Coroutines         | Latest  | Programmation asynchrone |
| Navigation Compose | Latest  | Navigation entre écrans  |

### 4.2 Bibliothèques Jetpack

- **Lifecycle** - Gestion du cycle de vie
- **ViewModel** - Gestion d'état
- **LiveData/Flow** - Observation de données
- **Material3** - Composants Material Design

### 4.3 Patterns et Pratiques

- **MVVM** - Architecture principale
- **Repository Pattern** - Abstraction de la source de données
- **Dependency Injection** - Hilt pour l'injection
- **Single Source of Truth** - Room comme source unique
- **Reactive Programming** - Flow et StateFlow

---

## 5. STRUCTURE DU PROJET

```
com.example.project_355/
├── model/
│   ├── Contact.kt              # Entité Contact
│   ├── RecentContact.kt        # Entité Contact Récent
│   ├── ContactDao.kt           # DAO pour les opérations DB
│   ├── Database.kt             # Configuration Room
│   └── OperationResponse.kt    # Modèle de réponse
│
├── viewModel/
│   ├── ContactsViewModel.kt         # VM liste contacts
│   ├── ContactDetailsViewModel.kt   # VM détails contact
│   ├── NewContactViewModel.kt       # VM nouveau contact
│   ├── EditContactViewModel.kt      # VM édition contact
│   └── HomeViewModel.kt             # VM écran accueil
│
├── view/
│   ├── screen/
│   │   ├── Home.kt              # Écran d'accueil
│   │   ├── Contact.kt           # Liste des contacts
│   │   ├── ContactDetail.kt     # Détails d'un contact
│   │   ├── NewContact.kt        # Création de contact
│   │   └── EditContact.kt       # Édition de contact
│   │
│   ├── components/
│   │   ├── ContactCard.kt           # Carte de contact
│   │   ├── FavoriteContactCard.kt   # Carte favori
│   │   ├── ConfirmationDialog.kt    # Dialogue confirmation
│   │   ├── Input.kt                 # Champ de saisie
│   │   ├── Group.kt                 # Groupe de boutons
│   │   ├── Square.kt                # Conteneur carré
│   │   ├── AppBarIcon.kt            # Icône navigation
│   │   └── BottomNavigationBar.kt   # Barre navigation
│   │
│   └── navigation/
│       ├── NavigationGraph.kt   # Graphe de navigation
│       └── BottomNavItem.kt     # Items de navigation
│
├── util/
│   ├── DateUtils.kt    # Utilitaires de formatage de dates
│   └── CallUtils.kt    # Utilitaires d'appel téléphonique
│
└── MainActivity.kt     # Activité principale
```

---

## 6. FONCTIONNEMENT DÉTAILLÉ

### 6.1 Flux de Données

1. **Lecture des données:**

   ```
   Room DB → DAO → ViewModel (Flow) → UI (Compose)
   ```

2. **Écriture des données:**
   ```
   UI (Action) → ViewModel → DAO → Room DB → UI (Mise à jour)
   ```

### 6.2 Gestion d'État

- Utilisation de `StateFlow` pour les états UI
- `collectAsState()` pour observer dans Compose
- `remember` et `mutableStateOf` pour l'état local
- `LaunchedEffect` pour les effets secondaires

### 6.3 Navigation

Routes définies:

- `home` - Écran d'accueil
- `contacts` - Liste des contacts
- `new_contact` - Nouveau contact
- `contact_detail/{contactId}` - Détails avec paramètre
- `edit_contact/{contactId}` - Édition avec paramètre

---

## 7. FONCTIONNALITÉS TECHNIQUES AVANCÉES

### 7.1 Recherche en Temps Réel

Implémentation avec `flatMapLatest`:

```kotlin
val searchResults: Flow<List<Contact>> = _searchQuery.flatMapLatest { query ->
    if (query.isBlank()) {
        contactDao.getAllContacts()
    } else {
        contactDao.searchContacts(query)
    }
}
```

### 7.2 Formatage de Dates

Utilitaire personnalisé retournant:

- Numéro du jour
- Numéro du mois
- Année
- Jour de la semaine (Today/Yesterday/Tomorrow ou nom du jour)
- Nom du mois

### 7.3 Injection de Dépendances

Configuration Hilt:

- `@HiltAndroidApp` sur l'application
- `@HiltViewModel` sur les ViewModels
- `@Inject` pour les dépendances
- Module pour la configuration Room

---

## 8. TESTS ET VALIDATION

### 8.1 Validation des Données

- Vérification des champs obligatoires
- Messages d'erreur appropriés
- Toast notifications pour le feedback utilisateur

### 8.2 Gestion des Erreurs

- Try-catch dans les ViewModels
- Messages d'erreur utilisateur-friendly
- États de chargement pour les opérations asynchrones

---

## 9. AMÉLIORATIONS FUTURES POSSIBLES

1. **Synchronisation Cloud** - Backup des contacts
2. **Import/Export** - Fichiers VCF
3. **Groupes de Contacts** - Organisation par groupes
4. **Photos de Profil** - Ajout d'avatars personnalisés
5. **Historique d'Appels** - Intégration avec l'historique système
6. **Thèmes** - Mode sombre/clair personnalisable
7. **Widgets** - Accès rapide aux favoris
8. **Recherche Vocale** - Recherche par commande vocale

---

## 10. CONCLUSION

Ce projet démontre une compréhension approfondie du développement Android moderne en utilisant:

- **Architecture MVVM** pour une séparation claire des responsabilités
- **Jetpack Compose** pour une UI moderne et déclarative
- **Room** pour une persistance de données robuste
- **Hilt** pour une injection de dépendances propre
- **Coroutines et Flow** pour la programmation asynchrone

L'application est complète, fonctionnelle et suit les meilleures pratiques recommandées par Google pour le développement Android. Le code est maintenable, testable et extensible pour de futures améliorations.

### Points Forts du Projet

✅ Architecture MVVM bien implémentée  
✅ Interface utilisateur moderne et intuitive  
✅ Fonctionnalités complètes de CRUD  
✅ Recherche et filtrage performants  
✅ Navigation fluide et cohérente  
✅ Code propre et bien organisé  
✅ Utilisation des dernières technologies Android

---

**Date de Réalisation:** Janvier 2026  
**Technologies:** Kotlin, Jetpack Compose, Room, Hilt, MVVM
