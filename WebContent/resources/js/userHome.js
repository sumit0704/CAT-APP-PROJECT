
$( document ).ready(function() {
	
	$('ul.nav li.dropdown').hover(function() {
		  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(200);
		}, function() {
		  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(200);
		});
	
	$(document).on('mouseenter', '.aa', function () {
        $(this).find('.aa').show();
    }).on('mouseleave', '.aa', function () {
        $(this).find('.aa').hide();
    });
	
	
	$('#show_paginator').bootpag({
	      total: 10,
	      page: 3,
	      maxVisible: 10
	}).on('page', function(event, num)
	{
	     $("#flatFiles").html("Page " + num); 
	});

	$("li").click(function(){
		  // If this isn't already active
		  if (!$(this).hasClass("active")) {
		    // Remove the class from anything that is active
		    $("li.active").removeClass("active");
		    // And make this active
		    $(this).addClass("active");
		  }
		});
	
	$(document).ready(function(){
	    $('.check:button').toggle(function(){
	        $('input:checkbox').attr('checked','checked');
	        $(this).val('uncheck all')
	    },function(){
	        $('input:checkbox').removeAttr('checked');
	        $(this).val('check all');        
	    })
	})
	
	/*var $pageItem = $(".pagination li")
	$pageItem.click(function(){
        console.log($(this).html().indexOf('Next'));
        if($(this).html().indexOf('Next') <= -1 && $(this).html().indexOf('Previous') <= -1){
        $pageItem.removeClass("active");
        $(this).addClass("active");
          }
      });
	
	
	$('#allFiles').easyPaginate({
	    paginateElement: 'input',
	    elementsPerPage: 1,
	    effect: 'climb'
	});
	$('#flatFiles').easyPaginate({
	    paginateElement: 'input',
	    elementsPerPage: 1,
	    effect: 'climb'
	});*/
	
	
	
	
});

