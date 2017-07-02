<?php
//PARTE PHP EN ESTA USAREMOS UNA CONEXIONA ANTES DE MANIPULAR LOS QUERIS CON LA BASE DE DATOS 
//LA CUAL SERA POR EL INCLUDE FUNCTIONS.PHP
include('functions.php');
// ACA SE MANEJA EL QUERY PODEMOS UTILIZAR CUALQUIER TIPO DE QUERI SOPORTADO POR MYSQL 
if($resultset=getSQLResultSet("SELECT CONCAT(nombre,' ',cedula) as nombre FROM `usuario`")){	//SE HACE LA PETICION DEL NOMBRE Y CEDULA
	while ($row = $resultset->fetch_array(MYSQLI_NUM)){											//SE LO GUARDA EN UN ARRAY
		echo json_encode($row);																	//Y SE LO MUESTRA
	}
}

?>


