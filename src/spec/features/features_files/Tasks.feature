@LoginaddTasks
Feature: Se rendre sur le site locale et effectuer une série de tests afin de vérifierla connexion et la gestion des taches

	Background:
	    Given Je me rends sur le site "todolist.url"

  @login_et/ou_PwdVides
  Scenario: Se rendre sur le site locale et effectuer le test de connexion avec login et/ou mot de passe  vide
    When les champs login et/ou mot de passe sont vides
    Then Je vérifie que le bouton de connexion est non cliquable
   
  @loginParamsIncorrect
  Scenario: Se rendre sur le site locale et effectuer le test de connexion avec des identifiant non conforme
    When Je saisie le login "ok" et le mot de passe "oo"
    And Je clique sur le bouton de connexion
    Then un message rouge apparait pour alerter "Désolé, les identifiants sont incorrects."
    And les liens "Tâches" "Déconnexion" ne sont pas affichés
 
  @loginCasPassant
  Scenario: Se rendre sur le site locale et effectuer le test de connexion avec des identifiant valide
    When Je me connecte 
    Then la page change
    And les liens "Tâches" "Déconnexion" sont affichés
 
  @addTaskParamsVides
  Scenario: Se rendre sur le site locale et effectuer le test d ajout d une tache nul
    When Je me connecte 
    And Je clique sur le bouton Ajout de tache
    Then Je vérifie que le bouton ajout de tache est inactive

  @addTaskNomVide
  Scenario: Se rendre sur le site locale et effectuer le test d ajout d une tache sans nom
    When Je me connecte 
    And Je saisie la description de la tache "ok"
    And Je clique sur le bouton Ajout de tache
    Then Je vérifie que le bouton ajout de tache est inactive

  @addTaskDescriptionVide
  Scenario: Se rendre sur le site locale et effectuer le test d ajout d une tache sans description
   When Je me connecte 
    And Je saisie le nom de la tache "ko"
    And Je clique sur le bouton Ajout de tache
    Then Je vérifie que le bouton ajout de tache est inactive

  @addTask
  Scenario: Se rendre sur le site locale et effectuer le test d ajout d une tache sans description
    When Je me connecte 
    And J ajoute une tache
    Then Je verifie que la tache est ajoutee