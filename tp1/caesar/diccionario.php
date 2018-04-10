<?php

  $diccionario = array();
  $file = fopen('diccionario.csv', 'r');
  while (($line = fgetcsv($file)) !== FALSE) {
    $diccionario = $line;
  }
  fclose($file);

  /*
  api
  */
  function orden($elemento){
    global $diccionario;

    $indice = array_search($elemento, $diccionario);

    if ($indice == "")
      $indice = -1;

    return $indice;
  }

  function ordenInferior(){
    return 0;
  }

  function ordenSuperior(){
    return lenght() - 1;
  }

  function elemento($indice) {
    global $diccionario;
    return $diccionario[$indice];
  }

  function lenght() {
    global $diccionario;
    return count($diccionario);
  }

  function toChar($indice){
    global $diccionario;
    return $diccionario[$indice];
  }

?>
