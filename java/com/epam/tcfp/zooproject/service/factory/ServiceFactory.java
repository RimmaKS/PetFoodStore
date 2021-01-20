package com.epam.tcfp.zooproject.service.factory;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
	
	  private static final Map<String, Service> SERVICE_MAP = new HashMap<>();
	  private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

	static {
	    SERVICE_MAP.put("/ZOOPROJECT/LOGIN", new UserLoginService());
	    SERVICE_MAP.put("/ZOOPROJECT/REGISTER", new UserRegistrationService());
	    SERVICE_MAP.put("/ZOOPROJECT/LOGOUT", new UserLogoutService());
	    SERVICE_MAP.put("/ZOOPROJECT/USERDATAGET", new GetUserAccountDataService());
	    SERVICE_MAP.put("/ZOOPROJECT/USERDATAEDIT", new UserDataEditService());
	    SERVICE_MAP.put("/ZOOPROJECT/PRODUCTSHOW", new ProductShowService());
	    SERVICE_MAP.put("/ZOOPROJECT/MANAGEUSERS", new ManageUsersService());
	    SERVICE_MAP.put("/ZOOPROJECT/DEACTIVATEUSER", new DeactivateUserService());
	    SERVICE_MAP.put("/ZOOPROJECT/ADDTOCART", new AddToShoppingCartService());
	    SERVICE_MAP.put("/ZOOPROJECT/REMOVEFROMCART", new RemoveFromShoppingCartService());
	    SERVICE_MAP.put("/ZOOPROJECT/CHANGEAMOUNT", new ChangeAmountInShoppingCartService());
	    SERVICE_MAP.put("/ZOOPROJECT/CREATEORDER", new CreateOrderService());
	    SERVICE_MAP.put("/ZOOPROJECT/CHECKADDRESS", new CheckUserAddressService());
	    SERVICE_MAP.put("/ZOOPROJECT/ORDERHISTORY", new GetUserOrderHistoryService());
	    SERVICE_MAP.put("/ZOOPROJECT/EDITPRODUCT", new ProductEditService());
	    SERVICE_MAP.put("/ZOOPROJECT/ADDPRODUCT", new ProductAddService());
	    SERVICE_MAP.put("/ZOOPROJECT/UPDATEORDERSTATUS", new UpdateOrderStatusService());
	    SERVICE_MAP.put("/ZOOPROJECT/GETORDERSSTATUS", new GetOrdersStatusService());
	}


	private ServiceFactory() {
	}

	  public Service getService(String request) {
			Service service = SERVICE_MAP.get("/ERROR");

	    for (Map.Entry<String, Service> pair : SERVICE_MAP.entrySet()) {
	      if (request.equalsIgnoreCase(pair.getKey())) {
	        service = SERVICE_MAP.get(pair.getKey());
	      }
	    }
	    return service;
	  }

	  public static ServiceFactory getInstance() {
	    return SERVICE_FACTORY;
	  }
}