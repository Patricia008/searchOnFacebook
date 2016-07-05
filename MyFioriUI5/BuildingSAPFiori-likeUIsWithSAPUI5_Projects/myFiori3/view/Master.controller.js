jQuery.sap.require("sap.ui.demo.myFiori.util.Formatter");

sap.ui.controller("sap.ui.demo.myFiori.view.Master", {

	handleListItemPress : function (evt) {
		var context = evt.getSource().getBindingContext();
		this.nav.to("Detail", context);
	}
});