INSERT INTO 
	COUNTERS (counter_name, service_type, counter_service, present_token) 
VALUES
  	('priority_deposit', 0, 0, 1),
  	('priority_deposit', 0, 0, 2),
  	('priority_withdraw', 0, 1, null),
  	('priority_withdraw', 0, 1, null),
  	('priority_enquiry', 0, 2, null),
  	('priority_enquiry', 0, 2, null),
  	('regular_deposit', 1, 0, null),
  	('regular_deposit', 1, 0, null),
  	('regular_withdraw', 1, 1, 4),
  	('regular_withdraw', 1, 1, 5),
  	('regular_enquiry', 1, 2, null),
  	('regular_enquiry', 1, 2, null);

INSERT INTO 
	CUSTOMERS (name, address, phone_number, service_type) 
VALUES
  	('mani_priority', 'address', 7587580, 0),
  	('kanth_priority', 'address', 7587580, 0),
  	('kumar_priority', 'address', 7587580, 0),
  	('kumar_regular', 'address', 7587580, 1),
  	('mani_regular', 'address', 7587580, 1),
  	('kanth_regular', 'address', 7587580, 1);
  	
INSERT INTO 
	COUNTER_TOKEN_MAPPINGS (token_id, counter_id) 
VALUES
  	(1, 1),
  	(2, 2),
  	(3, 1),
  	(4, 9),
  	(5, 10),
  	(6, 9);
  	
INSERT INTO 
	TOKENS (token_id, counter_service, customer_id, service_type, status) 
VALUES
  	(1, 0, 1, 0, 0),
  	(2, 0, 2, 0, 0),
  	(3, 0, 3, 0, 0),
  	(4, 1, 4, 1, 0),
  	(5, 1, 5, 1, 0),
  	(6, 1, 5, 1, 0);
  	
  	
  	
  	