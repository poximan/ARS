var MongoClient = require("mongodb").MongoClient

module.exports = function(){

  var module = {};

  let coleccion;

  module.existe = function(email, pass, callback) {

    (async() => {
      let doc = await coleccion.findOne( {email: email, pass: pass} )
      callback((doc == null) ? false : true)
    })()
  }

  module.enUso = function(email, callback) {

    (async() => {
      let doc = await coleccion.findOne( {email: email} )
      callback((doc == null)? false : true)
    })()
  }

  module.guardar = function(email, pass, callback) {

    module.enUso(email, (existe) => {
      console.log(coleccion);

      if(!existe)
        coleccion.save( { fecha: new Date(), email: email, pass: pass } );

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
