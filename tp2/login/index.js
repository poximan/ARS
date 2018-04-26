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
  console.log('usuario conectado');

  socket.on('login', function(usr, pass){

    cifrador.cifrar(usr, pass)
    let hash_pass = cifrador.hash;
    storage_api.validar(usr, hash_pass)
  });

  socket.on('crear', function(usr, pass){

    cifrador.cifrar(usr, pass)
    let hash_pass = cifrador.hash;
    storage_api.guardar(usr, hash_pass)
  });

  socket.on('disconnect', function(){
    console.log('usuario desconectado');
  });
});

let listener = http.listen(3000, function(){
    console.log('Escuchando puerto ' + listener.address().port);
});
