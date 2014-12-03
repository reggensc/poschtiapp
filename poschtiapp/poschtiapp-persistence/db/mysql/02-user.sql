CREATE USER 'poschtiapp' IDENTIFIED BY 'poschtiapp';
GRANT ALL ON poschtiapp.* TO 'poschtiapp'@'%' IDENTIFIED BY 'poschtiapp';
GRANT ALL ON poschtiapp.* TO 'poschtiapp'@'localhost' IDENTIFIED BY 'poschtiapp';
FLUSH PRIVILEGES;