## Exemple intégration de la base de données H2 avec Spring Boot
Dans ce projet, nous verrons un exemple comment intégrer la base de données H2 aux projets Spring Boot.
Nous allons créer une API d'opérations CRUD `Person` , qui est un simple service REST dans lequel nous allons interagir avec H2 à l'aide de Spring Boot.<br/>

### Qu'est-ce que la base de données H2 ?
---
H2 est une base de données embarquée open source, basée sur Java. C'est très rapide et très léger.
En règle générale, il est utilisé comme base de données en mémoire, ce qui signifie qu'il stocke les données 
en mémoire et ne conservera pas les données sur le disque. Bien que si nous devons conserver les données, 
en appuyant simplement sur un commutateur, vous pouvez également conserver les données.<br/>

*La base de données H2 n'est pas recommandée pour les environnements de production et est plutôt 
utilisée pour les preuves de concept, les tests, les prototypes et les applications similaires.*

### Prérequis
---
Pour ce projet, vous auriez besoin des spécifications suivantes :<br/>
- IDE ou éditeur de texte
- JDK 1.8 +
- Maven 3+ ou Gradle 4+ *(Nous ferons confiance à Maven pour cet article)*
- Tout IDE prenant en charge Java et Spring Boot (Spring Tool Suite (STS), IntelliJ, VSC, NetBeans, etc.)
- Postman, curl ou n'importe quel client HTTP pour tester l'API REST

### Configuration du projet Spring Boot
---
Assurez-vous d'ajouter des dépendances Web, H2 et JPA (Java Persistence API).
* **Spring Web** - Pour inclure Spring MVC et Tomcat intégré dans votre projet.
* **Spring Data JPA** - API de persistance Java et Hibernate.
* **H2 Database** - H2 est une base de données intégrée, open source et en mémoire.
* **Spring Boot DevTools** - Outils de développement très utiles. Spring Boot DevTools récupère les modifications et redémarre l'application.
* **Mapstruct** - Un mappeur Java Bean qui mappent automatiquement entre deux Java Beans (DO to DTO et DTO to DO).

### Comprendre les configurations par défaut de H2
---
Puisque nous avons ajouté H2 en tant que dépendance, Spring Boot sait que dans ce projet, 
nous nous connecterons à la base de données H2, il est donc important de configurer 
les propriétés liées à H2 telles que la base de données URL, username, password, etc :
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=false
```

**Remplacement des configurations par défaut de H2**
Par exemple, par défaut, la console H2 est désactivée, activons-la donc 
en ajoutant la propriété ci-dessous dans le fichier `application.properties` :
```
spring.h2.console.enabled=true
```

### Architecture technique
---
Cette image présente l'architecture globale du projet.<br/><br/>
![Archi_globale](https://user-images.githubusercontent.com/75081354/135051349-ece59247-fe8b-4376-8334-5a7239e597a8.jpg)

Le module au cœur du Spring Framework (Spring Core) repose fondamentalement sur un seul principe de conception objet : 
l’inversion de contrôle.<br/>
	
L'**Inversion de contrôle (Spring IOC)** permet au développeur de s'occuper uniquement du code metier (Exigences fonctionnelles) 
et c'est le Framework qui s'occupe du code technique (Exigences Techniques).

### Exigences technique
---
* Un client web (Browser) communique avec le serveur web (Apache) en utilisant le protocole HTTP
* Les données sont stockées dans SGBD PostgreSQL
* L'application est composé de 4 couches :
	- La couche de données (model) pour les classes entités (DO et DTO).
	- La couche DAO (Repository, Spring Data, JPA, Hibenate et JDBC) pour interagir avec la base de données PostgreSQL.
	- La couche métier pour le traitement de la logique métier.
	- La couche infrastructure (controller) basée sur Rest API pour traiter la logique d'entreprise.

### Exécuter le projet
---
Après avoir exécuté l'application, ouvrons la console H2 en tapant `http://localhost:8080/h2-console` dans la barre d'adresse du navigateur :<br/>
![H2-1](https://user-images.githubusercontent.com/75081354/135051502-216d490f-fbc3-45d9-8ede-bafb83fb2ab0.PNG)

Vous pouvez voir une valeur différente dans l'URL JDBC, modifiez donc l'URL de la base de données `jdbc:h2:mem:testdb` 
dans l'écran de connexion car il s'agit de l'URL par défaut configurée par Spring Boot.
Cliquez sur se connecter pour vous connecter et voir les différentes options.

### Test de l'API Person
---
Pour tester le service REST, nous utiliserons Postman , qui peut être facilement intégré au navigateur 
Chrome à l'aide de son extension de navigateur ou utilisé comme outil autonome.<br/>

L'API Person effectuera des opérations CRUD à l'aide de différents points de terminaison. 
Vous trouverez ci-dessous les détails des points de terminaison :<br/>
* `POST /api/persons/add` - insérera un nouvel objet personne dans la base de données.
Pour valider les données insérées dans la base de données H2 :<br/>
* Ouvrez la console H2
* Validez le tableau PERSON. Il nous montrera les données que nous avons insérées dans la requête POST.<br/><br/>
![H2-2](https://user-images.githubusercontent.com/75081354/135051540-27e15c5e-96d1-490f-8738-702b8e183785.PNG)
* `GET /api/persons/listperson` - affichera tous les objets personne disponibles dans la base de données H2.
* `GET /api/persons/get/{id}` - affichera l'objet person avec l'identifiant donné.
* `GET /api/persons/findAllPersonsBySearch?searchText=#&page=#size=#` - consulter les objets personne disponibles dans la base de données H2 avec mot clé et pagination.
* `GET /api/persons/listpagined` - affichera tous les objets personne disponibles dans la base de données H2 avec pagination.
* `PUT /api/persons/update` - mettre à jour un objet personne existant dans la base de données H2.
* `DELETE /api/persons/delete/{id}` - supprimera l'objet personne avec l'identifiant donné de la base de données.
* `DELETE /api/persons/deleteall` - supprimera tous les objets personne disponibles dans la base de données H2.

### Conclusion
---
Dans ce petit guide, nous avons examiné comment intégrer la base de données H2 
en mémoire avec Spring Boot et construit une API REST simple autour de celle-ci pour en présenter l'utilisation.<br/>

H2 est une base de données embarquée légère écrite en Java, généralement utilisée pour le prototypage.
