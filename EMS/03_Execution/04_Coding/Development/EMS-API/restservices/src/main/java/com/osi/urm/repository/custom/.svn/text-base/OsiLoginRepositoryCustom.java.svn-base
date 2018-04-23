package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.domain.MenuDetails;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.DataAccessException;

public interface OsiLoginRepositoryCustom {
	public OsiUser validateLogin(String userName, String password, String currentDate) throws DataAccessException;
	public List<MenuDetails> getAllMenusAndSubMenus(Integer userId) throws DataAccessException;
	public void logout(Integer userId, String token) throws DataAccessException;
	public OsiUser forgotPAsswordUserDetails(String userName, String currentDate)throws DataAccessException;
	public List<String> getAdminEmailIds(String currentDate)throws DataAccessException;
}
