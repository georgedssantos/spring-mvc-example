(function () {  
	'use strict';  
	window.addEventListener('load', function () {  
		var form = document.getElementById('titulo-validation');  
          form.addEventListener('submit', function (event) {  
              if (form.checkValidity() === false) {  
                  event.preventDefault();  
                  event.stopPropagation();  
               }  
               form.classList.add('was-validated');  
          }, false);  
    }, false);  
})();

$("#success-alert").fadeTo(6000, 1500).slideUp(1500, function(){
    $("#success-alert").slideUp(1500);
});

$('#idModalConfirmacaoExclusao').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) //BOTÃO QUE ACIONOU O MODAL
  
  var codigoTitulo = button.data('codigo'); //EXTRAI INFORMAÇÃO DOS ATRIBUTOS data-*
  //alert(codigoTitulo);
  var descricaoTitulo = button.data('descricao');
	
  //ATUALIZA O CONTEÚDO DO MODAL.
  //var modal = $(this)
  //modal.find('.modal-title').text('Nova mensagem para ' + recipient)
  //modal.find('.modal-body input').val(recipient)
  
  var modal = $(this); 
  var form = modal.find('form');

  var action = form.data('url-base');
  if (!action.endsWith('/')) {
		action += '/';
  }
  
  form.attr('action', action + codigoTitulo);
  
  modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
  
  //alert(form.attr('action'));
  
});

