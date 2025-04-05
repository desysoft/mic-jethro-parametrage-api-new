package org.jethro.parametrage.api.tools;

public class ParametersConfig {

    public ParametersConfig() {
    }

    public static final String status_enable ="enable";
    public static final String status_is_Waiting ="is_waiting";
    public static final String status_delete ="delete";
    public static final String status_complete ="complete";
    public static final String status_block ="blocked";
    public static final String status_disable ="disable";
    public static String PROCESS_SUCCES = "1";
    public static String PROCESS_FAILED = "0";
    public static String SUCCES_CREATE = "Création effectuée avec succès";
    public static String SUCCES_UPDATE = "Modification effectuée avec succès";
    public static String SUCCES_DELETE = "Suppression effectuée avec succès";
    public static String SUCCES_LINKED = "Liaison effectuée avec succès";
    public static String SUCCES_BLOCKED = "Blocage effectué avec succès";

    public static String FAILED_CREATE = "Echec de la création";
    public static String FAILED_UPDATE = "Echec de la modification";
    public static String FAILED_DELETE = "Echec de la suppression";

    public static String FAILED_LINKED = "Echec lors de la liaison";
    public static String FAILED_BLOCKED = "Echec lors dU blocage";

    public static String FAILED_GENERATE_ENVELOPPE = "Echec lors de la génération";
    public static String FAILED_CREATED_LIGNE_TABLEAU_UNIQUE_POSTE_AND_TABLEAU = "Cette ligne existe déja dans ce tableau";
    public static String CategorieEnveloppeNotFoundMessage = "Categorie introuvable dans la base de données";
    public static String VersionEnveloppeNotFoundMessage = "Version introuvable dans la base de données";
    public static String genericParameterNullMessage = "Paramètre null";
    public static String genericNotFoundMessage = "Element introuvable";
    public static String genericParameterNotFoundMessage = "Element de paramètre introuvable";
    public static String operationNotFoundMessage = "Operation introuvable";
    public static String codeAlreadyExist = "Ce code est déja existant";
    public static String NameCategorieAlreadyExist = "Ce nom de catégorie est déja existant";
    public static String Poste_already_USE_In_table = "Cette ligne est utilisé dans un tableau";

    public static String FORMULE_NOT_FOUND_MESSAGE = "Formule introuvable dans la base de données";
    public static String VERSION_NOT_FOUND_MESSAGE = "Version introuvable dans la base de données";
    public static String PARAMS_FORMULE_NOT_FOUND = "La formule contient des paramètres inconnus";
    public static String FORMULE_CODE_OR_NAME_ALREADY_EXIST = "Code ou nom de la formule déja existant dans la BD";

    public static String taskTableNameInApplication = "Tache";
    public static String projectTableNameInApplication = "Projet";
    public static String personTableNameInApplication = "Personne";
    public static String userTableNameInApplication = "Utitilisateur";
    public static String activityTableNameInApplication = "Activité";

    public static String GENERIC_MESSAGE_PROCESS_FAILED = "Echec lors de l'opération. Veuillez svp vérifier les paramètres";
    public static String CATEGORY_PER_VERSION_ALREADY_EXIST = "Cette version est déja liée à cette catégorie";
    public static String PROJECT_PERSON_NOT_EXIST = "Cette personne n'est pas affectée à ce projet";
    public static String FAILED_LINKED_TASK_TO_PERSON = "Echec dans l'attribution de la tache à la personne";
    public static String FAILED_TASK_ALREADY_LINKED_TO_PERSON = "Cette tâche est déja attribuée à cette personne";
    public static String FAILED_TASK_ALREADY_LINKED_TO_PROJECT = "Cette tâche est déja attribuée à un projet";
    public static String DATESTART_SMALL_THAN_DATEEND = "La date de début est supérieur à la date de fin";
    public static String DATESTART_SMALL_THAN_DATESTART_PARENT = "La date de début est supérieur à la date de Début de l'élément parent";
    public static String DATEEND_GREATHER_THAN_DATEEND_PARENT = "La date de fin est supérieur à la date de fin de l'élément parent";
    public static String NO_CATEGORIE_IN_THIS_VERSION = "Aucune catégorie n'est lié à cette version";
    public static String NO_ENTITY_IN_ALL_CATEGORIES = "Aucune entité n'est définie dans les différentes lignes";
    public static String ENVELOPPE_ALREADY_EXIST = "Cette enveloppe existe déja";
    public static String FORMULE_LINK_IN_VERSION = "Cette formule est actuellement utilisée dans une version";
    public static String UNABLE_FORMULE_LINK_TO_VERSION_FORMULETYPE = "Impossible d'ajouter encore une formule à cette version pour ce type";
    public static String EMPTY_LIST = "Liste vide";
    public static String CATEGORIES_EMPTY_LIST = "Liste de catégories vide";

