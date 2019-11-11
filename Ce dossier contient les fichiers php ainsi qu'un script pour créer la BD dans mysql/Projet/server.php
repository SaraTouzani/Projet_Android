<?php

include 'connect.php';

if (isset($_REQUEST["operation"])) {
	if ($_REQUEST["operation"]=="enreg") {
		$lesdonnees=$_REQUEST["lesdonnees"];
		$donnee=json_decode(utf8_encode($lesdonnees), TRUE);
		$id=$donnee["id"];
		$num=$donnee["num"];
		$duree=$donnee["duree"];
		$statut=$donnee["statut"];
		$date=$donnee["sDate"];


		$req="INSERT INTO  `appel` (
		`id` ,
		`num` ,
		`duree` ,
		`statut`,
		`date`)
		VALUES (
		NULL , '".$num."', '".$duree."' , '".$statut."', '".$date."');";
		$res=mysqli_query($con,$req);
		mysqli_close($con);
		echo "all is well****";
	}
}



?>