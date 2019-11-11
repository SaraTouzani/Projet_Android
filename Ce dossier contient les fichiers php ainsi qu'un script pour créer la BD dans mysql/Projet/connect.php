<?php
$hostname="localhost";
$username="root";
$password='';
$dbname="appels";

 
//Connect to the database
$con = mysqli_connect($hostname, $username, $password,$dbname);
//mysqli_select_db($dbname, $con);
if(!$con){ echo "connexion impossible".mysqli_connect_error();}


?>