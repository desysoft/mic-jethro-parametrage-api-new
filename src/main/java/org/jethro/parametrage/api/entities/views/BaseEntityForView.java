package org.jethro.parametrage.api.entities.views;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseEntityForView extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    public String uuid;

    @Column(name = "code")
    public String code;

    @Column(name = "libelle")
    public String libelle;



}
