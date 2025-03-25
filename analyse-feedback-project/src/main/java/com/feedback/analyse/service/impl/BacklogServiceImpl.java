package com.feedback.analyse.service.impl;

import com.feedback.analyse.exception.ResourceNotFoundException;
import com.feedback.analyse.model.Backlog;
import com.feedback.analyse.repository.BacklogRepository;
import com.feedback.analyse.service.BacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BacklogServiceImpl implements BacklogService {
    private final BacklogRepository backlogRepository;

    @Override
    public List<Backlog> getAllBacklogs() {
        return backlogRepository.findAll();
    }

    @Override
    public Backlog getBacklogById(Long id) {
        return backlogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Backlog non trouvé avec l'id : " + id));
    }

    @Override
    public Backlog createBacklog(Backlog backlog) {
        return backlogRepository.save(backlog);
    }

    @Override
    public Backlog updateBacklog(Long id, Backlog backlogDetails) {
        Backlog backlog = getBacklogById(id);
        
        backlog.setStatut(backlogDetails.getStatut());
        
        return backlogRepository.save(backlog);
    }

    @Override
    public Backlog updateStatut(Long id, String statut) {
        Backlog backlog = getBacklogById(id);
        backlog.setStatut(statut);
        return backlogRepository.save(backlog);
    }

    @Override
    public void deleteBacklog(Long id) {
        Backlog backlog = getBacklogById(id);
        backlogRepository.delete(backlog);
    }
}
