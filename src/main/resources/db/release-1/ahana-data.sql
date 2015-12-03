INSERT INTO lookup(code, category, description, seq, deprecated) VALUES ('India', 'COUNTRY', 'India', 1, false),
('Malaysia', 'COUNTRY', 'Malaysia', 2, false),('Bangladesh', 'COUNTRY', 'Bangladesh', 3, false),('Indonesia', 'COUNTRY', 'Indonesia', 4, false),
('Pakistan', 'COUNTRY', 'Pakistan', 5, false),('Singapore', 'COUNTRY', 'Singapore', 6, false),('Sri Lanka', 'COUNTRY', 'Sri Lanka', 7, false)
,('Nepal', 'COUNTRY', 'Nepal', 8, false),('Thailand', 'COUNTRY', 'Thailand', 9, false),('Vietnam', 'COUNTRY', 'Vietnam', 10, false),
('Philippines', 'COUNTRY', 'Philippines', 11, false);

INSERT INTO lookup(code, category, description, seq, deprecated) VALUES ('Mr', 'SALUTATION', 'Mr', 1, false),
('Miss', 'SALUTATION', 'Miss', 2, false),('Dr', 'SALUTATION', 'Dr', 3, false),('Ms', 'SALUTATION', 'Ms', 4, false),
('Prof', 'SALUTATION', 'Prof', 5, false),('Rev', 'SALUTATION', 'Rev', 6, false);

INSERT INTO lookup(code, category, description, seq, deprecated) VALUES ('A+', 'BLOODGROUP', 'A+', 1, false),
('A-', 'BLOODGROUP', 'A-', 2, false),('B+', 'BLOODGROUP', 'B+', 3, false),('B-', 'BLOODGROUP', 'B-', 4, false),
('AB+', 'BLOODGROUP', 'AB+', 5, false),('AB-', 'BLOODGROUP', 'AB-', 6, false),
('O+', 'BLOODGROUP', 'O+', 7, false),('O-', 'BLOODGROUP', 'O-', 8, false);

INSERT INTO lookup(code, category, description, seq, deprecated) VALUES 
('Tamil Nadu', 'STATE_INDIA', 'Tamil Nadu', 1, false),('Andhra Pradesh', 'STATE_INDIA', 'Andhra Pradesh', 2, false),
('Arunachal Pradesh', 'STATE_INDIA', 'Arunachal Pradesh', 3, false),('Assam', 'STATE_INDIA', 'Assam', 4, false),
('Bihar', 'STATE_INDIA', 'Bihar', 5, false),('Chhattisgarh', 'STATE_INDIA', 'Chhattisgarh', 6, false),
('Goa', 'STATE_INDIA', 'Goa', 7, false),('Gujarat', 'STATE_INDIA', 'Gujarat', 8, false),
('Haryana', 'STATE_INDIA', 'Haryana', 9, false),('Himachal Pradesh', 'STATE_INDIA', 'Himachal Pradesh', 10, false),
('Jammu & kashmir', 'STATE_INDIA', 'Jammu & kashmir', 11, false),('Jharkhand', 'STATE_INDIA', 'Jharkhand', 12, false),
('Karnataka', 'STATE_INDIA', 'Karnataka', 13, false),('Kerala', 'STATE_INDIA', 'Kerala', 14, false),
('Madhya Pradesh', 'STATE_INDIA', 'Madhya Pradesh', 15, false),('Maharashtra', 'STATE_INDIA', 'Maharashtra', 16, false),
('Manipur', 'STATE_INDIA', 'Manipur', 17, false),('Meghalaya', 'STATE_INDIA', 'Meghalaya', 18, false),
('Mizoram', 'STATE_INDIA', 'Mizoram', 19, false),('Nagaland', 'STATE_INDIA', 'Nagaland', 20, false),
('Orissa', 'STATE_INDIA', 'Orissa', 21, false),('Punjab', 'STATE_INDIA', 'Punjab', 22, false),
('Rajasthan', 'STATE_INDIA', 'Rajasthan', 23, false),('Sikkim', 'STATE_INDIA', 'Sikkim', 24, false),
('Tripura', 'STATE_INDIA', 'Tripura', 25, false),('Uttar Pradesh', 'STATE_INDIA', 'Uttar Pradesh', 26, false),
('Uttarakhand', 'STATE_INDIA', 'Uttarakhand', 27, false),('West Bengal', 'STATE_INDIA', 'West Bengal', 28, false),
('Andaman & Nicobar Islands', 'STATE_INDIA', 'Andaman & Nicobar Islands', 29, false),('Chandigarh', 'STATE_INDIA', 'Chandigarh', 30, false),
('Dadra & Nagar Haveli', 'STATE_INDIA', 'Dadra & Nagar Haveli', 31, false),('Daman & Diu', 'STATE_INDIA', 'Daman & Diu', 32, false),
('Delhi', 'STATE_INDIA', 'Delhi', 33, false),('Lakshadweep', 'STATE_INDIA', 'Lakshadweep', 34, false),('Puducherry', 'STATE_INDIA', 'Puducherry', 35, false);

INSERT INTO seed_container(high_oid, seed_id, seq_id,type) VALUES(00000,'a01',1,'REST');
INSERT INTO seed_container(high_oid, seed_id, seq_id,type) VALUES(1,'PAT',2,'PAT');

