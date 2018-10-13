-- Default users
INSERT INTO pos.user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES
  (1, 'admin',    '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com',  1, str_to_date('01/01/2016', '%m/%d/%Y')),
  (2, 'user',     '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user',  'user', 'enabled@user.com',  1, str_to_date('01/01/2016','%m/%d/%Y')),
  (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user',  'user', 'disabled@user.com', 0, str_to_date('01/01/2016','%m/%d/%Y'));

-- Authorities
INSERT INTO authority (ID, NAME) VALUES
  (1, 'ROLE_USER'),
  (2, 'ROLE_ADMIN');

-- Assign authorities to default users
INSERT INTO pos.user_authority (user_id, authority_id) VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (3, 1);
