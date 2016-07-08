sap.ui.controller("sap.ui.demo.myFiori.view.Master", {

	handleListItemPress : function (evt) {
		var context = evt.getSource().getBindingContext();
		this.nav.to("Detail", context);
	}
	
//	handleSearch : function (evt) {
//		var filters = [];
//		var query = evt.getParameter("query");
//		if(query && query.length > 0) {
//			var filter = new sap.ui.model.Filter("FriendId",
//					sap.ui.model.FilterOperator.Contains, query);
//			
//			filers.push(filter);
//		}
//		
//		var list = this.getView().byId("list");
//		var binding = list.getBinding("items");
//		binding.filter(filters);
//	}
//	handleListSelect : function(evt) {
//		var context = evt.getParameter("listItem").getBindingContext();
//		this.nav.to("Detail",context);
//	}
});