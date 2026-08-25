package org.jethro.parametrage.api.dao;

import org.jethro.parametrage.api.dto.ProfessionCreateDto;
import org.jethro.parametrage.api.dto.ProfessionUpdateDto;
import org.jethro.parametrage.api.entities.Profession;

public interface ProfessionDao extends CRUDCommon<Profession>  {
    Profession saveByProfessionCreateDto(ProfessionCreateDto professionCreateDto);

    Profession updateByProfessionUpdateDto(ProfessionUpdateDto professionUpdateDto);
}
