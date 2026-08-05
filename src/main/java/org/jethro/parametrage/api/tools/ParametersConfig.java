package org.jethro.parametrage.api.tools;

public class ParametersConfig {

    public ParametersConfig() {
    }

    public static final String status_enable ="enable";
    public static final String status_delete ="delete";
    public static String PROCESS_SUCCES = "1";
    public static String PROCESS_FAILED = "0";
    public static String SUCCES_CREATE = "Création effectuée avec succès";
    public static String SUCCES_UPDATE = "Modification effectuée avec succès";
    public static String SUCCES_DELETE = "Suppression effectuée avec succès";

    public static String FAILED_CREATE = "Echec de la création";
    public static String FAILED_UPDATE = "Echec de la modification";
    public static String FAILED_DELETE = "Echec de la suppression";

    public static String genericParameterNullMessage = "Paramètre null";
    public static String genericNotFoundMessage = "Element introuvable";
    public static String genericParameterNotFoundMessage = "Element de paramètre introuvable";
    public static String operationNotFoundMessage = "Operation introuvable";
    public static String codeAlreadyExist = "Ce code est déja existant";

    public static String GENERIC_MESSAGE_PROCESS_FAILED = "Echec lors de l'opération. Veuillez svp vérifier les paramètres";

    public static String path_log_file = "/var/log/task-manager/";

    public static String PATHWAY_CODE_PREFIXE="F";
    public static String NEIGHBORHOOD_CODE_PREFIXE="Q";
    public static String ACADEMIC_LEVEL_CODE_PREFIXE="NA";
    public static String PROFESSION_CODE_PREFIXE="P";
    public static String MARITAL_STATUS_CODE_PREFIXE="SM";
    public static String SLICE_AGE_CODE_PREFIXE="TA";
    public static String TYPE_ACADEMIC_STATUS_CODE_PREFIXE="SA";
    public static String WORKER_TYPE_CODE_PREFIXE="TP";

    // Message des bean valisation en cas d'érreur.
    public static final String BVM_NOT_BLANK = "Le paramètre requis ne dois pas être vide ou null";
    public static final String BVM_NOT_NULL = "Le paramètre requis est null";
    public static final String BVM_LIBELLE_NOT_UNIQUE = "Ce libelle est déjà utilisé par un autre objet";

}
