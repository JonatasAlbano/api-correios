package com.joalbano.correiosapi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.joalbano.correiosapi.dto.ResponseDTO;


@Service
public class CorreiosService {
	
	@Bean
	public RestTemplate restTemplate() {
	 
	    var factory = new SimpleClientHttpRequestFactory();
	    return new RestTemplate(factory);
	}
	
	public ResponseDTO getMaxDateByCorreios(String cdServico, String sCepOrigem, String sCepDestino) {
		
		String url = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx/CalcPrazo?nCdServico=" + cdServico + "&sCepOrigem="+ sCepOrigem +"&sCepDestino="+ sCepDestino;
		String response = this.restTemplate().getForObject(url, String.class);
	    int indexOf = response.indexOf("DataMaxEntrega");
	    
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String maxDate = response.substring(indexOf + 15, indexOf + 25);
	    
	    Date formatedDate = null;
		try {
			formatedDate = simpleDateFormat.parse(maxDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    ResponseDTO responseDTO = new ResponseDTO();
	    responseDTO.setMaxDate(formatedDate);
	    return responseDTO;
	}

}
