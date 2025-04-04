package org.jethro.parametrage.api.dao.impl.views;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jethro.parametrage.api.dao.views.CommonDaoForView;
import org.jethro.parametrage.api.dao.views.VSituationMatrimonialeDao;
import org.jethro.parametrage.api.dao.views.VQuartierDao;
import org.jethro.parametrage.api.entities.views.VSituationMatrimoniale;
import org.jethro.parametrage.api.entities.views.VQuartier;

@ApplicationScoped
public class VSituationMatrimonialeDaoImpl extends CommonDaoForView<VSituationMatrimoniale> implements VSituationMatrimonialeDao {
}

