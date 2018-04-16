<?php

// initialize variables
$pswd = "";
$code = "";
$error = "";
$valid = true;
$color = "#FF0000";

// if form was submit
if ($_SERVER['REQUEST_METHOD'] == "POST")
{
	// declare encrypt and decrypt funtions
	require_once('vigenere.php');
	
	// set the variables
	$pswd = $_POST['pswd'];
	$code = $_POST['code'];
	
	// check if password is provided
	if (empty($_POST['pswd']))
	{
		$error = "Please enter a password!";
		$valid = false;
	}
	
	// check if text is provided
	else if (empty($_POST['code']))
	{
		$error = "Please enter some text or code to encrypt or decrypt!";
		$valid = false;
	}
	
	// check if password is alphanumeric
	else if (isset($_POST['pswd']))
	{
		if (!ctype_alpha($_POST['pswd']))
		{
			$error = "Password should contain only alphabetical characters!";
			$valid = false;
		}
	}
	
	// inputs valid
	if ($valid)
	{
		// if encrypt button was clicked
		if (isset($_POST['encrypt']))
		{
			$code = encrypt($pswd, $code);
			$error = "Text encrypted successfully!";
			$color = "#526F35";
		}
			
		// if decrypt button was clicked
		if (isset($_POST['decrypt']))
		{
			$code = decrypt($pswd, $code);
			$error = "Code decrypted successfully!";
			$color = "#526F35";
		}
	}
}

?>

<html>
	<head>
		<title>XBCrypt - Vigenere Cipher</title>
		<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="style.css">
		<script type="text/javascript" src="Script.js"></script>
	</head>
	<body>
		<br><br><br>
		<form action="index.php" method="post">
			<table cellpadding="5" align="center" cellpadding="2" border="7">
				<caption><hr><b>XBCrypt - Text Cryption</b><hr></caption>
				<tr>
					<td align="center">Password: <input type="text" name="pswd" id="pass" value="<?php echo htmlspecialchars($pswd); ?>" /></td>
				</tr>
				<tr>
					<td align="center"><textarea id="box" name="code"><?php echo htmlspecialchars($code); ?></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" name="encrypt" class="button" value="Encode" onclick="validate(1)" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="decrypt" class="button" value="Decode" onclick="validate(2)" /></td>
				</tr>
				<tr>
					<td align="center">XBCrypt | By #XByte | Copyright &copy; 2015 | <span style="cursor:pointer;color:#0000FF" onclick="help()">help</span></td>
				</tr>
				<tr>
					<td><center><div style="color: <?php echo htmlspecialchars($color) ?>"><?php echo htmlspecialchars($error) ?></div></center></td>
				</tr>
			</table>
		</form>
	</body>
</html>