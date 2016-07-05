jQuery.sap.require("sap.ui.demo.myFiori.util.Formatter");

jQuery.sap.require("sap.ui.demo.myFiori.util.Grouper");

sap.ui.controller("sap.ui.demo.myFiori.view.Master", {

	handleListItemPress : function (evt) {
		var context = evt.getSource().getBindingContext();
		this.nav.to("Detail", context);
	},
	
/*	handleSearch : function (evt) {
		
		//Creating a model filter
		var filters =[];
		var query = evt.getParameter("query");
		if(query && query.length > 0) {
			var filter = new sap.ui.model.Filter ("SoId", sap.model.FilterOperator.Contains, query);
			filters.push(filter);
		}
		//Updating list binding
		var list = this.getView().byId("List");
		var binding = list.getBinding("items");
		binding.filter(filters);	
	}*/
	
	handleSearch : function (evt) {      
		// create model filter   
		var filters = [];   
		var query = evt.getParameter("query");   
		if (query && query.length > 0) {    
			var filter = new sap.ui.model.Filter("SoId", sap.ui.model.FilterOperator.Contains, query);    
			filters.push(filter);   
			}      
		// update list binding   
		var list = this.getView().byId("List");   
		var binding = list.getBinding("items");   
		binding.filter(filters);  
	},
	
	handleListSelect : function (evt) {   
		var context = evt.getParameter("listItem").getBindingContext();   
		this.nav.to("Detail", context);  
		},
		
		handleGroup : function (evt) {  
			
			// compute sorters   
			var sorters = [];   
			var item = evt.getParameter("selectedItem");   
			var key = (item) ? item.getKey() : null;   
			if ("GrossAmount" === key || "LifecycleStatus" === key) {    
				sap.ui.demo.myFiori.util.Grouper.bundle = this.getView().getModel("i18n").getResourceBundle();    
				var grouper = sap.ui.demo.myFiori.util.Grouper[key];    
				sorters.push(new sap.ui.model.Sorter(key, true, grouper));   
			}
			
			// update binding   
			var list = this.getView().byId("List");   
			var oBinding = list.getBinding("items");   
			oBinding.sort(sorters); 
		}
});