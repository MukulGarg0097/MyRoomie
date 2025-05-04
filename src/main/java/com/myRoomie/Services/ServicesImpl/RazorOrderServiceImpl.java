package com.myRoomie.Services.ServicesImpl;

//{"amount":1620,"amount_paid":0,"notes":[],"created_at":1554120104,"amount_due":1620,"currency":"INR"
//,"receipt":"","id":"order_CENpAyjQdvsxUO","entity":"order","offer_id":null,"status":"created","attempts":0}

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.RazorOrderEntity;
import com.myRoomie.Repository.IRazorOrderRepository;
import com.myRoomie.Services.IRazorOrderService;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.RazorOrderMapper;
import com.myRoomie.response.dto.RazorOrderResponse;
import com.myRoomie.response.dto.TransactionResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class RazorOrderServiceImpl implements IRazorOrderService{

	@Value("${com.razorpaykey_id}")
	String razorKey;
	
	@Value("${com.razorpaysecretKey}")
  	String razorSecret;
	
	@Autowired
	IRazorOrderRepository repo;
	
	@Autowired
	RazorOrderMapper razorMap;
	
	@Override
	public RazorOrderResponse createOrder(TransactionResponse transaction) throws BaseException
	{	
		RazorpayClient razorpayClient;
		JSONObject options = new JSONObject();
		RazorOrderEntity entitySave=new RazorOrderEntity();
		Order order=null;
		try {
	    	options.put("amount", transaction.getNetTotalAmount()*100);
	    	options.put("currency", "INR");
	    	options.put("receipt", transaction.getId().toString());
			razorpayClient = new RazorpayClient(razorKey, razorSecret);
			order = razorpayClient.Orders.create(options);
			if(ObjectUtils.isEmpty(order))
			{
				throw new BaseException(ResponseCode.ERROR_IN_PARSING);
			}
			entitySave= repo.save(razorMap.mapOrderToRazorOrderEntity(order));
			if(ObjectUtils.isEmpty(entitySave))
			{
				throw new BaseException(ResponseCode.ERROR_IN_SAVING);
			}
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			throw new BaseException(ResponseCode.BAD_REQUEST, e1.getMessage());
		}
		return razorMap.mapRazorOrderEntityToResponse(entitySave);
	}
	
}
