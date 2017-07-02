<?php 
// ACA SE HACE LA CONEXION CON LA BASE DE DATOS 
header( 'Content-Type: text/html;charset=utf-8' );


function ejecutarSQLCommand($commando){
 // SE GUARDA LOS DATOS A REQUERIR A LA HORA DE ENTRAR A LA BASE DE DATOS
  $mysqli = new mysqli("localhost", "WebService", "webservice", "webservice");
                        //SERVIDOR   USUARIO        CONTRASEÑA    NOMBRE DE LA BASE DE DATOS
/* VERIFICACION DE CONEXION */
if ($mysqli->connect_errno) {
    printf("Connect failed: %s\n", $mysqli->connect_error);
    exit();
}

if ( $mysqli->multi_query($commando)) {
     if ($resultset = $mysqli->store_result()) {
    	while ($row = $resultset->fetch_array(MYSQLI_BOTH)) {
    		
    	}
    	$resultset->free();
     }
    
   
}



$mysqli->close();
}

function getSQLResultSet($commando){
 
 
  $mysqli = new mysqli("localhost", "WebService", "webservice", "webservice");
/* VERIFICACION DE CONEXION */
if ($mysqli->connect_errno) {
    printf("Connect failed: %s\n", $mysqli->connect_error);
    exit();
}

if ( $mysqli->multi_query($commando)) {
	return $mysqli->store_result();
	
     
    
   
}



$mysqli->close();
}


?>
