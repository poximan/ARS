<?php

  $gestor = fopen("simbolos/simbolos.txt", "r");
  if ($gestor)
    while (($buffer = fgets($gestor)) !== false){

      $ch = substr($buffer, 0, 1);

      if($ch === ' ')
        $simbolos[] = $ch;
      else
        $simbolos[] = trim($buffer);
    }


  fclose($gestor);

  /*
  api
  */
  function orden($elemento){

    global $simbolos;
    $indice = array_search($elemento, $simbolos);

    if ($indice === false)
      return -1;

    return $indice;
  }

  function ordenInferior(){
    return 0;
  }

  function ordenSuperior(){
    return lenght() - 1;
  }

  function lenght() {
    global $simbolos;
    return count($simbolos);
  }

  function toChar($indice){
    global $simbolos;
    return $simbolos[$indice];
  }

?>
