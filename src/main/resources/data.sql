INSERT INTO 
	COUNTERS (counter_name, service_type, counter_service) 
VALUES
  	('priority_deposit', 0, 0),
  	('priority_deposit', 0, 0),
  	('priority_withdraw', 0, 1),
  	('priority_withdraw', 0, 1),
  	('priority_enquiry', 0, 2),
  	('priority_enquiry', 0, 2),
  	('regular_deposit', 1, 0),
  	('regular_deposit', 1, 0),
  	('regular_withdraw', 1, 1),
  	('regular_withdraw', 1, 1),
  	('regular_enquiry', 1, 2),
  	('regular_enquiry', 1, 2);

INSERT INTO 
	CUSTOMERS (name, address, phone_number, service_type) 
VALUES
  	('mani_priority', 'address', 7587580, 0),
  	('kanth_priority', 'address', 7587580, 0),
  	('kumar_priority', 'address', 7587580, 0),
  	('kumar_regular', 'address', 7587580, 1),
  	('mani_regular', 'address', 7587580, 1),
  	('kanth_regular', 'address', 7587580, 1);