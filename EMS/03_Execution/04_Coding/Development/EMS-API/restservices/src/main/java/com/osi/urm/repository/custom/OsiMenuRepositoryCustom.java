package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.domain.OsiMenus;
import com.osi.urm.exception.DataAccessException;

public interface OsiMenuRepositoryCustom {
	public Integer updateMenu(OsiMenus osiMenus) throws DataAccessException;
	public Integer findMenusByNameId(Integer id, String menuName, Integer businessGroupId) throws DataAccessException;
	public Integer findMenusByName(String menuName, Integer businessGroupId) throws DataAccessException;
	public Integer deleteMenu(Integer id, Integer businessGroupId, Integer userId) throws DataAccessException;
	public Integer deleteMenus(List<Integer> id, Integer businessGroupId, Integer userId )throws DataAccessException;

}
