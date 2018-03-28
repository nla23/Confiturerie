-----------------------------------------
Equipe 1 - Projet Confiturerie - GLO-3004
-----------------------------------------
Auteurs: 
-----------------------------------------
St�phane Lalancette		- 908 279 903    
Francis Charest    		- 905 179 619
F�lix Veillette-Potvin 	- 111 074 805
Nicolas Lauzon			- 111 101 145
-----------------------------------------
Date de remise: 28 juillet 2018
---------------------------------------------------------------------------------------------------------------------------------------------------


Le manuel suivant explique le fonctionnement du programme Confiturerie qui a �t� mis au point par l'�quipe num�ro 1 dans le cadre du cours GLO-3004.
Le programme est �crit en Java et est con�u pour �tre ex�cut� avec le logiciel Eclipse Oxygen.2 Version (4.7.2)

---------
IMPORTANT
--------- 
Pour ex�cuter le programme vous devez importer les 5 fichiers classes suivantes dans Eclipse et ensuite en faire la compilation. 
Nous avons aussi besoin que la classe java.util.Scanner soit import�e pour que l'ex�cution fonctionne.

--------------------------------------------------------------------
Les classes suivantes sont n�cessaires pour l'ex�cution du programme:
--------------------------------------------------------------------
Confiturerie.java: Cette classe contient le main() du programme; c'est �galement dans cette classe que sont cr��s les threads pour chacun des bocaux. 
Etiquette.java: Cette classe sert au processus d'�tiquetage des bocaux.
Reservoir.java: Cette classe sert � configurer les param�tres des bocaux et du r�servoir comme le volume et la capacit�.
Bocal.java: Cette classe contient les param�tres sp�cifiques � chacun des bocaux comme son type (A/B) et son num�ro. On se sert �galement de cette classe pour sp�cifier si le bocal est d�j� �tiquet� et remplit.
Valve.java: Cette classe sert � configurer le type de la valve (A/B) et son statut (ouverte/ferm�e).   

-------------------------------------------------------------------------------------------------------------------------------------------------------------
L'application que nous avons d�velopp�e sert � simuler le fonctionnement d'une confiturerie. 
Nous avons un tapis roulant avec des bocaux de types diff�rents qui circulent dessus et pour lesquels certaines actions sont ex�cut�es dans le but de mod�liser
le fonctionnement r�el en usine. Les actions suivantes sont ex�cut�es dans l'ordre suivant par le simulateur et une trace est affich�e � l'�cran pour permettre
de suivre le fil de l'ex�cution:
-------------------------------------------------------------------------------------------------------------------------------------------------------------
1) Cr�ation d'un bocal: Simule la cr�ation d'un bocal qui va ensuite circuler sur la chaine de montage de la confiturerie.
2) D�but du remplissage d'un bocal: Indique que le pot de confiture est pr�t pour le remplissage et que la valve peut �tre ouverte. 
3) Ouverture d'une valve: Simule l'ouverture d'une valve qui permet ensuite le remplissage du bocal. 
4) Fin du remplissage d'un bocal: Indique qu'on vient de terminer le remplissage du pot de confiture et que la valve peut �tre ferme.
4) Fermeture d'une valve: Simule la fermeture d'une valve une fois que le bocal est rempli de confiture. 
5) Affichage du volume restant dans le r�servoir: Permets de connaitre le volume de confiture restant dans le r�servoir pour un type de bocal.
6) �tiquettage d'un bocal: Simule l'�tiquetage du bocal une fois que celui-ci est rempli de confiture.
7) Bocal termin�: Indique que le bocal est compl�t�.
8) Bocal en attente: Indique qu'un type de bocal est en attente de son tour pour pouvoir acc�der au tapis roulant.

-------------------------------------------
Instructions pour l'ex�cution du programme:
-------------------------------------------
- Vous devez saisir � l'�cran le nombre de bocaux de type A et de type B que vous voulez simuler.
- Le type de bocal (A ou B) est fix� et ne peut �tre chang�.
- Les valeurs permises pour le nombre de bocaux accept�s sont de 1 � 100.
- Vous pouvez saisir des valeurs identiques pour chaque type de bocaux (ex: A=2, B=2) ou diff�rentes (ex: A=3, B=5).
- Vous devez saisir obligatoirement � l'�cran le type de bocal qui doit �tre trait� en priorit�; les valeurs permises sont A ou B. 
- Une fois les valeurs pr�c�dentes saisies, le programme proc�de � la cr�ation des bocaux sous forme de threads (un thread par bocal).
- Les bocaux sont cr��s par alternance, si leur nombre de chaque type est identique: (ex: A, B, A, B, ... A, B). 
- Si jamais nous avons une diff�rence de bocaux entre les 2 types (ex: A=5, B=2), les bocaux du type en surplus seront cr��s en dernier:  (ex: A, B, A, B, A ... A).
- Le simulateur fonctionne avec 2 valves, soit une valve par type de bocal; ce nombre est fix� dans le code et ne peut donc pas �tre modifi�.
- La quantit� totale de chaque r�servoir (A et B) est fix�e � 5 litres et ne peut �tre modifi�e.
- Le remplissage de chaque pot de confiture diminue la volum�trie totale du r�servoir de 1 litre.
- Quand le r�servoir est vide (5 pots remplis), on proc�de au remplissage de celui-ci par un ajout de 5 nouveaux litres de confitures.

  