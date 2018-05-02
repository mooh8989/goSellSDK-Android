package company.tap.gosellapi.internal.api.facade;

import android.support.annotation.RestrictTo;

import company.tap.gosellapi.internal.api.api_service.APIService;
import company.tap.gosellapi.internal.api.api_service.RetrofitHelper;
import company.tap.gosellapi.internal.api.callbacks.APIRequestCallback;
import company.tap.gosellapi.internal.api.models.Charge;
import company.tap.gosellapi.internal.api.models.PaymentInfo;
import company.tap.gosellapi.internal.api.requests.CreateChargeRequest;
import company.tap.gosellapi.internal.api.requests.UpdateChargeRequest;
import company.tap.gosellapi.internal.logger.lo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class GoSellAPI {
    private APIService apiHelper;

    //init and pending requests
    private DataManager dataManager;

    private GoSellAPI() {
        apiHelper = RetrofitHelper.getApiHelper();
        dataManager = new DataManager(apiHelper);
    }

    private static class SingletonHolder {
        private static final GoSellAPI INSTANCE = new GoSellAPI();
    }

    public static GoSellAPI getInstance() {
        return SingletonHolder.INSTANCE;
    }


    //requests
    public void createCharge(final CreateChargeRequest createChargeRequest, final APIRequestCallback<Charge> requestCallback) {
        dataManager.request(new DataManager.DelayedRequest<>(apiHelper.createCharge(createChargeRequest), requestCallback));
    }

    public void retrieveCharge(final String chargeId, final APIRequestCallback<Charge> requestCallback) {
        dataManager.request(new DataManager.DelayedRequest<>(apiHelper.retrieveCharge(chargeId), requestCallback));
    }

    public void updateCharge(final String chargeId, final UpdateChargeRequest updateChargeRequest, final APIRequestCallback<Charge> requestCallback) {
        dataManager.request(new DataManager.DelayedRequest<>(apiHelper.updateCharge(chargeId, updateChargeRequest), requestCallback));
    }

    public void getPaymentTypes(/*final APIRequestCallback<ResponseBody> requestCallback*/PaymentInfo paymentInfo) {
        apiHelper.getPaymentTypes(paymentInfo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                lo.g("payment types success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                lo.g("payment types fail");
            }
        });
    }
}
