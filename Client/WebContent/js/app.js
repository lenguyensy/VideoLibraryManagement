var ENUM = {
	STATE : [ {
		name : 'ALABAMA',
		abbreviation : 'AL'
	}, {
		name : 'ALASKA',
		abbreviation : 'AK'
	}, {
		name : 'AMERICAN SAMOA',
		abbreviation : 'AS'
	}, {
		name : 'ARIZONA',
		abbreviation : 'AZ'
	}, {
		name : 'ARKANSAS',
		abbreviation : 'AR'
	}, {
		name : 'CALIFORNIA',
		abbreviation : 'CA'
	}, {
		name : 'COLORADO',
		abbreviation : 'CO'
	}, {
		name : 'CONNECTICUT',
		abbreviation : 'CT'
	}, {
		name : 'DELAWARE',
		abbreviation : 'DE'
	}, {
		name : 'DISTRICT OF COLUMBIA',
		abbreviation : 'DC'
	}, {
		name : 'FEDERATED STATES OF MICRONESIA',
		abbreviation : 'FM'
	}, {
		name : 'FLORIDA',
		abbreviation : 'FL'
	}, {
		name : 'GEORGIA',
		abbreviation : 'GA'
	}, {
		name : 'GUAM',
		abbreviation : 'GU'
	}, {
		name : 'HAWAII',
		abbreviation : 'HI'
	}, {
		name : 'IDAHO',
		abbreviation : 'ID'
	}, {
		name : 'ILLINOIS',
		abbreviation : 'IL'
	}, {
		name : 'INDIANA',
		abbreviation : 'IN'
	}, {
		name : 'IOWA',
		abbreviation : 'IA'
	}, {
		name : 'KANSAS',
		abbreviation : 'KS'
	}, {
		name : 'KENTUCKY',
		abbreviation : 'KY'
	}, {
		name : 'LOUISIANA',
		abbreviation : 'LA'
	}, {
		name : 'MAINE',
		abbreviation : 'ME'
	}, {
		name : 'MARSHALL ISLANDS',
		abbreviation : 'MH'
	}, {
		name : 'MARYLAND',
		abbreviation : 'MD'
	}, {
		name : 'MASSACHUSETTS',
		abbreviation : 'MA'
	}, {
		name : 'MICHIGAN',
		abbreviation : 'MI'
	}, {
		name : 'MINNESOTA',
		abbreviation : 'MN'
	}, {
		name : 'MISSISSIPPI',
		abbreviation : 'MS'
	}, {
		name : 'MISSOURI',
		abbreviation : 'MO'
	}, {
		name : 'MONTANA',
		abbreviation : 'MT'
	}, {
		name : 'NEBRASKA',
		abbreviation : 'NE'
	}, {
		name : 'NEVADA',
		abbreviation : 'NV'
	}, {
		name : 'NEW HAMPSHIRE',
		abbreviation : 'NH'
	}, {
		name : 'NEW JERSEY',
		abbreviation : 'NJ'
	}, {
		name : 'NEW MEXICO',
		abbreviation : 'NM'
	}, {
		name : 'NEW YORK',
		abbreviation : 'NY'
	}, {
		name : 'NORTH CAROLINA',
		abbreviation : 'NC'
	}, {
		name : 'NORTH DAKOTA',
		abbreviation : 'ND'
	}, {
		name : 'NORTHERN MARIANA ISLANDS',
		abbreviation : 'MP'
	}, {
		name : 'OHIO',
		abbreviation : 'OH'
	}, {
		name : 'OKLAHOMA',
		abbreviation : 'OK'
	}, {
		name : 'OREGON',
		abbreviation : 'OR'
	}, {
		name : 'PALAU',
		abbreviation : 'PW'
	}, {
		name : 'PENNSYLVANIA',
		abbreviation : 'PA'
	}, {
		name : 'PUERTO RICO',
		abbreviation : 'PR'
	}, {
		name : 'RHODE ISLAND',
		abbreviation : 'RI'
	}, {
		name : 'SOUTH CAROLINA',
		abbreviation : 'SC'
	}, {
		name : 'SOUTH DAKOTA',
		abbreviation : 'SD'
	}, {
		name : 'TENNESSEE',
		abbreviation : 'TN'
	}, {
		name : 'TEXAS',
		abbreviation : 'TX'
	}, {
		name : 'UTAH',
		abbreviation : 'UT'
	}, {
		name : 'VERMONT',
		abbreviation : 'VT'
	}, {
		name : 'VIRGIN ISLANDS',
		abbreviation : 'VI'
	}, {
		name : 'VIRGINIA',
		abbreviation : 'VA'
	}, {
		name : 'WASHINGTON',
		abbreviation : 'WA'
	}, {
		name : 'WEST VIRGINIA',
		abbreviation : 'WV'
	}, {
		name : 'WISCONSIN',
		abbreviation : 'WI'
	}, {
		name : 'WYOMING',
		abbreviation : 'WY'
	} ],
	USER_TYPE : {
		premimum : "Premimum Member",
		simple : "Simple Customer"
	},
	MONTHLY_FEE : {
		15 : "$ 15",
		30 : "$ 30"
	},
	GENRES : [ 'Action', 'Adventure', 'Adult', 'Animation', 'Comedy', 'Crime',
			'Documentary', 'Drama', 'Fantasy', 'Family', 'Film-Noir', 'Horror',
			'Musical', 'Mystery', 'Romance', 'Sci-Fi', 'Short', 'Thriller',
			'War', 'Western' ]
};

