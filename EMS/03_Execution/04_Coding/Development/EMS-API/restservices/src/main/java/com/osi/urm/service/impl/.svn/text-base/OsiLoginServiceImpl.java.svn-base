package com.osi.urm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.MenuDetails;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.domain.OsiUserLogins;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.OsiLoginRepository;
import com.osi.urm.repository.OsiUserLoginsRepository;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.security.util.TokenGenerator;
import com.osi.urm.service.OsiLoginService;
import com.osi.urm.service.dto.OsiUserDTO;

/**
 * Service Implementation for managing OsiLogin.
 */
@Service
@Transactional
public class OsiLoginServiceImpl implements OsiLoginService{

    private final Logger log = LoggerFactory.getLogger(OsiLoginServiceImpl.class);
    
    @Autowired
    private OsiLoginRepository osiLoginRepository;
    
    @Autowired
    private OsiUserLoginsRepository osiUserLoginsRepository;
    
    @Autowired
    private AppConfig appConfig;
    
    @Autowired
    private TokenGenerator tokenGenerator;
    
    @Autowired
    private AuthTokenStore authTokenStore;
    
    @Autowired
    private OsiEmployeesRepositoryCustom employeesRepositoryCustom;
    
	@Override
	public OsiUserDTO validateLogin(OsiUserDTO osiUserDTO) throws BusinessException {
		OsiUser osiUser = null;
		String token = null;
		try {
			osiUser = osiLoginRepository.validateLogin(osiUserDTO.getUserName(), osiUserDTO.getPassword(), getCurrentDate());
			osiUserDTO.setManager(employeesRepositoryCustom.isManager(osiUser.getUserId()));
		osiUserDTO.setId(osiUser.getUserId());
		osiUserDTO.setFullName(osiUser.getFullName());
		osiUserDTO.setFirstName(osiUser.getFirstName());
		osiUserDTO.setLastName(osiUser.getLastName());
		osiUserDTO.setEmpNumber(osiUser.getEmpNumber());
		osiUserDTO.setBusinessGroupId(Integer.parseInt(appConfig.getBusinessGroupId()));
		osiUserDTO.setOrgId(osiUser.getOrgId());
		osiUserDTO.setOrgCode(osiUser.getOrgCode());
		osiUserDTO.setPhotoPath(osiUser.getPhotoPath());
		/*osiUserDTO.setEmailId(osiUser.getEmailId());
		osiUserDTO.setHasDefaultPwd(osiUser.getHasDefaultPwd());
		osiUserDTO.setPassword(null);*/
		if(osiUser!=null && osiUser.getUserId()!=null){
			token = tokenGenerator.generateToken(osiUserDTO.getUserName());
			osiUserDTO.setToken(token);
			osiUserDTO.setMenuTree(getAllMenusAndSubMenusService(osiUser.getUserId()));
			int tokenExpTime = Integer.parseInt(appConfig.getTokenExpTimeInSecs());
			authTokenStore.storeToken(new AuthorizationToken(token, osiUserDTO, tokenExpTime));
			OsiUserLogins osiUserLogins = new OsiUserLogins();
			osiUserLogins.setBusinessGroupId(Integer.parseInt(appConfig.getBusinessGroupId()));
			osiUserLogins.setUserId(osiUser.getUserId());
			osiUserLogins.setToken(osiUserDTO.getToken());
			osiUserLogins.setStartTime(new Date());
			osiUserLogins.setTokenExpTime(tokenExpTime);
			osiUserLoginsRepository.save(osiUserLogins);
		}
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getSystemMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiUserDTO;
	}
	

    private String getCurrentDate(){
        SimpleDateFormat format = new SimpleDateFormat(appConfig.getDbDateformat());
    	return format.format(new Date());
    }
    public void logout(Integer userId, String token) throws BusinessException{
    	try {
			osiLoginRepository.logout(userId, token);
		}  catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
    }
    
    public String getAllMenusAndSubMenusService(Integer userId) throws BusinessException{
		String response = null;
		List<MenuDetails> menusAndSubMenusList = null;
		try {
			menusAndSubMenusList = osiLoginRepository.getAllMenusAndSubMenus(userId);
		
		List<MenuDetails> rootList = new ArrayList<MenuDetails>();
		int length = menusAndSubMenusList.size();
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				MenuDetails menuDetails = menusAndSubMenusList.get(i);
				if(menuDetails.getMenuId() != null) {
					for (int j = 0; j < length; j++) {
						if(menusAndSubMenusList.get(j).getSubMenuId()!=null){
							if(menusAndSubMenusList.get(j).getSubMenuId().equals(menuDetails.getMenuId())) {
								menusAndSubMenusList.get(j).getChildren().add(menuDetails);
							}
						}
					}
				}
			}
			
			for (int k = 0; k < length; k++) {
				MenuDetails menuDetails = menusAndSubMenusList.get(k);
				if(menuDetails.getMenuId() == null) {
					menuDetails.setMenuId(0);
					rootList.add(menuDetails);
				}
			}	
			Gson gson = new Gson();			
			response = gson.toJson(rootList).toString();
		}
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return response;
	}
}
