<?php

  include 'diccionario.php';
  $DICCIONARIO = lenght();

  function cifrar($msg, $offset) {

    global $DICCIONARIO;

    $msg_cifrado = "";
    $offset = $offset % $DICCIONARIO;

    if($offset < 0) {
      $offset += $DICCIONARIO;
    }

    $i = 0;
    while($i < mb_strlen($msg)) {

      $c = mb_substr($msg,$i, 1);

      if((orden($c) >= ordenInferior()) && (orden($c) <= ordenSuperior())) {

        if((orden($c) + $offset) > ordenSuperior()) {
          $msg_cifrado .= toChar(orden($c) + $offset - $DICCIONARIO);
        } else {
          $msg_cifrado .= toChar(orden($c) + $offset);
        }
      } else {
        $msg_cifrado .= " ";
      }

      $i++;
    }
    return $msg_cifrado;
  }

  function descifrar($msg, $offset) {

    global $DICCIONARIO;

    $msg_descifrado = "";
    $offset = $offset % $DICCIONARIO;

    if($offset < 0) {
      $offset += $DICCIONARIO;
    }

    $i = 0;
    while($i < mb_strlen($msg)) {

      $c = mb_substr($msg,$i, 1);

      if((orden($c) >= ordenInferior()) && (orden($c) <= ordenSuperior())) {

        if((orden($c) - $offset) < ordenInferior()) {
          $msg_descifrado .= toChar(orden($c) - $offset + $DICCIONARIO);
        } else {
          $msg_descifrado .= toChar(orden($c) - $offset);
        }
      } else {
        $msg_descifrado .= " ";
      }

      $i++;
    }
    return $msg_descifrado;
  }
?>
