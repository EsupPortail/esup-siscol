# Integration Continue

L'intégration-continue a Dauphine se base sur les fonctionnalités 'gitlab-ci' et 'webhook [Job events]' de GitLab


## GitLab CI

Notre process sur le pipeline GitLab construit séquentiellement :

| tâche(s)                    | description                                                                                           |
|:----------------------------|:------------------------------------------------------------------------------------------------------|
| docbuild + appbuild         | build la documentation et les binaires applicatifs (jar/war/bar/etc.)                                 |
| rpmbuild_elX                | build les RPMs à partir des binaires des tâches précédantes                                           |
| make_artifact               | publie les archives construite via 'Job artifacts' de GitLab   (avec retention 2h + webhook[deploy])  |
| webhook_dauphine_res        | tâche qui attend le résultat final du webservice "gitlab-webhook" et affiche son log associé          |
| app_result                  | tâche qui nettoie les données caches fichiers du pipeline                                             |


Voir le fichier '[.gitlab-ci.yml](../.gitlab-ci.yml)' présent à la racine du projet


## WebHook dauphine

Le webhook 'gitlab-webhook' est un webservice qui contient 2 APIs

* une API qui reçoit les événements sur l'exécution des tâches du pipeline GitLab pour effectuer un déploiement
* une API qui renvoie pour un ID de pipeline données, le status de déploiement associé

Plus d'info en allant sur le projet du projet : [integration/gitlab-webhook](https://sources.dauphine.psl.eu/integration/gitlab-webhook)

### API sur event GitLab

L'API effectue les actions suivantes :
* sélectionne l'évennement sur la 'make_artifact' s'est terminé avec succès
* créé un fichier descripteur JSON avec les informations du pipeline associé : nom-du-projet, commiter, etc.
* lance le script 'run-actions.sh --pipeline pipelineID'

Le script 'run-actions.sh' lance les sous-scripts de déploiement spécifique à Dauphine :
* recup-artifact.sh : qui récupère les binaires génèré par le pipeline
* push-rpm.sh : qui déploie les RPMs dans le repository RPM privée de Dauphine
* deploy-tower.sh : qui lance le playbook de déploiement sur un environnement de recette
