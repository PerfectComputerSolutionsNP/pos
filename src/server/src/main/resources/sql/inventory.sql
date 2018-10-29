insert into category (id, name) values
  (1, 'Clothing'),
  (2, 'Bleach'),
  (3, 'Soap'),
  (4, 'Service'),
  (5, 'Food'),
  (6, 'Electronics');

insert into product(category_id, name, description, cost) values
  (1, 'T-Shirt',     'A basic T-Shirt',     15000),
  (1, 'Dress Shirt', 'A basic dress-Shirt', 20000),
  (1, 'Jacket',      'Adidas track jacket', 50000),
  (1, 'Pants',       'Track pants',         40000),
  (2, 'Clorox',      'Clorox bleach',       5000),
  (3, 'Tide',        'Good detergent',      8000),
  (3, 'No-Name',     'Bar detergent',       6000),
  (4, 'Cookies',     'Chips-A-Hoy',         3000);
