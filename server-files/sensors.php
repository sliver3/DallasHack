<?php

	require_once("connection.php");
	
	$request = $_GET['request'];
	$query = "select sensorID, position, location from SENSORS";
	$result = mysqli_query($con, $query);

	if ($result != null) {
    	$row = mysqli_fetch_array($result);
    	while ($row) {
	    	echo "$row[0] $row[1] $row[2],";
	    	$row = mysqli_fetch_array($result);
	    }

		
	} else {
		echo "Bad request";
	}

?>