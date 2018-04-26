(function ($) {
  $(function () {
    var socket = io();
    $('form').submit(function(){
      socket.emit('login', $('#email').val(), $('#pass').val());      
      return false;
    });
  });
})(jQuery);
