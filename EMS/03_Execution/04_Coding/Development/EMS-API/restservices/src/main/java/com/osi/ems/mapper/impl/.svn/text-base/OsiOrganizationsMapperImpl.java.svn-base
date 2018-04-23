package com.osi.ems.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.ems.domain.OsiCurrencies;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.mapper.OsiOrganizationsMapper;
import com.osi.ems.service.dto.OsiCurrenciesDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;

@Component
public class OsiOrganizationsMapperImpl implements OsiOrganizationsMapper {

	@Override
	public OsiOrganizationsDTO osiOrganizationsToOsiOrganizationsDTO(OsiOrganizations osiOrganizations) {
		if ( osiOrganizations == null ) {
            return null;
        }
		OsiOrganizationsDTO osiOrganizationsDTO = new OsiOrganizationsDTO();
		osiOrganizationsDTO.setOrgId(osiOrganizations.getOrgId());
		osiOrganizationsDTO.setOrgName(osiOrganizations.getOrgName());
		osiOrganizationsDTO.setOrgShortName(osiOrganizations.getOrgShortName());
		osiOrganizationsDTO.setParentOrgId(osiOrganizations.getParentOrgId());
		osiOrganizationsDTO.setPhoneNumber(osiOrganizations.getPhoneNumber());
		osiOrganizationsDTO.setCountryCode(osiOrganizations.getCountryCode());
		osiOrganizationsDTO.setEmailId(osiOrganizations.getEmailId());
		osiOrganizationsDTO.setWebsite(osiOrganizations.getWebsite());
		osiOrganizationsDTO.setFaxNumber(osiOrganizations.getFaxNumber());
		osiOrganizationsDTO.setFaxCode(osiOrganizations.getFaxCode());
		osiOrganizationsDTO.setActive(osiOrganizations.getActive());
		if(osiOrganizations.getBaseCurrencyId() != null)
		{
		OsiCurrenciesDTO bcurrencies = new OsiCurrenciesDTO();
		bcurrencies.setCurrencyId(osiOrganizations.getBaseCurrencyId().getCurrencyId());
		bcurrencies.setCurrencyCode(osiOrganizations.getBaseCurrencyId().getCurrencyCode());
		bcurrencies.setCurrencyName(osiOrganizations.getBaseCurrencyId().getCurrencyName());
		osiOrganizationsDTO.setBaseCurrencyId(bcurrencies);
		}if(osiOrganizations.getReportingCurrencyId() != null)
		{
		OsiCurrenciesDTO rcurrencies = new OsiCurrenciesDTO();
		rcurrencies.setCurrencyId(osiOrganizations.getReportingCurrencyId().getCurrencyId());
		rcurrencies.setCurrencyCode(osiOrganizations.getReportingCurrencyId().getCurrencyCode());
		rcurrencies.setCurrencyName(osiOrganizations.getReportingCurrencyId().getCurrencyName());
		osiOrganizationsDTO.setReportingCurrencyId(rcurrencies);
		}
		osiOrganizationsDTO.setContactPersonId(osiOrganizations.getContactPersonId());
		osiOrganizationsDTO.setCountryCode(osiOrganizations.getCountryCode());
		osiOrganizationsDTO.setOverheadPct(osiOrganizations.getOverheadPct());
		osiOrganizationsDTO.setTotalHrsPerYear(osiOrganizations.getTotalHrsPerYear());
		osiOrganizationsDTO.setCostCalc(osiOrganizations.getCostCalc());
		osiOrganizationsDTO.setInterEmpOverheadPct(osiOrganizations.getInterEmpOverheadPct());
		osiOrganizationsDTO.setCreatedBy(osiOrganizations.getCreatedBy());
		osiOrganizationsDTO.setCreationDate(osiOrganizations.getCreationDate());
		osiOrganizationsDTO.setLastUpdatedBy(osiOrganizations.getLastUpdatedBy());
		osiOrganizationsDTO.setLastUpdateDate(osiOrganizations.getLastUpdateDate());
		osiOrganizationsDTO.setStartDayOfWeek(osiOrganizations.getStartDayOfWeek());
		
		return osiOrganizationsDTO;
	}

	@Override
	public List<OsiOrganizationsDTO> osiOrganizationsListToOsiOrganizationsDTOList(
			List<OsiOrganizations> osiOrganizations) {
		if ( osiOrganizations == null ) {
            return null;
        }

        List<OsiOrganizationsDTO> list = new ArrayList<OsiOrganizationsDTO>();
        for ( OsiOrganizations osiOrganization : osiOrganizations ) {
            list.add( osiOrganizationsToOsiOrganizationsDTO( osiOrganization ) );
        }

        return list;
	}

