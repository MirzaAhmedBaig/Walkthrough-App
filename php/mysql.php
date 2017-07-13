<?php

//******** MYSQL CONSTANTS
define('MYSQL_URI', 'mysql.hostinger.in');
define('MYSQL_USR', 'u930183012_admin');
define('MYSQL_PWD', 'Insec@ma');
define('MYSQL_DBN', 'u930183012_insec');
define('MYSQL_PORT', 3306);

$mysql = new mysqli(
	MYSQL_URI, MYSQL_USR, MYSQL_PWD, MYSQL_DBN
) or die("Connect Error ({$mysql->connect_errno}) {$mysql->connect_error}");