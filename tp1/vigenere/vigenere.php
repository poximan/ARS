<?php

include "simbolos/simbolos.php";
$CANT_SIMBOLOS = lenght();

/*
cifrar - https://www.youtube.com/watch?v=8Pb5U64iwV4
ci = (mi + ki) mod n
*/
function cifrar($msg, $clave) {

	global $CANT_SIMBOLOS;

	$msg_cifrado = "";
	$tam_clave = strlen($clave);

	$i = 0;
  while($i < mb_strlen($msg)) {

		$m = mb_substr($msg, $i, 1);
		$k = $clave[$i % $tam_clave];

		if((orden($m) >= ordenInferior()) && (orden($m) <= ordenSuperior())) {

			if((orden($m) + orden($k)) > ordenSuperior()) {
        $msg_cifrado .= toChar(((orden($m) + orden($k)) % $CANT_SIMBOLOS) - $CANT_SIMBOLOS);
      } else {
        $msg_cifrado .= toChar((orden($m) + orden($k)) % $CANT_SIMBOLOS);
      }
    } else {
      $msg_cifrado .= " ERROR: simb \"$m\" desc pos $i";
      $i = mb_strlen($msg);
    }

    $i++;
	}
	return $msg_cifrado;
}

/*
descifrar - https://www.youtube.com/watch?v=8Pb5U64iwV4
mi = (ci - ki) mod n
*/
function descifrar($msg, $clave) {

	global $CANT_SIMBOLOS;

	$msg_descifrado = "";
	$tam_clave = strlen($clave);

	$i = 0;
  while($i < mb_strlen($msg)) {

		$m = mb_substr($msg, $i, 1);
		$k = $clave[$i % $tam_clave];

		if((orden($m) >= ordenInferior()) && (orden($m) <= ordenSuperior())) {

			if((orden($m) - orden($k)) < ordenInferior()) {
        $msg_descifrado .= toChar(((orden($m) - orden($k)) % $CANT_SIMBOLOS) + $CANT_SIMBOLOS);
      } else {
        $msg_descifrado .= toChar((orden($m) - orden($k)) % $CANT_SIMBOLOS);
      }
    } else {
      $msg_descifrado .= " ERROR: simb \"$m\" desc pos $i";
      $i = mb_strlen($msg);
    }

    $i++;
	}
	return $msg_descifrado;
}

?>
