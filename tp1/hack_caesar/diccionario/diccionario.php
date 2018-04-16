<?php

  $gestor = fopen("diccionario/diccionario.txt", "r");
  if ($gestor)
    while (($buffer = fgets($gestor)) !== false)
      $diccionario[] = substr($buffer, 0, 1);

  fclose($gestor);

  /*
  api
  */
  function existe($elemento){

    global $diccionario;
    $indice = array_search($elemento, $diccionario);

    return ($indice != false);
  }

?>
