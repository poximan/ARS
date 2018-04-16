<?php

  include "hack.php";

  $msj_original = $_POST['mensaje'];

  $claves = buscarClave($msj_original);

  //mostarResultado($msj_original, $clave);

  function mostarResultado($msj_original, $clave){

    echo "mensaje cifrado: {$msj_original}";
    echo "<br/>";
    echo "se uso desplazamiento de {$clave} digitos";
    echo "<br/>";
  }
?>
