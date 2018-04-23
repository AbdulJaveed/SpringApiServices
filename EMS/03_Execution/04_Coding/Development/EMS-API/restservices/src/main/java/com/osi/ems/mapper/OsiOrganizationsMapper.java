package com.osi.ems.mapper;

import java.util.List;

import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.exception.BusinessException;

public interface OsiOrganizationsMapper {

	OsiOrganizationsDTO osiOrganizationsToOsiOrganizationsDTO(OsiOrganizations osiOrganizations);

	List<OsiOrganizationsDTO> osiOrganizationsListToOsiOrganizationsDTOList(List<OsiOrganizations> osiOrganizations);

	OsiOrganizations osiOrganizationsDTOToOsiOrganizations(OsiOrganizationsDTO osiOrganizationsDTO) throws BusinessException;

	List<OsiOrganizations> osiOrganizationsDTOListToOsiOrganizationsList(List<OsiOrganizationsDTO> osiOrganizationsDTO) throws BusinessException;
}
