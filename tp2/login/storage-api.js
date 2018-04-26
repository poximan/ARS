var MongoClient = require("mongodb").MongoClient;

var db_global;

exports.guardar = function(usr, pass) {

  var coleccion_obj = db_global.collection("colecc_docker");

  coleccion_obj.save(
    { fecha: new Date(), usr: usr, pass: pass }
  );
}

MongoClient.connect('mongodb://mongo:27017/bd_login', function(err, db) {

  if(err) throw err;

  var dbase = db.db("bd_login");
  dbase.createCollection("colecc_login");
  db_global = dbase;
});
