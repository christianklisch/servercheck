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
		$('.taskform').ajaxForm({
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
			            		 $('#lastDate_' + val.idText).html(val.lastDateText);
			            	if($('#statusImage_' + val.idText) != val.statusImageHTML)
			            		 $('#statusImage_' + val.idText).replaceWith(val.statusImageHTML);
			            	if($('#lastResult_' + val.idText) != val.lastResult)
			            		 $('#lastResult_' + val.idText).html(val.lastResult);
			            });
			        }); 
		}, 
		7500);		
		
	});

