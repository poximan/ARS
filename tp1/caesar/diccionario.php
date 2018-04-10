<?php

  $dicc_mayusc = array();
  $file = fopen('dicc_mayusc.csv', 'r');
  while (($line = fgetcsv($file)) !== FALSE) {
    $dicc_mayusc = $line;
  }
  fclose($file);

  $dicc_minusc = array();
  $file = fopen('dicc_minusc.csv', 'r');
  while (($line = fgetcsv($file)) !== FALSE) {
    $dicc_minusc = $line;
  }
  fclose($file);

  function lenghtMayusc() {
    global $dicc_mayusc;
    return count($dicc_mayusc);
  }

  function diccionarioMayusc() {
    global $dicc_mayusc;
    return $dicc_mayusc;
  }

  function elementoMayusc($indice) {
    global $dicc_mayusc;
    return $dicc_mayusc[$indice];
  }

  function lenghtMinusc() {
    global $dicc_mayusc;
    return count($dicc_mayusc);
  }

  function diccionarioMinusc() {
    global $dicc_mayusc;
    return $dicc_mayusc;
  }

  function elementoMinusc($indice) {
    global $dicc_mayusc;
    return $dicc_mayusc[$indice];
  }

?>
