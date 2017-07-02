
<?php 
//PARTE PHP EN ESTA USAREMOS UNA CONEXIONA ANTES DE MANIPULAR LOS QUERIS CON LA BASE DE DATOS 
//LA CUAL SERA POR EL INCLUDE FUNCTIONS.PHP

include ('functions.php');
//EN ESTE CASI NUESTRA JASON NECESITA DATOS POR ELLO LO PEDIMOS Y GUARDAMOS EN VARIABLES
$cedula=$_GET['cedula'];
$nombre=$_GET['nombre'];
$fechanac=$_GET['fechanac'];
$direccion=$_GET['direccion'];
//LUEGO LOS USAMOS EN EL QUERY PARA ALMACENAR LOS DATOS EN LA BASE DE DATOS
ejecutarSQLCommand("INSERT INTO `usuario` (`cedula`, `nombre`, `fechanac`, `direccion`) 
	VALUES ('$cedula', '$nombre', '$fechanac', '$direccion');");

 
 ?>