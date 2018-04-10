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

    $orden = array_search($elemento, $diccionario);
    return $orden;
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

    $lenght = count($diccionario);
    return $lenght;
  }

  function toChar($indice){
    global $diccionario;
    return $diccionario[$indice];
  }

  function longMsg($msg) {
    return mb_strlen($msg);
  }

?>
