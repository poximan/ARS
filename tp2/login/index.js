let express = require('express')
let app = express()

let http = require('http').Server(app)

let io = require('socket.io')(http)
let path = require('path')

let storage_api = require("./storage-api")();
let cifrador = require("./cifrar")

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', function(req, res){
  res.sendFile(__dirname + '/public/index.html');
  console.log("pidiendo pagina");
});

io.on('connection', function(socket){
  console.log('cliente conectado');

  socket.on('login', function(usr, pass){

    cifrador.cifrar(usr, pass)
    let hash_pass = cifrador.hash;

    storage_api.existe(usr, hash_pass, (existe) => {
      if(existe){
        console.log("Usuario autenticado");        
        socket.emit("autenticado", {});
      }
      else
        console.log("Usuario no autenticado");
    })
  });

  socket.on('crear', function(nombre, usr, pass, nuevo_pass){

    cifrador.cifrar(usr, pass)
    let hash_pass = cifrador.hash;

    storage_api.guardar(usr, hash_pass, (creado) => {
      if(creado){
        console.log("Usuario creado");
        socket.emit("creado", {});
      }
      else
        console.log("Ya existe usuario con ese nombre");
    })
  });

  socket.on('disconnect', function(){
    console.log('usuario desconectado');
  });
});

let listener = http.listen(3000, function(){
    console.log('Escuchando puerto ' + listener.address().port);
});
