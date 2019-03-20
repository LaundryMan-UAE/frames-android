package checkout.checkout_android;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import com.checkout.sdk.CheckoutClient;
import com.checkout.sdk.core.TokenResult;
import com.checkout.sdk.paymentform.PaymentForm;
import com.checkout.sdk.utils.CardUtils.Cards;
import com.checkout.sdk.utils.Environment;

public class DemoActivity extends Activity {

    private PaymentForm mPaymentForm;

    private final CheckoutClient.TokenCallback callback = new CheckoutClient.TokenCallback() {

        @Override
        public void onTokenResult(TokenResult tokenResult) {
            if (tokenResult instanceof TokenResult.TokenResultSuccess) {
                mPaymentForm.clearForm(); // clear the form
                String id = ((TokenResult.TokenResultSuccess) tokenResult).getResponse().getId();
                displayMessage("Token", id);
            } else if (tokenResult instanceof TokenResult.TokenResultTokenisationFail) {
                String errorCode = ((TokenResult.TokenResultTokenisationFail) tokenResult).getError().getErrorCode();
                displayMessage("Token Error", errorCode);
            } else if (tokenResult instanceof TokenResult.TokenResultVolleyError) {
                String volleyError = String.valueOf(((TokenResult.TokenResultVolleyError) tokenResult).getError());
                displayMessage("Network Error", volleyError);
            } else {
                throw new RuntimeException("Unknown Error");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mPaymentForm = findViewById(R.id.checkout_card_form);
        CheckoutClient checkoutClient = new CheckoutClient(
                this,
                "pk_test_6e40a700-d563-43cd-89d0-f9bb17d35e73",
                Environment.SANDBOX,
                callback);
        mPaymentForm
                .setAcceptedCard(new Cards[]{Cards.VISA, Cards.MASTERCARD})
                .initialize(checkoutClient);

        // TODO: Make it look more like:
        // CheckoutClient checkoutClient = new CheckoutClient(this,
//        "pk_test_6e40a700-d563-43cd-89d0-f9bb17d35e73",
//                tokenListener,
//                Environment.SANDBOX
//        );
//        UiCustomizer uiCustomizer = new UiCustomizer.Builder()
//                .acceptedCards(Arrays.asList(Cards.VISA, Cards.MASTERCARD))
//                .build();
//        mPaymentForm = findViewById(R.id.checkout_card_form);
//        mPaymentForm.initialize(checkoutClient, mFormListener, uiCustomizer);
    }

    private void displayMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
