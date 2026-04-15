package com.library.service;

import com.library.model.Patron;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PatronService {
    private static final Logger logger = Logger.getLogger(PatronService.class.getName());
    private Map<String, Patron> patrons;

    public PatronService() {
        this.patrons = new HashMap<>();
    }

    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
        logger.info("Added patron: " + patron.getName());
    }

    public void removePatron(String id) {
        patrons.remove(id);
        logger.info("Removed patron with ID: " + id);
    }

    public Patron getPatron(String id) {
        return patrons.get(id);
    }
}
