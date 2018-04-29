var MongoClient = require("mongodb").MongoClient
const util = require("util")

module.exports = function(){

  var module = {};

  let coleccion;

  module.existe = function(usr, pass, callback) {

    (async() => {
      let doc = await coleccion.findOne( {usr: usr, pass: pass} )
      callback((doc == null) ? false : true)
    })()
  }

  module.enUso = function(usr, callback) {

    (async() => {
      let doc = await coleccion.findOne( {usr: usr} )
      callback((doc == null)? false : true)
    })()
  }

  module.guardar = function(usr, pass, callback) {

    module.enUso(usr, (existe) => {

      if(!existe)
        coleccion.save( { fecha: new Date(), usr: usr, pass: pass } );

      callback((existe)? false : true)
    })
  }

  MongoClient.connect('mongodb://localhost:27017/bd_login', function(err, db) {

    if(err) throw err;

    var dbase = db.db("bd_login");
    dbase.createCollection("colecc_login", () => {
      coleccion = dbase.collection("colecc_login")
    });
  });

  return module;
}
