let crypto;
try {
  crypto = require('crypto');
} catch (err) {
  console.log('crypto support is disabled!');
}

module.exports.cifrar = function(usr, pass) {

  pass = usr+pass;
  const hash = crypto.createHmac('sha256', pass)
                     .digest('hex');

  this.usr = usr;
  this.hash = hash;
}

module.exports.hash = function() {
  return this.hash;
}
