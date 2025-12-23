package org.example.tools;

import org.example.request.OrderRequest;
import org.example.request.UserRequest;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

/**
 * Service class containing methods exposed as tools to the LLM.
 * These methods simulate calls to external microservices.
 */
@Service
public class ToolService {


    private final RestClient userRestClient;

    private final RestClient orderRestClient;

    private final RestClient transactionRestClient;

    public ToolService(@Autowired RestClient userRestClient, @Autowired RestClient orderRestClient, @Autowired RestClient transactionRestClient){
        this.userRestClient=userRestClient;
        this.orderRestClient=orderRestClient;
        this.transactionRestClient=transactionRestClient;
    }
    /**
     * Simulates fetching user details from a 'User Microservice'.
     * This function is registered as a tool for the LLM.
     * * @param request The UserRequest containing the userId.
     * @return A map representing the fetched user data.
     */
    @Tool(name= "getUserDetails" ,description = "Fetches detailed information for a user by their ID. Useful for questions about user accounts or profiles.")
    public Object getUserDetails(Long userId) {
        System.out.println("--- Tool Executed: Fetching user details for ID: " + userId);

        var response = userRestClient.get().uri("/users/{userId}" , userId).retrieve().body(Object.class);
        return response;
    }

    @Tool(description = "Fetches the information of the users if asked by any other parameters other than id. If you don't have any matches you can say that no matches found.")
    public Object getAllUser() {
        System.out.println("--- Tool Executed: Fetching all users ");

        var response = userRestClient.get().uri("/users").retrieve().body(Object.class);
        return response;
    }

    /**
     * Simulates fetching order details from an 'Order Microservice'.
     * This function is registered as a tool for the LLM.
     * * @param request The OrderRequest containing the orderId.
     * @return A map representing the fetched order data.
     */
    @Tool(name= "getOrderDetails",description = "Retrieves the status and contents of a specific order using its ID. Accepts { \"orderId\": \"...\" } and returns order details. Do NOT use userId as orderId. Use this for all order-related questions.")
    public Object getOrderDetails(Long userId) {
        System.out.println("--- Tool Executed: Fetching order details for ID: " + userId);
        var response = orderRestClient.get().uri("/orders/user/{userId}" , userId).retrieve().body(Object.class);
        return response;
    }

    @Tool(name= "getTransactions",description = "Retrieves the status and transaction details of a specific order id.  Accepts { \"orderId\": \"...\" } and return transaction details. Do NOT use userId as orderId Use this for all transaction-related questions.")
    public Object getTransactions(Long orderId) {
        System.out.println("--- Tool Executed: Fetching transaction details for ID: " + orderId);
        var response = transactionRestClient.get().uri("/transactions/user/{userId}" , orderId).retrieve().body(Object.class);
        return response;
    }
}