INSERT INTO lookup(code, category, description, seq, deprecated) VALUES ('Standard-Default', 'CATEGORY', 'Standard-Default', 1, false),
('Free', 'CATEGORY', 'Free', 2, false),('Priority', 'CATEGORY', 'Priority', 3, false),('Priority-Free', 'CATEGORY', 'Priority-Free', 4, false),
('VIP', 'CATEGORY', 'VIP', 5, false);

 INSERT INTO lookup(code, category, description, seq, deprecated) VALUES ('Madurai', 'CITY_TAMIL NADU', 'Madurai', 1, false),
 ('Chennai', 'CITY_TAMIL NADU', 'Chennai', 30, false), ('Coimbatore', 'CITY_TAMIL NADU', 'Coimbatore', 2, false),
 ('Cuddalore', 'CITY_TAMIL NADU', 'Cuddalore', 3, false), ('Dharmapuri', 'CITY_TAMIL NADU', 'Dharmapuri', 4, false),
 ('Dindigul', 'CITY_TAMIL NADU', 'Dindigul', 5, false), ('Erode', 'CITY_TAMIL NADU', 'Erode', 6, false),
 ('Kanchipuram', 'CITY_TAMIL NADU', 'Kanchipuram', 7, false), ('Kanyakumari', 'CITY_TAMIL NADU', 'Kanyakumari', 8, false), 
 ('Karur', 'CITY_TAMIL NADU', 'Karur', 9, false), ('Krishnagiri', 'CITY_TAMIL NADU', 'Krishnagiri', 10, false), 
 ('Nagapattinam', 'CITY_TAMIL NADU', 'Nagapattinam', 11, false), ('Namakkal', 'CITY_TAMIL NADU', 'Namakkal', 12, false),
 ('Perambalur', 'CITY_TAMIL NADU', 'Perambalur', 13, false), ('Pudukkottai', 'CITY_TAMIL NADU', 'Pudukkottai', 14, false),
 ('Ramanathapuram', 'CITY_TAMIL NADU', 'Ramanathapuram', 15, false), ('Salem', 'CITY_TAMIL NADU', 'Salem', 16, false),
 ('Sivaganga', 'CITY_TAMIL NADU', 'Sivaganga', 17, false), ('Thanjavur', 'CITY_TAMIL NADU', 'Thanjavur', 18, false),
 ('The Nilgiris', 'CITY_TAMIL NADU', 'The Nilgiris', 19, false), ('Theni', 'CITY_TAMIL NADU', 'Theni', 20, false),
 ('Thoothukudi', 'CITY_TAMIL NADU', 'Thoothukudi', 21, false), ('Tiruchirapalli', 'CITY_TAMIL NADU', 'Tiruchirapalli', 22, false),
 ('Tirunelveli', 'CITY_TAMIL NADU', 'Tirunelveli', 23, false), ('Tiruvallur', 'CITY_TAMIL NADU', 'Tiruvallur', 24, false),
 ('Tiruvannamalai', 'CITY_TAMIL NADU', 'Tiruvannamalai', 25, false), ('Tiruvarur', 'CITY_TAMIL NADU', 'Tiruvarur', 26, false),
 ('Vellore', 'CITY_TAMIL NADU', 'Vellore', 27, false), ('Viluppuram', 'CITY_TAMIL NADU', 'Viluppuram', 28, false),
 ('Virudhunagar', 'CITY_TAMIL NADU', 'Virudhunagar', 29, false);
  
 INSERT INTO lookup(code, category, description, seq, deprecated) VALUES ('Allied Charges', 'CHARGESCATEGORY', 'Allied Charges', 1, false),
('Room Charges', 'CHARGESCATEGORY', 'Room Charges', 2, false),('Professional Charges', 'CHARGESCATEGORY', 'Professional Charges', 3, false),
('Procedures Charges', 'CHARGESCATEGORY', 'Procedures Charges', 4, false),
('Laboratory Charges', 'CHARGESCATEGORY', 'Laboratory Charges', 5, false);

 INSERT INTO `arkinfot_ahana`.`client` (`oid`, `client_id`, `api_key`, `authorized_services`, `auth_ip_addresses`, `client_type`, `contract_started_on`, `contract_ends_on`, `api_key_expiration_date`, `created_on`, `updated_on`, `created_by`, `updated_by`) 
 VALUES ('o0100000402000000501', 'ahana', '574EBDD5B9D10563', '*', '*', 'Internal', '2015-11-24 00:00:00', '2020-01-01 00:00:00', '2020-01-01 00:00:00', '2015-11-24 00:00:00', '2015-11-24 00:00:00', 'ahana', 'ahana');
 
 
 INSERT INTO lookup(code, category, description, seq, deprecated) VALUES ('All Days', 'DAYS', 'All Days', 1, false),
('Sunday', 'DAYS', 'Sunday', 2, false),('Monday', 'DAYS', 'Monday', 3, false),('Tueday', 'DAYS', 'Tueday', 4, false),
('Wednesday', 'DAYS', 'Wednesday', 5, false),('Thursday', 'DAYS', 'Thursday', 6, false),('Friday', 'DAYS', 'Friday', 7, false)
,('Saturday', 'DAYS', 'Saturday', 8, false);
