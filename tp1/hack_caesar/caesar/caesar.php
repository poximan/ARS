<?php

  include "caesar/simbolos/simbolos.php";
  $CANT_SIMBOLOS = lenght();

  function cifrar($msg, $offset) {

    global $CANT_SIMBOLOS;

    $msg_cifrado = "";
    $offset = $offset % $CANT_SIMBOLOS;

    if($offset < 0) {
      $offset += $CANT_SIMBOLOS;
    }

    $i = 0;
    while($i < mb_strlen($msg)) {

      $c = mb_substr($msg,$i, 1);

      if((orden($c) >= ordenInferior()) && (orden($c) <= ordenSuperior())) {

        if((orden($c) + $offset) > ordenSuperior()) {
          $msg_cifrado .= toChar(orden($c) + $offset - $CANT_SIMBOLOS);
        } else {
          $msg_cifrado .= toChar(orden($c) + $offset);
        }
      } else {
        $msg_cifrado .= " ERROR: simb \"$c\" desc pos $i";
        $i = mb_strlen($msg);
      }

      $i++;
    }
    return $msg_cifrado;
  }

  function descifrar($msg, $offset) {

    global $CANT_SIMBOLOS;

    $msg_descifrado = "";
    $offset = $offset % $CANT_SIMBOLOS;

    if($offset < 0) {
      $offset += $CANT_SIMBOLOS;
    }

    $i = 0;
    while($i < mb_strlen($msg)) {

      $c = mb_substr($msg,$i, 1);

      if((orden($c) >= ordenInferior()) && (orden($c) <= ordenSuperior())) {

        if((orden($c) - $offset) < ordenInferior()) {
          $msg_descifrado .= toChar(orden($c) - $offset + $CANT_SIMBOLOS);
        } else {
          $msg_descifrado .= toChar(orden($c) - $offset);
        }
      } else {
        $msg_cifrado .= " ERROR: simb \"$c\" desc pos $i";
        $i = mb_strlen($msg);
      }

      $i++;
    }
    return $msg_descifrado;
  }
?>
