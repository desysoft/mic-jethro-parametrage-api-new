package org.jethro.parametrage.api.dao.impl;

import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.dao.CommonDao;
import org.jethro.parametrage.api.dao.FiliereDao;
import org.jethro.parametrage.api.entities.Filiere;
import org.jethro.parametrage.api.entities.Profession;
import org.jethro.parametrage.api.exceptions.CodeExistException;
import org.jethro.parametrage.api.tools.ParametersConfig;
import org.jethro.parametrage.api.tools.string.ToolString;

@ApplicationScoped
public class FiliereDaoImpl extends CommonDao<Filiere> implements
    FiliereDao {


  public Filiere save(Filiere filiere) throws CodeExistException {
    try {
      LOG.info("save");
      if(filiere.code == null) {
        filiere.code = ToolString.getComplexId();
      }
      if (this.isExistCode(filiere.code)) {
        LOG.info("isExistCode");
        this.setMessage(ParametersConfig.PROCESS_FAILED);
        this.setDetailMessage(ParametersConfig.codeAlreadyExist);
        throw new CodeExistException(this.getDetailMessage());
      }
      this.persist(filiere);
      return filiere;
    } catch (Exception e) {
      this.setMessage(ParametersConfig.PROCESS_FAILED);
      throw new RuntimeException(e);
    }
  }

  public Filiere update(String uuid, Filiere filiereForUpdate) {
    try {
      Filiere filiereFind = this.findByIdCustom(uuid);
      if (filiereFind == null) {
        this.setMessage(ParametersConfig.PROCESS_FAILED);
        this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
        return null;
      }

      if (filiereForUpdate.code != null) {
        filiereFind.code = filiereForUpdate.code;
      }
      if (filiereForUpdate.libelle != null) {
        filiereFind.libelle = filiereForUpdate.libelle;
      }
      if (filiereForUpdate.description != null) {
        filiereFind.description = filiereForUpdate.description;
      }
      this.persist(filiereFind);
      return filiereFind;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  public boolean delete(String uuid) {
    try {
      Filiere filiere = this.findByIdCustom(uuid);
      if (filiere == null) {
        this.setMessage(ParametersConfig.PROCESS_FAILED);
        this.setDetailMessage(ParametersConfig.genericNotFoundMessage);
        return false;
      }
      filiere.delete();
      return true;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
