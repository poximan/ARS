<?php

$gestor = fopen("diccionario/diccionario.txt", "r");
if ($gestor)
  while (($buffer = fgets($gestor)) !== false)
    $diccionario[] = trim($buffer);

fclose($gestor);

/*
api
*/
function evaluarPalabra($frase){

  global $diccionario;
  $palabras = explode(" ", $frase);
  $puntaje = 0;

  foreach ($palabras as $palabra) {

    if(strlen($palabra) <= 1)
      continue;

    $indice = array_search($palabra, $diccionario);

    if ($indice != false)
      $puntaje++;
  }
  return $puntaje;
}

?>
