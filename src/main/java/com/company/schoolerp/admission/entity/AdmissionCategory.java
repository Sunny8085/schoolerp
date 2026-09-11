package com.company.schoolerp.admission.entity;

import java.math.BigDecimal;

import com.company.schoolerp.common.Auditable;
import com.company.schoolerp.common.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "admission_categories")
public class AdmissionCategory extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id", nullable = false)
    private AdmissionNotifications notification;

    @Column(name = "class_name", nullable = false, length = 50)
    private String className;

    @Column(name = "stream", length = 50)
    private String stream;

    @Column(name = "total_seats")
    private Integer totalSeats;
    
    @Builder.Default
    @Column(name = "application_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal applicationFee = BigDecimal.ZERO;

    @Column(name = "status", nullable = false, length = 20)
    private Status status;

}
