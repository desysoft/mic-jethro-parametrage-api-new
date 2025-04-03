package org.jethro.parametrage.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.jethro.parametrage.api.tools.ParametersConfig;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.jethro.parametrage.api.tools.string.ToolString;


@MappedSuperclass
public abstract class BaseEntity extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    public String uuid;

    @Column(name = "str_code",unique = true)
    public String code;

    @Column(name = "date_crea")
    @JsonIgnore
    public LocalDateTime createdDate;

    @Column(name = "date_modif")
    @JsonIgnore
    public LocalDateTime updatedDate;

    @Column(name = "created_by")
    @JsonIgnore
    public String createdBy;

    @Column(name = "updated_by")
    @JsonIgnore
    public String updatedBy;

    @Column(name = "status")
    @JsonIgnore
    public String status;

    public String generateEntityId(){
        UUID oUuid = UUID.randomUUID();
        return oUuid.toString();
    }

    @PrePersist
    public void initializeEntity() {
        this.createdDate = LocalDateTime.now();
        this.uuid = generateEntityId();
        this.status = ParametersConfig.status_enable;
        if(this.code == null) {
            this.code = ToolString.getComplexId();
        }
    }


    @PreUpdate
    public void setEntityForUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

}
