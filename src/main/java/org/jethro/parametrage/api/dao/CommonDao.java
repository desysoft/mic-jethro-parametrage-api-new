package org.jethro.parametrage.api.dao;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.transaction.Transactional;
import org.jethro.parametrage.api.entities.BaseEntity;
import org.jethro.parametrage.api.tools.ParametersConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonDao<T extends BaseEntity> extends AbstractDao implements PanacheRepositoryBase<T,String> {

    public static final Logger LOG = Logger.getLogger("CommonDao");

    /**
     * Ajoute la clause d'institution si le header X-Institution-Id est présent sur la requête
     * courante. Institution absente = clause inchangée, aucun filtrage : c'est le comportement
     * historique, préservé pour ne pas casser le contrat REST existant.
     */
    private String withInstitutionClause(String whereClause, Map<String, Object> params) {
        String institutionId = getCurrentInstitutionId();
        if (institutionId == null) {
            return whereClause;
        }
        params.put("institutionId", institutionId);
        return whereClause + " AND pkeyInstitutionId = :institutionId";
    }

    public List<T> getList(){
        LOG.info("CommonDao +++ getList");
        Map<String, Object> params = new HashMap<>();
        params.put("status", ParametersConfig.status_enable);
        return list(withInstitutionClause("status = :status", params), params);
    }

    public List<T> getList(int pageIndex, int pageSize){
        Map<String, Object> params = new HashMap<>();
        params.put("status", ParametersConfig.status_enable);
        return find(withInstitutionClause("status = :status", params), params)
                .page(pageIndex,pageSize)
                .list();
    }

    public List<T> getList(String searchValue, int pageIndex, int pageSize){
        Map<String, Object> params = new HashMap<>();
        searchValue = checkAndGetForSearchValue(searchValue);
        params.put("searchValue", searchValue);
        String whereClause = withInstitutionClause(
                "(LOWER(code) LIKE LOWER(:searchValue) OR LOWER(libelle) LIKE LOWER(:searchValue) OR LOWER(description) LIKE LOWER(:searchValue))",
                params);
        PanacheQuery<T> panacheQuery =  find(whereClause, params);
        if(pageSize==0){
            return panacheQuery.list();
        }else {
            return panacheQuery.page(pageIndex,pageSize).list();
        }
    }

    public T findByIdCustom(String uuid) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("uuid", uuid);
            params.put("status", ParametersConfig.status_enable);
            return this.find(withInstitutionClause("uuid = :uuid AND status = :status", params), params).firstResult();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    /**
     * Version REST-facing : filtrée par institution comme le reste des lectures.
     */
    public T findByCode(String code){
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            params.put("status", ParametersConfig.status_enable);
            return this.find(withInstitutionClause("code = :code AND status = :status", params), params).firstResult();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    /**
     * Contrôle d'unicité de code volontairement non filtré par institution : str_code porte une
     * contrainte unique globale en base (BaseEntity), donc le contrôle applicatif doit rester
     * global lui aussi, sous peine de laisser passer un doublon détecté trop tard par une
     * exception SQL brute au lieu de CodeExistException.
     */
    private T findByCodeAnyInstitution(String code){
        try {
            return this.find("code = ?1 AND status = ?2", code, ParametersConfig.status_enable).firstResult();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public Boolean isExistCode(String code){
        return findByCodeAnyInstitution(code)!=null;
    }

    @Transactional
    @Override
    public void persist(T entity) {
        if (entity.pkeyInstitutionId == null) {
            String institutionId = getCurrentInstitutionId();
            if (institutionId != null) {
                entity.pkeyInstitutionId = institutionId;
            }
        }
        PanacheRepositoryBase.super.persist(entity);
    }
}
