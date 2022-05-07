package com.datn.cinema.controller;

import com.datn.cinema.common.PaypalPaymentIntent;
import com.datn.cinema.common.PaypalPaymentMethod;
import com.datn.cinema.dto.LinkDTO;
import com.datn.cinema.dto.PaypalDTO;
import com.datn.cinema.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/paypal")
public class PayPalController {

    public static final String URL_PAYPAL_SUCCESS = "/pay/success";
    public static final String URL_PAYPAL_CANCEL = "/pay/cancel";


    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @PostMapping(value = "/pay")
    public ResponseEntity<LinkDTO> pay(@RequestBody PaypalDTO paypalDTO, HttpServletRequest request) {

        String cancelUrl = "http://localhost:4200/confirm";
        String successUrl = "http://localhost:4200/information";

        try {
            System.err.println("PRICE : " + paypalDTO.getPrice()); //test
            Payment payment = paypalService.createPayment(
                    5.0,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    System.err.println(payment.getLinks());
                    LinkDTO linkDTO = new LinkDTO(links.getHref());

                    return new ResponseEntity<>((linkDTO), HttpStatus.OK);
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay() {
        return "http://localhost:4200/confirm";
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public ResponseEntity<Void> successPay(@RequestParam("paymentId") String paymentId,
                                           @RequestParam("PayerID") String payerId) {
        try {
            System.err.println("HERE");
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
