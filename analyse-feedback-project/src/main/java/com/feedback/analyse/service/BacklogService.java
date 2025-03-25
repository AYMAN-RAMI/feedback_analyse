package com.feedback.analyse.service;

import com.feedback.analyse.model.Backlog;
import java.util.List;

public interface BacklogService {
    List<Backlog> getAllBacklogs();
    Backlog getBacklogById(Long id);
    Backlog createBacklog(Backlog backlog);
    Backlog updateBacklog(Long id, Backlog backlog);
    Backlog updateStatut(Long id, String statut);
    void deleteBacklog(Long id);
}
