package com.company.schoolerp.admission.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admission_applications")
public class AdmissionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_no", nullable = false, length = 50)
    private String applicationNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admission_category_id")
    private AdmissionCategory admissionCategory;

    @Column(name = "academic_session_id", nullable = false)
    private Long academicSessionId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "gender", length = 20)
    private String gender;
    
    @Builder.Default
    @Column(name = "nationality", length = 50)
    private String nationality = "INDIAN";

    @Column(name = "religion", length = 50)
    private String religion;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "aadhaar_no", length = 20)
    private String aadhaarNo;
    
    @Column(name = "apaar_id", length = 20)
    private String apaarId;
    
    @Column(name = "abc_id", length = 20)
    private String abcID;

    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Column(name = "guardian_name", length = 100)
    private String guardianName;

    @Column(name = "guardian_relation", length = 50)
    private String guardianRelation;

    @Column(name = "mobile", nullable = false, length = 20)
    private String mobile;

    @Column(name = "alternate_mobile", length = 20)
    private String alternateMobile;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "village", length = 100)
    private String village;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "district", length = 100)
    private String district;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "pincode", length = 10)
    private String pincode;
    
    // Previous school details
    @Column(name = "previous_school_name", length = 100)
    private String previousSchoolName;

    @Column(name = "previous_class", length = 50)
    private String previousClass;

    @Column(name = "previous_school_board", length = 50)
    private String previousSchoolBoard;

    @Column(name = "previous_roll_no", length = 50)
    private String previousRollNo;

    @Column(name = "previous_percentage", precision = 5, scale = 2)
    private BigDecimal previousPercentage;
    
    @Builder.Default
    @Column(name = "application_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal applicationFee = BigDecimal.ZERO;
    
    @Column(name = "application_payment_status", length = 30)
    private String applicationPaymentStatus;

    @Column(name = "application_payment_date")
    private OffsetDateTime applicationPaymentDate;

    @Column(name = "application_payment_mode", length = 30)
    private String applicationPaymentMode;

    @Column(name = "application_payment_transaction_no", length = 100)
    private String applicationPaymentTransactionNo;

    @Column(name = "application_payment_receipt_no", length = 100)
    private String applicationPaymentReceiptNo;
    
    @Builder.Default
    @Column(name = "admission_fee", nullable = false, precision = 12, scale = 2)
    private BigDecimal admissionFee = BigDecimal.ZERO;
    
    @Column(name = "admission_payment_status", nullable = false, length = 30)
    private String admissionPaymentStatus;

    @Column(name = "admission_payment_date")
    private OffsetDateTime admissionPaymentDate;

    @Column(name = "admission_payment_mode", length = 30)
    private String admissionPaymentMode;

    @Column(name = "admission_payment_transaction_no", length = 100)
    private String admissionPaymentTransactionNo;

    @Column(name = "admission_payment_receipt_no", length = 100)
    private String admissionPaymentReceiptNo;

    // Application status
    @Column(name = "status", nullable = false, length = 40)
    private String status;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "rejection_reason", columnDefinition = "TEXT")
    private String rejectionReason;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Builder.Default
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdmissionDocument> documents = new ArrayList<>();

}
