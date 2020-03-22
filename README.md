Ce projet contient l'API trainer-api permettant la gestion des trainers du jeu. 

L'API présente 4 routes :

GET     /trainers 
GET     /trainers/{name}
POST    /trainers/  
DELETE  /trainers/{name}

Les tests de ces routes sont disponibles dans la collection postman trainer-api.postman_collection.json.

La configuration par défaut lance l'API sur le port 9002.
Le code applicatif n'est pas déployé, cependant la base de données est hébergés chez clever-cloud.
