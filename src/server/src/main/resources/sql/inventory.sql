insert into customer (id, firstname, lastname, email) values
  (1, 'Jabari',  'Dash',   'jab.dash@gmail.com'),
  (2, 'Jalia',   'Dash',   'jal.dash@gmail.com'),
  (3, 'Jelani',  'Dash',   'del.dash@gmail.com'),
  (4, 'Vanessa', 'Arnold', 'v.arnold@gmail.com'),
  (5, 'Leonard', 'Dash',   'l.dash@gmail.com');
;

insert into category (id, name) values
  (1, 'Clothing'),
  (2, 'Bleach'),
  (3, 'Soap'),
  (4, 'Service'),
  (5, 'Food'),
  (6, 'Electronics'),
  (7, 'Shirt'),
  (8, 'Detergent'),
  (9, 'Laundry Bags');

# insert into kind(id, name) values
#   (1, 'good'),
#   (2, 'service');

insert into product(category_id, name, description, cents, weighted, taxed) values
  (1, 'T-Shirt',     'A basic T-Shirt',     1500,  true, true),
  (1, 'Dress Shirt', 'A basic dress-Shirt', 2000,  false, true),
  (1, 'Jacket',      'Adidas track jacket', 5000,  false, true),
  (1, 'Pants',       'Track pants',         4000,  true, true),
  (2, 'Clorox',      'Clorox bleach',       500,   false, false),
  (3, 'Tide',        'Good detergent',      800,   false, false),
  (9, 'Chips-A-Hoy', 'Blue',                  500, false, false),
  (9, 'Chipotle',    'Red',                   500, false, false);

# insert into product(category_id, name, description, cost, weighted, taxed, kind_id) values
#   (1, 'T-Shirt',     'A basic T-Shirt',     1500,  false, true,  2),
#   (1, 'Dress Shirt', 'A basic dress-Shirt', 2000,  false, true,  2),
#   (1, 'Jacket',      'Adidas track jacket', 5000,  false, true,  2),
#   (1, 'Pants',       'Track pants',         4000,  false, true,  2),
#   (2, 'Clorox',      'Clorox bleach',       500,   false, false, 1),
#   (3, 'Tide',        'Good detergent',      800,   false, false, 1),
#   (9, 'Chips-A-Hoy', 'Blue',                  500, false, false, 1),
#   (9, 'Chipotle',    'Red',                   500, false, false, 1);