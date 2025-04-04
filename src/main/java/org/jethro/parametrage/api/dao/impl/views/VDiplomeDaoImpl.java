package org.jethro.parametrage.api.dao.impl.views;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jethro.parametrage.api.dao.views.CommonDaoForView;
import org.jethro.parametrage.api.dao.views.VDiplomeDao;
import org.jethro.parametrage.api.dao.views.VQuartierDao;
import org.jethro.parametrage.api.entities.views.VDiplome;
import org.jethro.parametrage.api.entities.views.VQuartier;

@ApplicationScoped
public class VDiplomeDaoImpl extends CommonDaoForView<VDiplome> implements VDiplomeDao {
}

