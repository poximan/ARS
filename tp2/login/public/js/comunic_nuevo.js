(function ($) {
  $(function () {
    var socket = io();
    $('form').submit(function(){
      socket.emit('crear', $('#nuevo_nombre').val(), $('#nuevo_email').val(), $('#nuevo_pass').val(), $('#nuevo_pass_confirm').val());
      return false;
    });
  });
})(jQuery);
