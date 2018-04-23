package com.osi.urm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.urm.repository.OsiUserLoginsRepository;
import com.osi.urm.service.OsiUserLoginsService;
import com.osi.urm.service.dto.OsiUserLoginsDTO;

/**
 * Service Implementation for managing OsiUserLogins.
 */
@Service
@Transactional
public class OsiUserLoginsServiceImpl implements OsiUserLoginsService{

    private final Logger log = LoggerFactory.getLogger(OsiUserLoginsServiceImpl.class);
    
    @Autowired
    private OsiUserLoginsRepository osiUserLoginsRepository;

    /**
     * Save a osiUserLogins.
     *
     * @param osiUserLoginsDTO the entity to save
     * @return the persisted entity
     */
    public OsiUserLoginsDTO save(OsiUserLoginsDTO osiUserLoginsDTO) {
        log.debug("Request to save OsiUserLogins : {}", osiUserLoginsDTO);
        /*OsiUserLogins osiUserLogins = osiUserLoginsMapper.osiUserLoginsDTOToOsiUserLogins(osiUserLoginsDTO);
        osiUserLogins = osiUserLoginsRepository.save(osiUserLogins);
        OsiUserLoginsDTO result = osiUserLoginsMapper.osiUserLoginsToOsiUserLoginsDTO(osiUserLogins);
        return result;*/
        return null;
    }

    /**
     *  Get all the osiUserLogins.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<OsiUserLoginsDTO> findAll() {
        /*log.debug("Request to get all OsiUserLogins");
        List<OsiUserLoginsDTO> result = osiUserLoginsRepository.findAll().stream()
            .map(osiUserLoginsMapper::osiUserLoginsToOsiUserLoginsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;*/
    	return null;
    }

    /**
     *  Get one osiUserLogins by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public OsiUserLoginsDTO findOne(Long id) {
        log.debug("Request to get OsiUserLogins : {}", id);
       /* OsiUserLogins osiUserLogins = osiUserLoginsRepository.findOne(id);
        OsiUserLoginsDTO osiUserLoginsDTO = osiUserLoginsMapper.osiUserLoginsToOsiUserLoginsDTO(osiUserLogins);
        return osiUserLoginsDTO;*/
        return null;
    }
}
