package com.company.schoolerp.fee.entity;

import java.math.BigDecimal;

import com.company.schoolerp.common.Auditable;
import com.company.schoolerp.common.Status;
import com.company.schoolerp.fee.enums.AdmissionFeeType;
import com.company.schoolerp.fee.enums.LateFineType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admission_fee_structure")
public class AdmissionFeeStructure extends Auditable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adm_fee_id")
    private Long admFeeId;

    @Column(name = "adm_cat_id", nullable = false, length = 50)
    private String admCatId;

    @Column(name = "class_name", length = 100)
    private String className;

    @Column(name = "stream", length = 100)
    private String stream;

    @Column(name = "category", length = 100)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "fee_type", nullable = false, length = 100)
    private AdmissionFeeType feeType;

    @Column(name = "amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "late_fine_type", nullable = false, length = 20)
    private LateFineType lateFineType;

    @Column(name = "late_fine_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal lateFineAmount;

    @Column(name = "late_fine_status", nullable = false, length = 20)
    private String lateFineStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;
	
}












