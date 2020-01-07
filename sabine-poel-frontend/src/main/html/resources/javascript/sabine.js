function errore( button, element, message ) {
	
	doneButton( button );
	
	button.removeClass("btn-success");
	button.addClass("btn-danger");
	
	if ( message != undefined ) {
		element.html( message )	;
	}
	element.removeClass("text-success");
	element.addClass("text-danger");
	element.removeClass("hidden").addClass("show");
	
};

function succession( button, element, message ) {
	
	doneButton( button );
	button.removeClass("btn-danger");
	button.addClass("btn-success");
	
	if ( message != undefined ) {
		element.html( message )	;
	}
	
	element.removeClass("text-danger");
	element.addClass("text-success");
	
	if ( element != undefined ) {
		element.removeClass("hidden").addClass("show");
	}
	
};

var busyButton = function( btn, busyText ) { 
	
	var btnTextOriginal
		= btn.html();
	
	btn.attr("data-text-original", btnTextOriginal );
	btn.prop( "disabled", "disabled" );
	if ( ! busyText ) {
		busyText = "Even geduld...";
	}
	btn.html( "<i class=\"fa fa-cog fa-spin\"></i>&nbsp;&nbsp;" + busyText );
		
};

var doneButton = function( btn ) { 
	
	var btnTextOriginal
		= btn.attr("data-text-original");
	
	btn.prop( "disabled", false );
	btn.html( btnTextOriginal );
		
};

function clearError() {
	
	$jq(".error").removeClass("show").addClass("hidden");
	
}

function clearStatus() {
	$jq(".status").removeClass("show").addClass("hidden");
}