<?php
session_start();
include_once "mysql.php";

header('Content-Type: application/json');

$fname=$_REQUEST['fn'];
$lname=$_REQUEST['ln'];
$id = $_REQUEST['u'];
$pass = $_REQUEST['p'];

$result = $mysql->query("select * from user_frainz where email='{$id}'");
if ($result->num_rows == 1) {
	echo json_encode(array("t" => "e", "r"=>"Email already present...!"));
	return;
}
$mysql->autocommit(false);
$r1 = $mysql->query("insert into user_frainz values('$id','$fname','$lname','$pass')");
if ($r1) {
	$mysql->commit();
    echo json_encode(array("t" => "s"));
}else {
    echo json_encode(array("t" => "e", "r"=>"Something went wrong please try again...!"));
   }
   $mysql->autocommit(true);


