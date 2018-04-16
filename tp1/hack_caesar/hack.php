<?php

include "diccionario/diccionario.php";
include "caesar/caesar.php";

function buscarClave($msg) {

  $offset = 0;

  while($offset < lenght()){

    $palabra = descifrar($msg, $offset);
    $puntaje = evaluarPalabra($palabra);

    $msg_descifrado[$offset] = array(
      "offset" => $offset,
      "puntaje" => $puntaje,
      "palabra" => $palabra
    );

    $offset++;
  }

  usort($msg_descifrado, 'ordenarPorPuntaje');
  return $msg_descifrado;
}

function ordenarPorPuntaje($a, $b) {
  $a = $a['puntaje'];
  $b = $b['puntaje'];

  if ($a == $b) return 0;
  return ($a > $b) ? -1 : 1;
}

?>
