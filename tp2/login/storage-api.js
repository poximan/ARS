var MongoClient = require("mongodb").MongoClient;

var db_global;

exports.validar = function(usr, pass) {

  let doc;

  function conectar() {
    return new Promise(resolve => {
      resolve(db_global.collection("colecc_login"));
    });
  }

  function buscar(coleccion_obj, usr, pass) {
    return new Promise(resolve => {
      resolve(coleccion_obj.findOne( { usr: usr, pass: pass } ));
    });
  }

  async function funcSync() {
    let coleccion_obj = await conectar();
    doc = await buscar(coleccion_obj, usr, pass);
    await console.log(doc);
    return await (doc == null)? false : true;
  }
  funcSync();
}

exports.guardar = function(usr, pass) {

  var coleccion_obj = db_global.collection("colecc_login");

  if(!exports.validar(usr, pass)){
    const res = coleccion_obj.save(
      { fecha: new Date(), usr: usr, pass: pass }
    );
    return true
  }
  else
    return false
}

MongoClient.connect('mongodb://localhost:27017/bd_login', function(err, db) {

  if(err) throw err;

  var dbase = db.db("bd_login");
  dbase.createCollection("colecc_login");
  db_global = dbase;
});
