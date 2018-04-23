package com.osi.ems.mapper;

import java.util.List;

import com.osi.ems.domain.OsiSkillGroups;
import com.osi.ems.service.dto.OsiSkillGroupsDTO;

public interface OsiSkillGroupMapper {

	/**
	 * Method for mapping the skillgroups from osiskillgroups dto.
	 * @param osiSkillGroupsDTO
	 * @return Returns the {@link OsiSkillGroups}.
	 */
	public OsiSkillGroups osiSkillGroupsDtoToOsiSkillGroups(OsiSkillGroupsDTO osiSkillGroupsDTO);
	/**
	 * Methdo for mapping the {@link OsiSkillGroupsDTO} from {@link OsiSkillGroups}
	 * @param osiSkillGroups
	 * @return Returns the {@link OsiSkillGroupsDTO} object.
	 */
	public OsiSkillGroupsDTO osiSkillGroupsToOsiSkillGroupsDTO(OsiSkillGroups osiSkillGroups);
	/**
	 * Method for mapping the list of {@link OsiSkillGroups} from list of {@link OsiSkillGroupsDTO}
	 * @param osiSkillGroupsDtoList
	 * @return Returns the list of {@link OsiSkillGroups}.
	 */
	public List<OsiSkillGroups> osiSkillGroupsDtoListToOsiSkillGroupsList(List<OsiSkillGroupsDTO>  osiSkillGroupsDtoList);
	/**
	 * Method for Mapping the {@link OsiSkillGroupsDTO} list from {@link OsiSkillGroups}
	 * @param osiSkillGroupsList
	 * @return Returns the List of {@link OsiSkillGroupsDTO} object.
	 */
	public List<OsiSkillGroupsDTO> osiSkillGroupsListToOsiSkillGroupsDTO(List<OsiSkillGroups> osiSkillGroupsList);
	
}
