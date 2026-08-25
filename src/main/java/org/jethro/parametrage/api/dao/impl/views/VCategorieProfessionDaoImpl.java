package org.jethro.parametrage.api.dao.impl.views;

import jakarta.enterprise.context.ApplicationScoped;
import org.jethro.parametrage.api.dao.views.CommonDaoForView;
import org.jethro.parametrage.api.dao.views.VCategorieProfessionDao;
import org.jethro.parametrage.api.entities.views.VCategorieProfession;

@ApplicationScoped
public class VCategorieProfessionDaoImpl extends CommonDaoForView<VCategorieProfession> implements VCategorieProfessionDao {
}
