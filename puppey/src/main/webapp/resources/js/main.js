(function($) {
	
/*
 * Global WellPicked object
 */
var WellPicked = {
	plugins: {},
	init: function() {
		for (var property in this.plugins) {
		    if (this.plugins.hasOwnProperty(property)) {
		        this.plugins[property]();
		    }
		}
	}
};

/*
 * Navigation
 */
WellPicked.plugins.navigation = function() {
	if($('#sidebar > .wrapper').length && $.fn.perfectScrollbar) {
	    $('#sidebar > .wrapper').perfectScrollbar({
	        "scrollYMarginOffset": 20,
	        "scrollXMarginOffset": 20
	    });
	}
};

/*
 * Form validation
 */
WellPicked.plugins.form = function() {
	var forms = '[data-wp-form]',
		formEvent = 'submit.wp',
		inputs = '[data-wp-form-pattern]',
		inputEvent = 'change.wp',
		inputContainerSelector = '.input-container',
		pattern = 'data-wp-form-pattern',
		error = 'data-wp-form-error',
		errorClass = 'has-error',
		errorSelector = '.help-error';
	
	var formHandler = function(event) {
		var valid = true;
		$(inputs, this).each(function() {
			var $container = ($(this).closest(inputContainerSelector).length ? $(this).closest(inputContainerSelector) : $(this).parent()),
				$error = $container.find(errorSelector);
			
			if( !$(this).val().match(new RegExp($(this).attr(pattern))) ) {
				$container.addClass(errorClass);
				$error.html($(this).attr(error));
				valid = false;
			} else {
				$container.removeClass(errorClass);
				$error.html("");
			}
		});
		return valid;
	};
		
	var inputHandler = function(event) {
		var $container = ($(this).closest(inputContainerSelector).length ? $(this).closest(inputContainerSelector) : $(this).parent()),
			$error = $container.find(errorSelector);
		
		$container.removeClass(errorClass);
		$error.html("");		
	};
	
	$(forms).on(formEvent, formHandler);
	$(inputs).on(inputEvent, inputHandler);
};

/*
 * Initializing
 */
WellPicked.init();

})(jQuery);