    public static String ERROR_MESSAGE_GENERATE_ENVELOPPE_TEMPLATE = "Echec dans la création d'enveloppe de la version \"VERSION\" de ENTITE_TITLE \"ENTITE\" Pour la catégorie \"CATEGORIE\"";

    public static String id_assignateTaskOperation = "1";
    public static String id_updateOperation = "2";
    public static String id_choseProjectLeadOperation = "3";
    public static String id_assignateActivityToTaskOperation = "4";
    public static String id_assignateTaskToProject = "5";

    public static String message_parameter_login_null = "Veuillez renseigner le login SVP";
    public static String message_parameter_pwd_null = "Veuillez renseigner le mot de passe SVP";

    public static String path_log_file = "/var/log/task-manager/";

    public static String path_report_file_etat_cadrage_a_notifier = "classes/META-INF/resources/reports/etat_cadrage_a_notifier.jasper";
    // public static String path_report_file_etat_cadrage_a_notifier = "classes/META-INF/resources/reports/etat_cadrage_a_notifier.jrxml";
    // public static String path_report_output_file_etat_cadrage_a_notifier = "/home/desysoft/projects/java/DGBF/output/";
    public static String path_report_output_file_etat_cadrage_a_notifier_final = System.getProperty("java.io.tmpdir")+ "/META-INF/resources/reports/output/final/";
    public static String path_report_output_file_etat_cadrage_a_notifier = System.getProperty("java.io.tmpdir")+ "/META-INF/resources/reports/output/";
    public static String etat_de_cadrage_final_file_name = "etat_de_cadrage_final.pdf";


    public static String path_report_img_entete = "classes/META-INF/resources/images/entete.png";

    public static String PATHWAY_CODE_PREFIXE="F";
    public static String NEIGHBORHOOD_CODE_PREFIXE="Q";
    public static String ACADEMIC_LEVEL_CODE_PREFIXE="NA";
    public static String PROFESSION_CODE_PREFIXE="P";
    public static String MARITAL_STATUS_CODE_PREFIXE="SM";
    public static String SLICE_AGE_CODE_PREFIXE="TA";
    public static String TYPE_ACADEMIC_STATUS_CODE_PREFIXE="SA";
    public static String WORKER_TYPE_CODE_PREFIXE="TP";


    public static String path_img_logo = "classes/META-INF/resources/images/logo_siib.png";
    public static String path_report_output_qrcode_file = System.getProperty("java.io.tmpdir")+"/qrcode/output/";
    public static String qrCodeImageName = "qrcode.png";
    // Message des bean valisation en cas d'érreur.
    public static final String BVM_NOT_BLANK = "Le paramètre requis ne dois pas être vide ou null";
    public static final String BVM_NOT_NULL = "Le paramètre requis est null";
    public static final String BVM_LIBELLE_NOT_UNIQUE = "Ce libelle est déjà utilisé par un autre objet";


    public static String  setERROR_MESSAGE_GENERATE_ENVELOPPE_TEMPLATE(String entite_title, String version, String entite, String categorie){
        return ERROR_MESSAGE_GENERATE_ENVELOPPE_TEMPLATE.replace("ENTITE_TITLE",entite_title).replace("VERSION",version).replace("ENTITE", entite).replace("CATEGORIE", categorie);
    }

}

