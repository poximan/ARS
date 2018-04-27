let express = require('express')
let app = express()

let http = require('http').Server(app)

let io = require('socket.io')(http)
let path = require('path')

let storage_api = require("./storage-api")
let cifrador = require("./cifrar")

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', function(req, res){
  res.sendFile(__dirname + '/public/index.html');
});

io.on('connection', function(socket){
  console.log('cliente conectado');

  socket.on('login', function(usr, pass){

    cifrador.cifrar(usr, pass)
    let hash_pass = cifrador.hash;

    if(storage_api.validar(usr, hash_pass))
      console.log("Usuario autenticado");
    else
      console.log("Usuario no autenticado");
  });

  socket.on('crear', function(nombre, usr, pass, nuevo_pass){

    cifrador.cifrar(usr, pass)
    let hash_pass = cifrador.hash;

    if(storage_api.guardar(usr, hash_pass))
      console.log("Usuario creado");
    else
      console.log("Ya existe usuario con ese nombre");
  });

  socket.on('disconnect', function(){
    console.log('usuario desconectado');
  });
});

let listener = http.listen(3000, function(){
    console.log('Escuchando puerto ' + listener.address().port);
});
