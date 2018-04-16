<?php

include "hack.php";

$msj_original = $_POST['mensaje'];

$msg_descifrado = buscarClave($msj_original);
mostarResultado($msj_original, $msg_descifrado);

function mostarResultado($msj_original, $msg_descifrado){

  echo "mensaje cifrado: {$msj_original} <br/><br/>";
  echo "claves candidatas <br/>";
  echo "----------------------<br/>";

  $maximo = (count($msg_descifrado) > 10)? 10 : count($msg_descifrado);

  for($i = 0; $i < $maximo; $i++){
    print "clave: " . $msg_descifrado[$i]["offset"] . " | mensaje descifrado: " . $msg_descifrado[$i]["palabra"];
    echo "<br/>";
  }
}

?>
