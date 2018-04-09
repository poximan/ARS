<?php
function cifrar($str, $offset) {
    $msg_cifrado = "";
    $offset = $offset % 26;
    if($offset < 0) {
        $offset += 26;
    }
    $i = 0;
    while($i < strlen($str)) {
        $c = strtoupper($str{$i});
        if(($c >= "A") && ($c <= 'Z')) {
            if((ord($c) + $offset) > ord("Z")) {
                $msg_cifrado .= chr(ord($c) + $offset - 26);
        } else {
            $msg_cifrado .= chr(ord($c) + $offset);
        }
      } else {
          $msg_cifrado .= " ";
      }
      $i++;
    }
    return $msg_cifrado;
}
function descifrar($str, $offset) {
    $msg_descifrado = "";
    $offset = $offset % 26;
    if($offset < 0) {
        $offset += 26;
    }
    $i = 0;
    while($i < strlen($str)) {
        $c = strtoupper($str{$i});
        if(($c >= "A") && ($c <= 'Z')) {
            if((ord($c) - $offset) < ord("A")) {
                $msg_descifrado .= chr(ord($c) - $offset + 26);
        } else {
            $msg_descifrado .= chr(ord($c) - $offset);
        }
      } else {
          $msg_descifrado .= " ";
      }
      $i++;
    }
    return $msg_descifrado;
}

//sample text
$operacion = $_POST['operacion'];
$clave = $_POST['clave'];
$mensaje = $_POST['$mensaje'];

$msg_cifrado = cifrar($mensaje, $clave);
echo $msg_cifrado;
echo "<br />";
echo descifrar($msg_cifrado, $clave);
?>
