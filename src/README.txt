-----------------------------------------
Equipe 1 - Projet Confiturerie - GLO-3004
-----------------------------------------
Auteurs: 
-----------------------------------------
Stéphane Lalancette		- 908 279 903    
Francis Charest    		- 905 179 619
Félix Veillette-Potvin 	- 111 074 805
Nicolas Lauzon			- 111 101 145
-----------------------------------------
Date de remise: 28 juillet 2018
---------------------------------------------------------------------------------------------------------------------------------------------------


Le manuel suivant explique le fonctionnement du programme Confiturerie qui a été mis au point par l'équipe numéro 1 dans le cadre du cours GLO-3004.
Le programme est écrit en Java et est conçu pour être exécuté avec le logiciel Eclipse Oxygen.2 Version (4.7.2)

---------
IMPORTANT
--------- 
Pour exécuter le programme vous devez importer les 5 fichiers classes suivantes dans Eclipse et ensuite en faire la compilation. 
Nous avons aussi besoin que la classe java.util.Scanner soit importée pour que l'exécution fonctionne.

--------------------------------------------------------------------
Les classes suivantes sont nécessaires pour l'exécution du programme:
--------------------------------------------------------------------
Confiturerie.java: Cette classe contient le main() du programme; c'est également dans cette classe que sont créés les threads pour chacun des bocaux. 
Etiquette.java: Cette classe sert au processus d'étiquetage des bocaux.
Reservoir.java: Cette classe sert à configurer les paramètres des bocaux et du réservoir comme le volume et la capacité.
Bocal.java: Cette classe contient les paramètres spécifiques à chacun des bocaux comme son type (A/B) et son numéro. On se sert également de cette classe pour spécifier si le bocal est déjà étiqueté et remplit.
Valve.java: Cette classe sert à configurer le type de la valve (A/B) et son statut (ouverte/fermée).   

-------------------------------------------------------------------------------------------------------------------------------------------------------------
L'application que nous avons développée sert à simuler le fonctionnement d'une confiturerie. 
Nous avons un tapis roulant avec des bocaux de types différents qui circulent dessus et pour lesquels certaines actions sont exécutées dans le but de modéliser
le fonctionnement réel en usine. Les actions suivantes sont exécutées dans l'ordre suivant par le simulateur et une trace est affichée à l'écran pour permettre
de suivre le fil de l'exécution:
-------------------------------------------------------------------------------------------------------------------------------------------------------------
1) Création d'un bocal: Simule la création d'un bocal qui va ensuite circuler sur la chaine de montage de la confiturerie.
2) Début du remplissage d'un bocal: Indique que le pot de confiture est prêt pour le remplissage et que la valve peut être ouverte. 
3) Ouverture d'une valve: Simule l'ouverture d'une valve qui permet ensuite le remplissage du bocal. 
4) Fin du remplissage d'un bocal: Indique qu'on vient de terminer le remplissage du pot de confiture et que la valve peut être ferme.
4) Fermeture d'une valve: Simule la fermeture d'une valve une fois que le bocal est rempli de confiture. 
5) Affichage du volume restant dans le réservoir: Permets de connaitre le volume de confiture restant dans le réservoir pour un type de bocal.
6) Étiquettage d'un bocal: Simule l'étiquetage du bocal une fois que celui-ci est rempli de confiture.
7) Bocal terminé: Indique que le bocal est complété.
8) Bocal en attente: Indique qu'un type de bocal est en attente de son tour pour pouvoir accéder au tapis roulant.

-------------------------------------------
Instructions pour l'exécution du programme:
-------------------------------------------
- Vous devez saisir à l'écran le nombre de bocaux de type A et de type B que vous voulez simuler.
- Le type de bocal (A ou B) est fixé et ne peut être changé.
- Les valeurs permises pour le nombre de bocaux acceptés sont de 1 à 100.
- Vous pouvez saisir des valeurs identiques pour chaque type de bocaux (ex: A=2, B=2) ou différentes (ex: A=3, B=5).
- Vous devez saisir obligatoirement à l'écran le type de bocal qui doit être traité en priorité; les valeurs permises sont A ou B. 
- Une fois les valeurs précédentes saisies, le programme procède à la création des bocaux sous forme de threads (un thread par bocal).
- Les bocaux sont créés par alternance, si leur nombre de chaque type est identique: (ex: A, B, A, B, ... A, B). 
- Si jamais nous avons une différence de bocaux entre les 2 types (ex: A=5, B=2), les bocaux du type en surplus seront créés en dernier:  (ex: A, B, A, B, A ... A).
- Le simulateur fonctionne avec 2 valves, soit une valve par type de bocal; ce nombre est fixé dans le code et ne peut donc pas être modifié.
- La quantité totale de chaque réservoir (A et B) est fixée à 5 litres et ne peut être modifiée.
- Le remplissage de chaque pot de confiture diminue la volumétrie totale du réservoir de 1 litre.
- Quand le réservoir est vide (5 pots remplis), on procède au remplissage de celui-ci par un ajout de 5 nouveaux litres de confitures.

  