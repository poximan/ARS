<?php

  include "diccionario.php";

  const DICCIONARIO = 26;

  function cifrar($msg, $offset) {

    $msg_cifrado = "";
    $offset = $offset % DICCIONARIO;

    if($offset < 0) {
      $offset += DICCIONARIO;
    }

    $i = 0;
    while($i < strlen($msg)) {

      /*
      si el siguiente caracter esta escrito en mayuscula
      */
      if(ctype_upper($msg{$i})) {

        $c = $msg{$i};

        if(($c >= "A") && ($c <= 'Z')) {

          // valor ASCII del primer caracter
          if((ord($c) + $offset) > ord("Z")) {
            $msg_cifrado .= chr(ord($c) + $offset - DICCIONARIO);
          } else {
            $msg_cifrado .= chr(ord($c) + $offset);
          }
        } else {
          $msg_cifrado .= " ";
        }
      }

      /*
      si el siguiente caracter esta escrito en minuscula
      */
      if(!ctype_upper($msg{$i})) {

        $c = $msg{$i};

        if(($c >= "a") && ($c <= 'z')) {

          // valor ASCII del primer caracter
          if((ord($c) + $offset) > ord("z")) {
            $msg_cifrado .= chr(ord($c) + $offset - DICCIONARIO);
          } else {
            $msg_cifrado .= chr(ord($c) + $offset);
          }
        } else {
          $msg_cifrado .= " ";
        }
      }

      $i++;
    }
    return $msg_cifrado;
  }

  function descifrar($msg, $offset) {

    $msg_descifrado = "";
    $offset = $offset % DICCIONARIO;

    if($offset < 0) {
      $offset += DICCIONARIO;
    }
    $i = 0;
    while($i < strlen($msg)) {

      /*
      si el siguiente caracter esta escrito en mayuscula
      */
      if(ctype_upper($msg{$i})) {

        $c = $msg{$i};

        if(($c >= "A") && ($c <= 'Z')) {

          // valor ASCII del primer caracter
          if((ord($c) - $offset) < ord("A")) {
            $msg_descifrado .= chr(ord($c) - $offset + DICCIONARIO);
          } else {
            $msg_descifrado .= chr(ord($c) - $offset);
          }
        } else {
          $msg_descifrado .= " ";
        }
      }

      /*
      si el siguiente caracter esta escrito en minuscula
      */
      if(!ctype_upper($msg{$i})) {

        $c = $msg{$i};

        if(($c >= "a") && ($c <= 'z')) {

          // valor ASCII del primer caracter
          if((ord($c) - $offset) < ord("a")) {
            $msg_descifrado .= chr(ord($c) - $offset + DICCIONARIO);
          } else {
            $msg_descifrado .= chr(ord($c) - $offset);
          }
        } else {
          $msg_descifrado .= " ";
        }
      }

      $i++;
    }
    return $msg_descifrado;
  }
?>
