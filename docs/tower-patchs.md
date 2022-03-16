# Tower

2 patchs ont été réalisé sur Tower

* Patch pour récupérer la branche dans les 'facts'
* Patch de surcharge du path relatif au fichier 'requirements.yml'


## SCM_BRANCH

Ajouter dans les Facts accessible aux playbooks : "awx_job_scm_branch" et "tower_job_scm_branch"

### Editer le script python

Editer le fichier "/var/lib/awx/venv/awx/lib/python3.6/site-packages/awx/main/models/jobs.py" de votre installation tower

En ajoutant les lignes entre les commentaires dans le block 'awx_meta_vars'

~~~shell
    def awx_meta_vars(self):
        r = super(Job, self).awx_meta_vars()
        if self.project:
            for name in ('awx', 'tower'):
                r['{}_project_revision'.format(name)] = self.project.scm_revision
        if self.job_template:
            for name in ('awx', 'tower'):
                r['{}_job_template_id'.format(name)] = self.job_template.pk
                r['{}_job_template_name'.format(name)] = self.job_template.name
                #
                # ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                # PATCH VBR => avoir la version dans les Facts Ansible
                #
                # variables
                #       awx_job_scm_branch + tower_job_scm_branch
                #
                r['{}_job_scm_branch'.format(name)] = self.job_template.scm_branch
                # ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                #
        return r
~~~

### Redemarrer tower

~~~shell
[root@server ~]# systemctl stop ansible-tower
[root@server ~]# systemctl start ansible-tower
~~~


## Project path for requirements.yml

Ajouter dans un nouveau chemin pour le fichier 'requirements.yml'

Par default dans tower, le path du fichier 'requirements.yml' défini dans le projet est obligatoirement '/roles/requirements.yml'

### Editer le playbook project_update

Ajouter le nouveau bloque 'CUSTOM DAUPHINE REQUIREMENTS' après le bloque 'fetch galaxy roles from requirements.yml' dans le fichier playbook de mise à jour du projet

* /var/lib/awx/venv/awx/lib64/python3.6/site-packages/awx/playbooks/project_update.yml

~~~yml
    ...

    # ==============================================
    # DEFAULT TOWER
    # ==============================================
    - block:
        - name: detect requirements.yml
          stat:
            path: '{{project_path|quote}}/roles/requirements.yml'
          register: doesRequirementsExist

        - name: fetch galaxy roles from requirements.yml
          command: >
            ansible-galaxy role install -r roles/requirements.yml
            --roles-path {{projects_root}}/.__awx_cache/{{local_path}}/stage/requirements_roles
            {{ ' -' + 'v' * ansible_verbosity if ansible_verbosity else '' }}
          args:
            chdir: "{{project_path|quote}}"
          register: galaxy_result
          when: doesRequirementsExist.stat.exists
          changed_when: "'was installed successfully' in galaxy_result.stdout"
          environment:
            ANSIBLE_FORCE_COLOR: false
            GIT_SSH_COMMAND: "ssh -o StrictHostKeyChecking=no"

      when: roles_enabled|bool
      tags:
        - install_roles

    # ==============================================
    # CUSTOM DAUPHINE REQUIREMENTS
    # ==============================================
    - block:
        - name: detect requirements.yml (custom)
          stat:
            path: '{{project_path|quote}}/ansible/roles/requirements.yml'
          register: doesRequirementsExistCustom

        - name: fetch galaxy roles from requirements.yml (custom)
          command: >
            ansible-galaxy role install -r ansible/roles/requirements.yml
            --roles-path {{projects_root}}/.__awx_cache/{{local_path}}/stage/requirements_roles
            {{ ' -' + 'v' * ansible_verbosity if ansible_verbosity else '' }}
          args:
            chdir: "{{project_path|quote}}"
          register: galaxy_result
          when: doesRequirementsExistCustom.stat.exists
          changed_when: "'was installed successfully' in galaxy_result.stdout"
          environment:
            ANSIBLE_FORCE_COLOR: false
            GIT_SSH_COMMAND: "ssh -o StrictHostKeyChecking=no"

      when: roles_enabled|bool
      tags:
        - install_roles

    ...
~~~

### Redemarrer tower

~~~shell
[root@server ~]# systemctl stop ansible-tower
[root@server ~]# systemctl start ansible-tower
~~~
