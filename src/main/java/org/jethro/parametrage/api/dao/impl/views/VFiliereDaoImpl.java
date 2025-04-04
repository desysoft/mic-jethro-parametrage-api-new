package org.jethro.parametrage.api.dao.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.dao.views.CommonDaoForView;
import org.jethro.parametrage.api.dao.views.VFiliereDao;
import org.jethro.parametrage.api.dao.views.VProfessionDao;
import org.jethro.parametrage.api.entities.views.VFiliere;
import org.jethro.parametrage.api.entities.views.VProfession;

@ApplicationScoped
public class VFiliereDaoImpl extends CommonDaoForView<VFiliere> implements VFiliereDao {
}
