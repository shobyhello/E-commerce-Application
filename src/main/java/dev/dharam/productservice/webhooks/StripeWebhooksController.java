package dev.dharam.productservice.webhooks;

import com.stripe.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebhooksController {
    @PostMapping("/")
    public void handleWebhookRequest(@RequestBody Event webhookEvent){
         return ;
    }
}
