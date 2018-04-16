<?php

  include "diccionario/diccionario.php";
  include "caesar/caesar.php";

  function buscarClave($msg) {

    $offset = 0;

    while($offset < lenght()){

      $msg_descifrado[] = descifrar($msg, $offset);

      echo $msg_descifrado[$offset];
      echo "<br/>";

      $offset++;
    }

    return $msg_descifrado;
  }
?>
