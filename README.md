# Minesweeper4j

Minesweeper4j est une implémentation basique du jeu démineur en Java pur.

## Prérequis

- Java Development Kit (JDK) 8 ou plus récent
- Git (facultatif)

## Installation

1. Clonez le repository depuis GitHub (ou téléchargez l'archive ZIP) :

   ```bash
   git clone https://github.com/Jjiroos/minesweeper_4j.git
   ```

2. Accédez au répertoire du projet :

   ```bash
   cd minesweeper_4j
   ```

## Compilation et Exécution

### Via la Ligne de Commande

1. Compilez le code source :

   ```bash
   javac -d bin src/sc0vil/minesweeper/*.java
   ```

2. Exécutez le jeu :

   ```bash
   java -cp bin sc0vil.minesweeper.Main
   ```
Pour créer un exécutable de votre code source en Java, vous pouvez utiliser l'outil `jar` qui fait partie du JDK. Voici comment le faire et les étapes à ajouter dans le README :

### Création d'un exécutable JAR

1. Compilez le code source :

   ```bash
   javac -d bin src/sc0vil/minesweeper/*.java
   ```

2. Créez un fichier manifest (ex : `MANIFEST.MF`) avec le contenu suivant :

   ```plaintext
   Manifest-Version: 1.0
   Main-Class: sc0vil.minesweeper.Main
   ```

3. Créez l'archive JAR exécutable :

   ```bash
   jar cfm minesweeper4j.jar MANIFEST.MF -C bin .
   ```

4. Exécutez le JAR :

   ```bash
   java -jar minesweeper4j.jar
   ```

### Via un IDE (Eclipse, IntelliJ IDEA, etc.)

1. Importez le projet en tant que projet Java.
2. Configurez le projet pour utiliser la version appropriée du JDK.
3. Exécutez la classe `sc0vil.minesweeper.Main` pour lancer le jeu.

## Utilisation

Une fois le jeu lancé, vous pouvez jouer en suivant les règles classiques du démineur. Utilisez les clics de souris pour révéler les cases et marquer les mines.

## Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. Forkez le repository.
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`).
3. Commitez vos modifications (`git commit -m 'Add some AmazingFeature'`).
4. Poussez vers la branche (`git push origin feature/AmazingFeature`).
5. Ouvrez une Pull Request.

## Licence

Ce projet est sous licence Apache-2.0. Voir le fichier [LICENSE](LICENSE) pour plus de détails.