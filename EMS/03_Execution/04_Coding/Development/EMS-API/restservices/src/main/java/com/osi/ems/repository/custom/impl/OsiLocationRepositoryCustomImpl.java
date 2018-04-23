package com.osi.ems.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.domain.OsiLocations;
import com.osi.ems.domain.OsiRegions;
import com.osi.ems.domain.OsiTimezones;
import com.osi.ems.repository.custom.OsiLocationRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

@Repository
@Transactional
public class OsiLocationRepositoryCustomImpl implements OsiLocationRepositoryCustom {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	/* Get the list of locations and their addresses by the organization id */
	public List<OsiLocations> getLocationsByOrgId(Integer orgId) throws DataAccessException {
		LOGGER.info("getLocationsByOrgId : Begin");
		List<OsiLocations> osiLocationsList = null;
		OsiLocations osiLocations = null;
		List<Objects[]> result = new ArrayList<Objects[]>();
		try {
			Query query = entityManager
					.createQuery("select loc from OsiLocations loc where loc.osiOrganizations.orgId=:orgId")
					.setParameter("orgId", orgId);
			result = (List<Objects[]>) query.getResultList();
			osiLocationsList = new ArrayList<OsiLocations>();
			if (result != null || !result.isEmpty()) {
				for (Object objects : result) {
					osiLocations = new OsiLocations();
					osiLocations = (OsiLocations) objects;
					osiLocationsList.add(osiLocations);
				}
			}
		} catch (NoResultException ne) {
			LOGGER.error("Exception : ", ne.getMessage());
			throw new DataAccessException("ERR_1002", "No Records found in locations with the organizationId");
		} catch (Exception e) {
			LOGGER.error("Exception : ", e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getLocationsByOrgId : End");
		return osiLocationsList;
	}

	@Override
	public OsiLocations getLocationsByLocId(Integer locId) throws DataAccessException {
		LOGGER.info("getLocationsByOrgId : Begin");
		OsiLocations osiLocations = new OsiLocations();
		try {
			Query query = entityManager.createQuery("select loc from OsiLocations loc where loc.locationId=:locId")
					.setParameter("locId", locId);
			Object result = new Object();
			result = query.getSingleResult();
			if (result != null) {
				osiLocations = (OsiLocations) result;
			}
		} catch (NoResultException ne) {
			LOGGER.error("Exception : ", ne.getMessage());
			throw new DataAccessException("ERR_1002", "No Records found in locations with the location Id");
		} catch (Exception e) {
			LOGGER.error("Exception : ", e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getLocationsByLocId : End");
		return osiLocations;
	}

	@Override
	public List<OsiRegions> getRegionsInfo() throws DataAccessException {
		List<OsiRegions> osiRegionsList = null;
		OsiRegions osiRegions = null;
		LOGGER.info("getLocationsByOrgId : Begin");
		try {
			String queryString = "select regionId,regionName,regionShortName from OsiRegions";
			List<Object[]> queryList = (List<Object[]>) this.entityManager.createQuery(queryString).getResultList();
			osiRegionsList = new ArrayList<OsiRegions>(0);
			for (Object[] objects : queryList) {
				osiRegions = new OsiRegions();
				if (objects[0] != null)
					osiRegions.setRegionId(Integer.parseInt(objects[0].toString()));
				if (objects[1] != null)
					osiRegions.setRegionName(objects[1].toString());
				if (objects[2] != null)
					osiRegions.setRegionShortName(objects[2].toString());
				osiRegionsList.add(osiRegions);
			}
		} catch (NoResultException ne) {
			LOGGER.error("Exception : ", ne.getMessage());
			throw new DataAccessException("ERR_1002", "No Records found in regions");
		} catch (Exception e) {
			LOGGER.error("Exception : ", e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getRegionsInfo : End");
		return osiRegionsList;
	}

	@Override
	public List<OsiTimezones> getTimezonesInfo() throws DataAccessException {
		List<OsiTimezones> osiTimezonesList = null;
		OsiTimezones osiTimezones = null;
		LOGGER.info("getLocationsByOrgId : Begin");
		try {
			String queryString = "select tzId,tzName,tzShortName from OsiTimezones";
			List<Object[]> queryList = (List<Object[]>) this.entityManager.createQuery(queryString).getResultList();
			osiTimezonesList = new ArrayList<OsiTimezones>(0);
			for (Object[] objects : queryList) {
				osiTimezones = new OsiTimezones();
				if (objects[0] != null)
					osiTimezones.setTzId(Integer.parseInt(objects[0].toString()));
				if (objects[1] != null)
					osiTimezones.setTzName(objects[1].toString());
				if (objects[2] != null)
					osiTimezones.setTzShortName(objects[2].toString());
				osiTimezonesList.add(osiTimezones);
			}
		} catch (NoResultException ne) {
			LOGGER.error("Exception : ", ne.getMessage());
			throw new DataAccessException("ERR_1002", "No Records found in time zones");
		} catch (Exception e) {
			LOGGER.error("Exception : ", e.getMessage());
			throw new DataAccessException("ERR_1000", "Exception occured while executing");
		}
		LOGGER.info("getTimezonesInfo : End");
		return osiTimezonesList;
	}

}
