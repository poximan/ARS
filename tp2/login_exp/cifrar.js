let crypto;
try {
  crypto = require('crypto');
} catch (err) {
  console.log('crypto support is disabled!');
}

module.exports.cifrar = function(email, pass) {

  pass = email+pass;
  const hash = crypto.createHmac('sha256', pass)
                     .digest('hex');

  this.email = email;
  this.hash = hash;
}

module.exports.hash = function() {
  return this.hash;
}
