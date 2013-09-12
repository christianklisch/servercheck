/**
 * jquery functions 4 webinterface
 * 
 * @author Christian Klisch
 * 
 *         License:
 * 
 *         This program is free software; you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License version 2 as
 *         published by the Free Software Foundation.
 * 
 *         This program is distributed in the hope that it will be useful, but
 *         WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *         General Public License for more details.
 * 
 *         You should have received a copy of the GNU General Public License
 *         along with this program; if not, write to the Free Software
 *         Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

$(document).ready(function() {	
	
		$.ajaxSetup({
		    cache: false
		});	
		
		$('.taskviewform').ajaxForm({
			success : function() {
				//alert("Send");
			}
		});
		
		$('.taskexecform').ajaxForm({
			success : function() {
				//alert("Send");
			}
		});		
		
		$('.taskparameterform').ajaxForm({
			success : function() {
				//alert("Send");
			}
		});		
		
		setInterval(function(){ 
			var refreshId = $.getJSON("/json",   
			        function (data) {
			            $.each(data , function(i,val){		
			            	if($('#lastTime_' + val.idText).html() != val.lastTimeText)
			            		 $('#lastTime_' + val.idText).hide().html(val.lastTimeText).fadeIn('fast');
			            	if($('#lastDate_' + val.idText).html() != val.lastDateText)
			            		 $('#lastDate_' + val.idText).hide().html(val.lastDateText).fadeIn('fast');
			            	if($('#statusImage_' + val.idText) != val.statusImageHTML)
			            		 $('#statusImage_' + val.idText).replaceWith(val.statusImageHTML);
			            	if($('#lastResult_' + val.idText) != val.lastResultText)
			            		 $('#lastResult_' + val.idText).hide().html(val.lastResultText).fadeIn('fast');
			            });
			        }); 
		}, 
		7500);		
		
		 $( ".taskdialog" )
		 .button()
		 .click(function() {
			 $( "#dialog-form_" + $(this).prev('input[type=hidden]').attr('value')).dialog( "open" );
			 return false;
		 });		
		
		 
		 $( ".dialog-form" ).dialog({
			 autoOpen: false,
			 height: 250,
			 width: 350,
			 modal: true,
			 buttons: {
				 "Execute": function() {
				 //alert($(this).find('.parameterP').val());
				 //alert($(this).find('.processP').attr('value'));
				 
				 var frm = $("#form_" + $(this).find('.parameterP').attr('value'));
				 
		         $.ajax({
		            type: 'POST',
		            url: frm.attr('action'),
		            data: {
		            	process : $(this).find('.processP').attr('value'),
		            	parameter : $(this).find('.parameterP').val()
		            },
		            success: function (data) {
		                //alert('ok');
		            }
		         });				 
				 
				 $( this ).dialog( "close" );
				 return false;
				 },
					 Cancel: function() {
						 $( this ).dialog( "close" );
					 }
				 },
					 close: function() {
					 }
			 });
		 
	});

