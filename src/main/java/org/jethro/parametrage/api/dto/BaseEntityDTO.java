package org.jethro.parametrage.api.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntityDTO {

    protected String uuid;
    protected String code;
/*    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected String createdBy;
    protected String updatedBy;
    protected String status;*/
    protected String pkey_institution_id;
}
