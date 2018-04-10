<?php

  include "caesar.php";

  $operacion = $_POST['operacion'];
  $clave = $_POST['clave'];
  $msj_original = $_POST['mensaje'];

  $msj_resultado = "";

  if ($operacion == "cifrar"){
    $msj_resultado = cifrar($msj_original, $clave);
    mostarResultado($msj_original, $operacion, $clave);
  }

  if ($operacion == "descifrar") {
    $msj_resultado = descifrar($msj_original, $clave);
    mostarResultado($msj_original, $operacion, $clave);
  }

  echo "mensaje resultante {$msj_resultado}";

  function mostarResultado($msj_original, $operacion, $clave){

    echo "mensaje original: {$msj_original}";
    echo "<br/>";
    echo "para {$operacion} se uso {$clave} desplazamiento";
    echo "<br/>";
  }
?>