var URL = {
	USER_CONTROLLER : "userajaxcontroller.jsp",
	MOVIE_CONTROLLER : "movieajaxcontroller.jsp",
	DASHBOARD_CONTROLLER : "dashboardajaxcontroller.jsp",
	ANON_CONTROLLER : "anonAjaxController.jsp"
}



var NavUtil = (function() {
	return {
		init : function(selNav) {
			$(selNav).addClass('active');
		}
	}
})();

var FormUtil = (function() {
	return {
		emptyHandler : function() {
			var val = $(this).val(), isEmpty = val.trim() == "";

			$(this).closest('.control-group').toggleClass('error', isEmpty);
			$(this).next().toggle(isEmpty);

			// bind tabout to remove error stuffs
			$(this).on(
					"change",
					function() {
						var val = $(this).val(), isEmpty = val.trim() == "";
						$(this).closest('.control-group').toggleClass('error',
								isEmpty);
						$(this).next().toggle(isEmpty);
					});

			return isEmpty;
		},
		populateUserForm : function() {
			// populate user type
			for ( var k in ENUM.USER_TYPE) {
				if (ENUM.USER_TYPE.hasOwnProperty(k))
					$('#userType').append(
							'<option>' + ENUM.USER_TYPE[k] + '</option>');
			}

			// populate states
			for ( var k in ENUM.STATE) {
				if (ENUM.STATE.hasOwnProperty(k))
					$('#state').append(
							'<option value="' + ENUM.STATE[k].abbreviation
									+ '">' + ENUM.STATE[k].name + '</option>');
			}

			// populate fee
			for ( var k in ENUM.MONTHLY_FEE) {
				if (ENUM.MONTHLY_FEE.hasOwnProperty(k))
					$('#monthlySubscriptionFee').append(
							'<option value="' + k + '">' + ENUM.MONTHLY_FEE[k]
									+ '</option>');
			}

			// change user type show different fields.
			$('#userType')
					.change(
							function() {
								var isPremium = $(this).find(':selected').val() == ENUM.USER_TYPE.premimum;
								$('#frmUser').find('.premium')
										.toggle(isPremium);
								$('#frmUser').find('.simple')
										.toggle(!isPremium);

								$('#totalOutstandingMoviesSpan').html(
										isPremium ? "10" : "2");

								if ($('#totalOutstandingMovies').val().length == 0)
									$('#totalOutstandingMovies').val(
											isPremium ? "10" : "2");
							});
		}
	}
})();

