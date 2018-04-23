package com.osi.ems.repository.custom;

import java.util.List;

import com.osi.ems.domain.OsiCategories;
import com.osi.urm.exception.DataAccessException;

public interface OsiCategoriesRepositoryCustom {

	List<OsiCategories> getCategoryFlexFields(String categoryName, int orgId) throws DataAccessException;
    List<OsiCategories> searchEntries(String categoryName, String tableName, Integer orgId) throws DataAccessException;

	void removeCategoryFieldsByCategoryId(Integer id) throws DataAccessException;

	List<String> findColumnsByTableName(String tableName) throws DataAccessException;
	
	OsiCategories findCategoryNameByFunctionIdAndOrg(String functionUrl, int orgId) throws DataAccessException;
}
