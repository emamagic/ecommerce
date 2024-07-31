package com.emamagic.ecommerce.product;

import com.emamagic.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> reqBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> reqEntity = new HttpEntity<>(reqBody, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> respType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PurchaseResponse>> respEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                reqEntity,
                respType
        );

        if (respEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: " + respEntity.getStatusCode());
        }

        return respEntity.getBody();
    }
}
