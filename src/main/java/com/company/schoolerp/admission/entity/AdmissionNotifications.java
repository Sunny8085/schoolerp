package com.company.schoolerp.admission.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.company.schoolerp.common.Auditable;
import com.company.schoolerp.common.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "admission_notifications")
public class AdmissionNotifications extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_no", nullable = false, length = 50)
    private String notificationNo;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "application_start_date", nullable = false)
    private LocalDate applicationStartDate;

    @Column(name = "application_end_date", nullable = false)
    private LocalDate applicationEndDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;

    @Column(name = "session", nullable = false, length = 100)
    private String session;
    
    @Builder.Default
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AdmissionCategory> categories = new ArrayList<>();
}

