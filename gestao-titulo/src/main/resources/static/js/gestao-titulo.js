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

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	$('.js-datepicker').datepicker({
	    format: 'dd/mm/yyyy',                
	    language: 'pt-BR'
	});
	
	$('.js-atualizar-status').on('click', function(event) {
		
		//NAO FAZER O COMPARTAMENTO PADRAO DO LINK, NAO IR PARA O CONTROLER
		event.preventDefault();
		
		//console.log('click');
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		//console.log('urlReceber', urlReceber);
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		
		response.done(function(e) {
			var codigoTitulo = botaoReceber.data('codigo');
			$('[data-role=' + codigoTitulo + ']').html('<span class="badge badge-success">' + e + '</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro recebendo cobrança');
		});
				
	});
});





