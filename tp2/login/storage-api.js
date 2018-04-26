var MongoClient = require("mongodb").MongoClient;

var db_global;

exports.validar = function(usr, pass) {

  let doc;
  async function pedirDatos() {
    await console.log("entrando..");
    await let coleccion_obj = db_global.collection("colecc_login");
    await console.log("esto antes");
    return await doc = coleccion_obj.findOne( { usr: usr, pass: pass } );
  }
  pedirDatos();
  console.log(doc);
}

exports.guardar = function(usr, pass) {

  var coleccion_obj = db_global.collection("colecc_login");

  coleccion_obj.save(
    { fecha: new Date(), usr: usr, pass: pass }
  );
}

MongoClient.connect('mongodb://localhost:27017/bd_login', function(err, db) {

  if(err) throw err;

  var dbase = db.db("bd_login");
  dbase.createCollection("colecc_login");
  db_global = dbase;
});