	@Override
	public OsiOrganizations osiOrganizationsDTOToOsiOrganizations(OsiOrganizationsDTO osiOrganizationsDTO) {
		if ( osiOrganizationsDTO == null ) {
            return null;
        }
		OsiOrganizations osiOrganizations = new OsiOrganizations();
		osiOrganizations.setOrgId(osiOrganizationsDTO.getOrgId());
		osiOrganizations.setOrgName(osiOrganizationsDTO.getOrgName());
		osiOrganizations.setOrgShortName(osiOrganizationsDTO.getOrgShortName());
		osiOrganizations.setParentOrgId(osiOrganizationsDTO.getParentOrgId());
		osiOrganizations.setPhoneNumber(osiOrganizationsDTO.getPhoneNumber());
		osiOrganizations.setCountryCode(osiOrganizationsDTO.getCountryCode());
		osiOrganizations.setWebsite(osiOrganizationsDTO.getWebsite());
		osiOrganizations.setActive(osiOrganizationsDTO.getActive());
		osiOrganizations.setEmailId(osiOrganizationsDTO.getEmailId());
		osiOrganizations.setFaxNumber(osiOrganizationsDTO.getFaxNumber());
		osiOrganizations.setFaxCode(osiOrganizationsDTO.getFaxCode());
		if(osiOrganizationsDTO.getBaseCurrencyId() != null)
		{
		OsiCurrencies bcurrencies = new OsiCurrencies();
		bcurrencies.setCurrencyId(osiOrganizationsDTO.getBaseCurrencyId().getCurrencyId());
		bcurrencies.setCurrencyCode(osiOrganizationsDTO.getBaseCurrencyId().getCurrencyCode());
		bcurrencies.setCurrencyName(osiOrganizationsDTO.getBaseCurrencyId().getCurrencyName());
		osiOrganizations.setBaseCurrencyId(bcurrencies);
		}if(osiOrganizationsDTO.getReportingCurrencyId() != null)
		{
		OsiCurrencies rcurrencies = new OsiCurrencies();
		rcurrencies.setCurrencyId(osiOrganizationsDTO.getReportingCurrencyId().getCurrencyId());
		rcurrencies.setCurrencyCode(osiOrganizationsDTO.getReportingCurrencyId().getCurrencyCode());
		rcurrencies.setCurrencyName(osiOrganizationsDTO.getReportingCurrencyId().getCurrencyName());
		osiOrganizations.setReportingCurrencyId(rcurrencies);
		}
		osiOrganizations.setContactPersonId(osiOrganizationsDTO.getContactPersonId());
		osiOrganizations.setCountryCode(osiOrganizationsDTO.getCountryCode());
		osiOrganizations.setOverheadPct(osiOrganizationsDTO.getOverheadPct());
		osiOrganizations.setTotalHrsPerYear(osiOrganizationsDTO.getTotalHrsPerYear());
		osiOrganizations.setCostCalc(osiOrganizationsDTO.getCostCalc());
		osiOrganizations.setInterEmpOverheadPct(osiOrganizationsDTO.getInterEmpOverheadPct());
		
		osiOrganizations.setCreatedBy(osiOrganizationsDTO.getCreatedBy());
		osiOrganizations.setCreationDate(osiOrganizationsDTO.getCreationDate());
		osiOrganizations.setLastUpdatedBy(osiOrganizationsDTO.getLastUpdatedBy());
		osiOrganizations.setLastUpdateDate(osiOrganizationsDTO.getLastUpdateDate());
		osiOrganizations.setStartDayOfWeek(osiOrganizationsDTO.getStartDayOfWeek());
		
		return osiOrganizations;
	}

	@Override
	public List<OsiOrganizations> osiOrganizationsDTOListToOsiOrganizationsList(
			List<OsiOrganizationsDTO> osiOrganizationsDTO) {
		if ( osiOrganizationsDTO == null ) {
            return null;
        }

        List<OsiOrganizations> list = new ArrayList<OsiOrganizations>();
        for ( OsiOrganizationsDTO osiOrganizationsDTO_ : osiOrganizationsDTO ) {
            list.add( osiOrganizationsDTOToOsiOrganizations( osiOrganizationsDTO_ ) );
        }

        return list;
	}

}