var CommonUtil = (function() {
	return {
		showMessage : function(msg, task) {
			msg = $.trim(msg);
			if (msg === "true")
				msg = task + " Succeeds";

			$('#msg').html(msg).show(1000);
			setTimeout(function() {
				$('#msg').hide(1000);
			}, 5000);
		}
	}
})();

// util
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

(function($) {
	/*
	 * Author: Sy Le
	 * 
	 * button states btn disable / enable utitlity
	 * 
	 * touse simply call $(selector).btnDisable
	 */

	var attrDisabled, confirmBoxId, cssDisabled, hashConfirmBox, hashMsgBox, msgBoxId;
	cssDisabled = "disabled";
	attrDisabled = "disabled";
	$.fn.btnDisable = function() {
		return this.each(function() {
			return $(this).addClass(cssDisabled).attr(attrDisabled, true);
		});
	};
	$.fn.btnEnable = function() {
		return this.each(function() {
			return $(this).removeClass(cssDisabled).removeAttr(attrDisabled);
		});
	};
	$.fn.btnIsDisabled = function() {
		var isDisabled;
		isDisabled = false;
		this.each(function() {
			if ($(this).hasClass(cssDisabled)) {
				return isDisabled = true;
			}
		});
		return isDisabled;
	};
	/*
	 * Author: Sy Le
	 * 
	 * wrapper function for validation jqBootstrapValidation
	 */

	$.fn.formValidation = function() {
		return this
				.each(function() {
					var $frm;
					$frm = $(this);
					return $frm.find('input, select, textarea')
							.jqBootstrapValidation();
				});
	};
	$.fn.formValidate = function() {
		var invalidCount;
		invalidCount = 0;
		this.each(function() {
			var $frm;
			$frm = $(this);
			if ($frm.find('input, select, textarea').jqBootstrapValidation(
					'hasErrors')) {
				$frm.submit();
				return invalidCount++;
			}
		});
		return invalidCount === 0;
	};
	$.fn.triggerCrossInputValidation = function() {
		return this.each(function() {
			return $(this).val((new Date()).getTime()).change();
		});
	};
	/*
	 * Author: Sy Le
	 * 
	 * msg box (bootstrap modal), abstract away the call to create a modal. to
	 * use do this $.msgBox({header:'sample header', body: 'sample body',
	 * footer: 'sample footer'})
	 */

	hashMsgBox = {};
	msgBoxId = 0;
	$.msgBox = function(conf) {
		var dom, domId, icon = '';
		conf = $
				.extend(
						{
							header : "",
							body : "",
							footer : "<button class=\"btn btn-primary\" data-dismiss=\"modal\">OK</button>",
							id : "msgBox" + (++msgBoxId),
							type : 'info'
						}, conf);
		domId = conf.id;

		switch (conf.type) {
		case 'loading':
			icon = common.loading;
			break;
		}

		$("body")
				.append(
						"<div id=\""
								+ domId
								+ "\" class=\"msgBox modal hide fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" data-backdrop=\"static\" data-keyboard=\"false\">"
								+ "<div class=\"modal-header\">" + "<h3>"
								+ conf.header + "</h3>" + "</div>"
								+ "<div class=\"modal-body\">" + "<p>" + icon
								+ " " + conf.body + "</p>" + "</div>"
								+ "<div class=\"modal-footer\">" + conf.footer
								+ "</div>" + "</div>");
		dom = $("#" + domId);
		dom.modal("show");
		dom.on("hidden", function() {
			dom.remove();
			return delete hashMsgBox[domId];
		});
		hashMsgBox[domId] = dom;
		return dom;
	};
	/*
	 * Author: Sy Le
	 * 
	 * confirmation box. a shortcut to generate confirmation dialog with 2
	 * buttons. to call, simply use
	 * 
	 * $.confirmBox({header:'confirmation?', body: 'do you want to do this?',
	 * btnPrimary:{ text:'Yes', cb : function(){return true;}} })
	 * 
	 * or you can look at account.coffee and search for disableEnablekey to see
	 * actual usage
	 */

	hashConfirmBox = {};
	confirmBoxId = 0;
	$.confirmBox = function(conf) {
		var dom, domId;
		conf = $.extend({
			id : "confirmBox" + (++confirmBoxId),
			header : "Confirmation?",
			body : "",
			btnPrimary : {
				text : "OK",
				cb : function(e) {
					/*
					 * this var is referred to the clicked primary button
					 * 
					 * return values: $.Deferred().resolve() to close the dialog
					 * $.Deferred().fail() to keep the dialog open
					 */

					return $.Deferred().resolve();
				}
			},
			btnSecondary : {
				text : "Cancel",
				cb : function() {
				}
			}
		}, conf);
		domId = conf.id;
		$("body")
				.append(
						"<div id=\""
								+ domId
								+ "\" class=\"msgBox modal hide fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" data-backdrop=\"static\" data-keyboard=\"false\">"
								+ "<div class=\"modal-header\">"
								+ "<h3>"
								+ conf.header
								+ "</h3>"
								+ "</div>"
								+ "<div class=\"modal-body\">"
								+ "<p>"
								+ conf.body
								+ "</p>"
								+ "</div>"
								+ "<div class=\"modal-footer\">"
								+ "<a class=\"btn btn-primary\">"
								+ conf.btnPrimary.text
								+ "</a><button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" aria-hidden=\"true\">"
								+ conf.btnSecondary.text + "</button></div>"
								+ "</div>");
		dom = $("#" + domId);
		hashConfirmBox[domId] = $("#" + domId).modal("show").on("hidden",
				function() {
					dom.remove();
					return delete hashConfirmBox[domId];
				});
		dom.find(".btn-primary").click(function(e) {
			var dfdConfirm;
			dfdConfirm = conf.btnPrimary.cb.apply(this, arguments);
			if (dfdConfirm === false) {
				dfdConfirm = $.Deferred();
			}
			if (dfdConfirm === true) {
				dfdConfirm = $.Deferred().resolve();
			}
			return dfdConfirm.done(function() {
				return dom.modal("hide");
			});
		});
		dom.find(".btn-secondary").click(function() {
			return conf.btnSecondary.cb.apply(this, arguments);
		});
		return hashConfirmBox[domId];
	};
	/*
	 * Author: Sy Le
	 * 
	 * loading indicator (show the spinning wheel when there is some background
	 * running) to show it $.loadingIndicator.show()
	 * 
	 * to hide it $.loadingIndicator.hide()
	 */

	$.loadingIndicator = (function() {
		var init = function() {
			if ($("#loadingIndicator").length === 0) {
				$("body")
						.append(
								"<div id=\"loadingIndicator\"><div class=\"ajax-loader\"></div></div>");
				$(document).bind("mousemove", function(e) {
					return $("#loadingIndicator").css({
						top : e.pageY + 10,
						left : e.pageX + 10,
						position : "absolute",
						'z-index' : 999999
					});
				});
			}
		};

		return {
			show : function() {
				init();
				$("#loadingIndicator").show();
			},
			hide : function() {
				init();
				$("#loadingIndicator").hide();
			}
		}
	})();
})(jQuery);

// override alert
window.alert = function(msg) {
	$.msgBox({
		header : "Information",
		body : msg
	})
}

// style
$(document).delegate('.table tr', 'mouseover', function() {
	$(this).addClass('info');
}).delegate('.table tr', 'mouseout', function() {
	$(this).removeClass('info');
}).delegate('.table tr', 'click', function() {
	$(this).siblings().removeClass('success');
	$(this).addClass('success');
}).delegate('.table tr', 'dblclick', function() {
	$(this).find('.icon-edit').click();
});