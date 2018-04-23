package com.osi.urm.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.urm.service.OsiUserLoginsService;
import com.osi.urm.service.dto.OsiUserLoginsDTO;

/**
 * REST controller for managing OsiUserLogins.
 */
@RestController
@RequestMapping("/api")
public class OsiUserLoginsResource {

    private final Logger log = LoggerFactory.getLogger(OsiUserLoginsResource.class);
        
    @Autowired
    private OsiUserLoginsService osiUserLoginsService;

    /**
     * POST  /osi-user-logins : Create a new osiUserLogins.
     *
     * @param osiUserLoginsDTO the osiUserLoginsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new osiUserLoginsDTO, or with status 400 (Bad Request) if the osiUserLogins has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/osi-user-logins")
    public ResponseEntity<OsiUserLoginsDTO> createOsiUserLogins(@Valid @RequestBody OsiUserLoginsDTO osiUserLoginsDTO) throws URISyntaxException {
        log.debug("REST request to save OsiUserLogins : {}", osiUserLoginsDTO);
        if (osiUserLoginsDTO.getId() != null) {
            return ResponseEntity.badRequest()
            		.body(null);
        }
        OsiUserLoginsDTO result = osiUserLoginsService.save(osiUserLoginsDTO);
        return ResponseEntity.created(new URI("/api/osi-user-logins/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /osi-user-logins : Updates an existing osiUserLogins.
     *
     * @param osiUserLoginsDTO the osiUserLoginsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated osiUserLoginsDTO,
     * or with status 400 (Bad Request) if the osiUserLoginsDTO is not valid,
     * or with status 500 (Internal Server Error) if the osiUserLoginsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/osi-user-logins")
    public ResponseEntity<OsiUserLoginsDTO> updateOsiUserLogins(@Valid @RequestBody OsiUserLoginsDTO osiUserLoginsDTO) throws URISyntaxException {
        log.debug("REST request to update OsiUserLogins : {}", osiUserLoginsDTO);
        if (osiUserLoginsDTO.getId() == null) {
            return createOsiUserLogins(osiUserLoginsDTO);
        }
        OsiUserLoginsDTO result = osiUserLoginsService.save(osiUserLoginsDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /osi-user-logins : get all the osiUserLogins.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of osiUserLogins in body
     */
    @GetMapping("/osi-user-logins")
    public List<OsiUserLoginsDTO> getAllOsiUserLogins() {
        log.debug("REST request to get all OsiUserLogins");
        return osiUserLoginsService.findAll();
    }

    /**
     * GET  /osi-user-logins/:id : get the "id" osiUserLogins.
     *
     * @param id the id of the osiUserLoginsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the osiUserLoginsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/osi-user-logins/{id}")
    public ResponseEntity<OsiUserLoginsDTO> getOsiUserLogins(@PathVariable Long id) {
        log.debug("REST request to get OsiUserLogins : {}", id);
        OsiUserLoginsDTO osiUserLoginsDTO = osiUserLoginsService.findOne(id);
        /*return Optional.ofNullable(osiUserLoginsDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/
        return null;
    }

}
