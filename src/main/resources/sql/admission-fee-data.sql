
delete from admission_fee_structure where adm_fee_id in(1,2,3);
INSERT INTO admission_fee_structure
(
    adm_fee_id, adm_cat_id, class_name, stream, category, fee_type, amount, late_fine_type, late_fine_amount, status
)
VALUES
(1, 'ADM001', 'Class 1', NULL, 'General', 'ADMISSION', 5000, 'NONE', 0, 'ACTIVE'),
(2, 'ADM001', 'Class 1', NULL, 'General', 'LAB', 1000, 'NONE', 0, 'ACTIVE'),
(3, 'ADM001', 'Class 1', NULL, 'General', 'TRANSPORTATION', 2000, 'FIXED', 100, 'ACTIVE');