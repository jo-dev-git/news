# News App

Ce repository contient une application simple développée en Kotlin.
Elle consiste à afficher les actualités à la une selon la langue du smartphone.
L'utilisateur a la possibilité de cliquer sur une actualité et de voir le détail.

## 🛠️ Tech Stack

- **Kotlin**: code 100% Kotlin.
- **Jetpack Compose**: Pour la partie UI et la navigation
- **Coroutine**: Meilleure performance, meilleur gestion des tâches qui s'exécutent de manière asynchrone
- **Hilt**: Pour l'injection de dépendances
- **Coil**: Permet une meilleure optimisation avec gestion de cache pour afficher des images
- **Jetpack Compose Test APIs / Mockk / JUnit / Truth**: Pour les tests 
- **Architecture MVVM**: Meilleure architecture recommandée avec des ViewModels pour la gestion d'états
- **Retrofit / OkHttp / GSon**: Pour l'appel réseau

---

L'architecture MVVM est celle recommandée par Google et permet une réelle séparation des tâches de l'application (appel réseau, formatage des données, interactions utilisateur déclenchant des évènements qui mettent à jour l'UI)
Cette app repose sur une activité unique et la navigation entre les écrans se fait avec Navigation Compose.

Une autre possibilité aurait été de développer via des views, des fragments et non Compose mais mon choix s'est porté sur les dernières recommandations Google.

Pourquoi utiliser Hilt par exemple ? 
Cela permet la simplification du code, permet d'éviter les duplications de code dans de multiples fonctions ou classes. De plus, Hilt injecte de manière automatique les dépendances et gère leur cycle de vie.


