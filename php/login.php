<?php
session_start();
include_once "mysql.php";

header('Content-Type: application/json');

$id = $_REQUEST['u'];
$pass = $_REQUEST['p'];
$utype=$_REQUEST['t'];
$result = $mysql->query("select * from user u, login l,login_user lu where
												l.login_id=lu.login_id and
												lu.user_id=u.user_id and
												l.login_id='{$id}' and
												l.password='{$pass}' and
												u.user_type='{$utype}'");
if ($result->num_rows == 1) {
	echo json_encode(array("t" => "s"));
	$_SESSION['user']=$id;
} else {
	echo json_encode(array("t" => "e"));
}
