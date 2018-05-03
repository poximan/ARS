let express = require('express')
let app = express()

var router = express.Router()

let storage_api = require("../storage-api")()
let cifrador = require("../cifrar")

module.exports = function(){

  var module = {};

  module.router = router;
  module.express = express;
  module.app = app;

  // GET route for reading data
  router.get('/', function (req, res, next) {
    console.log("pidiendo pagina");
    return res.sendFile(path.join(__dirname + '/public/index.html'));
  })

  //POST route for updating data
  router.post('/', function (req, res, next) {

    // confirm that user typed same password twice
    if (req.body.password !== req.body.passwordConf) {
      var err = new Error("Contraseña y verificacion no son iguales");
      err.status = 400;
      res.send("Contraseña y verificacion no son iguales");
      return next(err);
    }

    if (req.body.email &&
      req.body.password &&
      req.body.passwordConf) {

      cifrador.cifrar(req.body.email, req.body.password)
      let hash_pass = cifrador.hash;

      storage_api.guardar(req.body.email, hash_pass, (creado) => {
        if(creado){
          console.log("Usuario creado");
          return res.redirect('/profile');
        }
        else{
          console.log("Ya existe usuario con ese email");
          return next(null);
        }
      })

    } else if (req.body.logemail && req.body.logpassword) {

      cifrador.cifrar(req.body.logemail, req.body.logpassword)
      let hash_pass = cifrador.hash;

      storage_api.existe(req.body.logemail, hash_pass, (existe) => {
        if(existe){
          console.log("Usuario autenticado");
          return res.redirect('/profile');
        }
        else{
          console.log("Usuario no autenticado");
          var err = new Error("Usuario no autenticado");
          err.status = 401;
          return next(err)
        }
      })
    } else {
      var err = new Error('All fields required.');
      err.status = 400;
      return next(err)
    }
  })

  // GET route after registering
  router.get('/profile', function (req, res, next) {

    cifrador.cifrar(req.body.logemail, req.body.logpassword)
    let hash_pass = cifrador.hash;

    storage_api.existe(req.body.logemail, hash_pass, (existe) => {
      if(existe){
        return res.send('<h2>Mail: </h2>' + req.body.logemail + '<br><a type="button" href="/logout">Logout</a>')
      }
      else{
        console.log("Usuario no autenticado");
        var err = new Error("Usuario no autenticado");
        err.status = 400;
        return next(err);
      }
    })
  })

  // GET for logout logout
  router.get('/logout', function (req, res, next) {
    if (req.session) {
      // delete session object
      req.session.destroy(function (err) {
        if (err) {
          return next(err);
        } else {
          return res.redirect('/');
        }
      })
    }
  })

  return module;
}
