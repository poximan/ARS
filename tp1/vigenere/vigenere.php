<?php

include "simbolos/simbolos.php";
$CANT_SIMBOLOS = lenght();

// function to encrypt the text given
function cifrar($msg, $clave) {
	
	$clave = strtolower($clave);
	$msg_cifrado = "";
	$ki = 0;
	$kl = strlen($clave);
	$length = strlen($msg);

	for ($i = 0; $i < $length; $i++) {

		$c = mb_substr($msg,$i, 1);

		$msg_cifrado .= toChar(
			((orden($clave[$ki]) - ordenInferior() + ord($c) - ordenInferior()) % $CANT_SIMBOLOS) + ordenInferior()
		);

		$ki++;
		if ($ki >= $kl) {
			$ki = 0;
		}
	}
	return $msg_cifrado;
}

function descifrar($msg, $clave) {

	$clave = strtolower($clave);
	$msg_descifrado = "";
	$ki = 0;
	$kl = strlen($clave);
	$length = strlen($msg);

	for ($i = 0; $i < $length; $i++) {
		$c = mb_substr($msg,$i, 1);

		$x = (ord($c) - ord("A")) - (ord($clave[$ki]) - ord("a"));

		if ($x < 0)	{
			$x += $CANT_SIMBOLOS;
		}

		$x = $x + ord("A");

		$msg_descifrado .= chr($x);

		// update the index of key
		$ki++;
		if ($ki >= $kl)	{
			$ki = 0;
		}
	}
	return $msg_descifrado;
}

?>
