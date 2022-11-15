var search_delay;
function FastSearch(elem,type,game) {
	if(!type) var type = "";
	if(!game) var game = 0;
	if(!elem) return false;
    $(elem).blur(function() {
        $("#searchsuggestions").fadeOut()
    });
    $(elem).keyup(function() {
        var a = $(this).val();
        0 == a.length ? $("#searchsuggestions").fadeOut() : 1 < a.length && (clearInterval(search_delay), search_delay = setInterval(function() {
            do_search(a,elem,type,game);
        }, 600))
    })
}

function do_search(a,elem,type, game) {
	if(!type) var type = "";
	if(!game) var game = 0;
    clearInterval(search_delay);
    $("#searchsuggestions").remove();
    $("body").append("<div id='searchsuggestions' style='display:none'><div></div></div>");

    $.post( "/engine/ajax/search.php", {
        query: a,
		go: type,
		game: game
    }, function(a) {
        $("#searchsuggestions > div").html(a);
		$("#searchsuggestions").fadeIn().css({
			position:'absolute',
			'z-index':'2',
			top:($(elem).offset().top + $(elem).outerHeight()),
			width:$(elem).innerWidth(),
			left:$(elem).offset().left
		});
    });
}
