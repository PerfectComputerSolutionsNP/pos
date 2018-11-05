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

insert into service(category_id, name, description) values
  (4, 'Wash', 'Wash some clothes'),
  (4, 'Dry',  'Dry some clothes'),
  (4, 'Fold', 'Fold some clothes'),
  (4, 'Iron', 'Iron some clothes');

insert into product(category_id, name, description, cost) values
  (1, 'T-Shirt',     'A basic T-Shirt',     15000),
  (1, 'Dress Shirt', 'A basic dress-Shirt', 20000),
  (1, 'Jacket',      'Adidas track jacket', 50000),
  (1, 'Pants',       'Track pants',         40000),
  (2, 'Clorox',      'Clorox bleach',       5000),
  (3, 'Tide',        'Good detergent',      8000),
  (3, 'No-Name',     'Bar detergent',       6000),
  (4, 'Cookies',     'Chips-A-Hoy',         3000),
  (5, 'Meat',        'Chicken',             2000),
  (5, 'Rice',        'Carolina',            250000),
  (6, 'Television',  'LG',                  3500000),
  (6, 'Radio',       'Radio-Bemba',         123400),
  (7, 'Shirt',       'White shirts',        25000),
  (7, 'Pedro',       'Color shirt',         20000),
  (8, 'Sun Products','150 oz',              18000000),
  (8, 'Wisk',        '150 oz',              125000),
  (8, 'Snuggle',     '150 oz',              125000),
  (8, 'Sunlight',    '150 oz',              125000),
  (8, 'Arm & Hammer','150 oz',              125000),
  (9, 'Juan',        'Green',                 5000),
  (9, 'Chips-A-Hoy', 'Blue',                  5000),
  (9, 'Chipotle',    'Red',                   5000),
  (9, 'Small',       'White',                 5000),
  (9, 'Large',       'White',                 5000);