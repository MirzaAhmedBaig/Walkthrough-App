<?php
session_start();
include_once "mysql.php";

header('Content-Type: application/json');

$uname=$_REQUEST['un'];
$id = $_REQUEST['u'];
$pass = $_REQUEST['p'];
$utype=$_REQUEST['t'];
$email=$_REQUEST['em'];
$phno=$_REQUEST['phno'];

$result = $mysql->query("select * from login where login_id='{$id}'");
if ($result->num_rows == 1) {
	echo json_encode(array("t" => "e", "r"=>"Login Id already present...!"));
	return;
}
$result = $mysql->query("select * from user where email='{$email}' and user_type='{$utype}'");
if ($result->num_rows == 1) {
	echo json_encode(array("t" => "e", "r"=>"Email already present...!"));
	return;
}
$mysql->autocommit(false);
$r1 = $mysql->query("insert into login values('$id','$pass')");
$r2 = $mysql->query("insert into user values(null,'{$uname}','{$utype}','{$email}','{$phno}')");
$r3 = $mysql->query("insert into login_user values('{$id}',{$mysql->insert_id})");
$r4 = $mysql->query("insert into location values('{$id}',0,0,null)");
$r5 = $mysql->query("insert into notification values(null,'{$id}',null,null,0,0,null)");
if ($r1 && $r2 && $r3 && $r4 && $r5) {
	$mysql->commit();
    echo json_encode(array("t" => "s"));
}else {
    echo json_encode(array("t" => "e", "r"=>"Something went wrong please try again...!"));
   }
   $mysql->autocommit(true);


