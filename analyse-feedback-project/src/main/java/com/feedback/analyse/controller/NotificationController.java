package com.feedback.analyse.controller;

import com.feedback.analyse.dto.NotificationDTO;
import com.feedback.analyse.model.Notification;
import com.feedback.analyse.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @GetMapping("/po/{poId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByPo(@PathVariable Long poId) {
        List<Notification> notifications = notificationRepository.findByDestinataireId(poId);
        List<NotificationDTO> notificationDTOs = notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationDTOs);
    }

    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setContenu(notification.getContenu());
        dto.setDateEnvoi(notification.getDateEnvoi());
        dto.setDestinataireNom(notification.getDestinataire().getNom());
        return dto;
    }
}
