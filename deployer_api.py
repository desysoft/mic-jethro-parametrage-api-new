#!/usr/bin/env python3

import subprocess
import sys
from datetime import datetime
import os

# Déclaration des constantes
const_image_name = 'desysoft/'
default_folderpath = '.'

# Récupération de l'heure actuelle
current_time = datetime.now().strftime('%Y%m%d%H%M')

commit = input("Avez-vous fait un dernier commit y/n : ")

if commit.lower() == "n":
    print("======= Veuillez faire votre commit avant de continuer ============")
    exit()

# Récupérer le chemin du dossier courant
chemin_dossier_courant = os.getcwd()

# Extraire le nom du dossier à partir du chemin
nom_dossier_courant = os.path.basename(chemin_dossier_courant)

isSameNameThanFoler = input("Le nom du micro sera '"+nom_dossier_courant+"'. Voulez-vous garder ce nom y/n : ")


if isSameNameThanFoler.lower() == "n":
    name_micro = input('Entrez le nom du micro à déployer: ')
else:
   name_micro=nom_dossier_courant

# Construction du nom de l'image final
tag = subprocess.check_output(['git', 'rev-parse', 'HEAD']).strip().decode()
print(f"TAG = {tag}")

if not name_micro:
    print("======= EMPTY name_micro ============")
    exit()

if not tag:
    tag = current_time

folderpath = default_folderpath

print(f"name_micro ==== {name_micro}")
print(f"tag ==== {tag}")
print(f"folderpath ==== {folderpath}")

final_image_name = f"{const_image_name}{name_micro}:{tag}"

# Affichage du nom de l'image
print(f"Nom de l'image final : {final_image_name}")

# START CLEAN PACKAGE
print("===============================================================")
print(f"START CLEAN PACKAGE {final_image_name}")
print("===============================================================")

# Clean package avant docker build
subprocess.run(['./mvnw', 'clean', 'package', '-f', f"{folderpath}/pom.xml"])

print("===============================================================")
print("======================= END PACKAGING =========================")
print("===============================================================")

# START DOCKER BUILD
print("===============================================================")
print(f"START DOCKER BUILD {final_image_name}")
print("===============================================================")

subprocess.run(['docker', 'build', '-f', f"{folderpath}/src/main/docker/Dockerfile.jvm", '-t', final_image_name, folderpath])

print("===============================================================")
print("==================== END DOCKER BUILD =========================")
print("===============================================================")

reponse = input(f"Voulez-vous envoyer l'image {final_image_name} sur le registry? y/n : ")

if reponse.lower() == "y":
    subprocess.run(['docker', 'push', final_image_name])
    print("===============================================================")
    print("====================== END DOCKER PUSH =========================")
    print("===============================================================")
    exit()

print("======================= END PROCESS ============================")
