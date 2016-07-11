sap.ui.controller("sap.ui.demo.myFiori.view.Master", {

	handleListItemPress : function (evt) {
		var context = evt.getSource().getBindingContext();
		this.nav.to("Detail", context);
	},
	
		handleSearch : function (evt) {      
				// create model filter   
			var filters = [];   
			var query = evt.getParameter("query"); 
			
			if (query && query.length > 0) {    
				var filter1 = new sap.ui.model.Filter("FriendName", sap.ui.model.FilterOperator.Contains, query);
				var filter2 = new sap.ui.model.Filter("FriendId", sap.ui.model.FilterOperator.Contains, query);
				var filter3 = new sap.ui.model.Filter("FriendWorkPlace", sap.ui.model.FilterOperator.Contains, query);
				var allFilters = new sap.ui.model.Filter([filter1, filter2,filter3],false);
				filters.push(allFilters);   
				}     
			
				// update list binding   
			var list = this.getView().byId("list");   
			var binding = list.getBinding("items");
			binding.filter(filters);  
		}
	
	//handleListSelect : function (evt) {   
		//var context = evt.getParameter("listItem").getBindingContext();   
		//this.nav.to("Detail", context);  
	//}
});