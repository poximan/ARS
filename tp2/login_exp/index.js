
var bodyParser = require('body-parser');
var routes = require('./routes/router')();

let router = routes.router;
let express = routes.express;
let app = routes.app;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.use(express.static(__dirname + '/public'));
app.use('/', router);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  var err = new Error('File Not Found');
  err.status = 404;
  next(err);
});

// error handler
// define as the last app.use callback
app.use(function (err, req, res, next) {
  res.status(err.status || 500);
  res.send(err.message);
});

let listener = app.listen(3000, function(){
  console.log('Escuchando puerto ' + listener.address().port);
});
