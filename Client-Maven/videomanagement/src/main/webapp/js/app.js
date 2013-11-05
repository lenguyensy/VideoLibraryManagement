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
	GENRES:['Action','Adventure','Adult','Animation','Comedy','Crime','Documentary','Drama','Fantasy','Family','Film-Noir','Horror','Musical','Mystery','Romance','Sci-Fi','Short','Thriller','War','Western']
};

var URL = {
	USER_CONTROLLER : "userajaxcontroller",
	MOVIE_CONTROLLER : "movieajaxcontroller",
	DASHBOARD_CONTROLLER : "dashboardajaxcontroller"
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

			return

		}
	}
})();


//util
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};