$(document).ready(function() {
    var hideDropdownTimeout;
    
    $('.link-ul li').mouseenter(function() {
        clearTimeout(hideDropdownTimeout);
        $(this).find('.dropdown').stop().slideDown(100); 
    });

    $('.link-ul li').mouseleave(function() {
        var dropdown = $(this).find('.dropdown');
        hideDropdownTimeout = setTimeout(function() {
            dropdown.stop().slideUp(100);
        }, 25);  
    });
});