<?php

$host = "localhost";
$username = "user";
$password = "Password";
$db = "OVERFLOW";
$con = mysqli_connect("$host", "$username","$password","$db");

if (!$con) {
    die("Connection failed: " . mysqli_connect_error());
}

?>