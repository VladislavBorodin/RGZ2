package com.example.vich_syst_rgr_back.core.utilLayerGlobal.entitiesUtil;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс-родитель для Entities, при наследовании от него даёт настроенные
 * автоинкрементирующийся Integer id в поле бд id int
 * даты createdDate, updatedDate и deletedDate в формате LocalDateTime
 * и в полях timestamp бд created_date, updated_date и deleted_date соответствующе
 * даты создания и изменения автозаполняющиеся через AuditingEntityListener
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate; // дата создания СУЩНОСТИ
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate; // дата редактирования СУЩНОСТИ
    @Column(name = "removed_date")
    private LocalDateTime removedDate; // дата удаления СУЩНОСТИ
}
