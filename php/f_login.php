<?php
session_start();
include_once "mysql.php";

header('Content-Type: application/json');

$id = $_REQUEST['u'];
$pass = $_REQUEST['p'];

$result = $mysql->query("select email, password from user_frainz where email='{$id}' and password='{$pass}'");
if ($result->num_rows == 1) {
	echo json_encode(array("t" => "s"));
} else {
	echo json_encode(array("t" => "e"));
}
