$(document).ready(function() {
    var hideDropdownTimeout;
    
    $('.link-ul li').mouseenter(function() {
        clearTimeout(hideDropdownTimeout);
        $(this).find('.dropdown').stop().slideDown(200); 
    });

    $('.link-ul li').mouseleave(function() {
        var dropdown = $(this).find('.dropdown');
        hideDropdownTimeout = setTimeout(function() {
            dropdown.stop().slideUp(200);
        }, 25);  
    });
});