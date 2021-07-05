package syaiful.kirom.pengaduanbanjaranyar.presenters

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import syaiful.kirom.pengaduanbanjaranyar.contracts.LoginActivityContracts
import syaiful.kirom.pengaduanbanjaranyar.models.User
import syaiful.kirom.pengaduanbanjaranyar.responses.WrappedListResponse
import syaiful.kirom.pengaduanbanjaranyar.responses.WrappedResponse
import syaiful.kirom.pengaduanbanjaranyar.utilities.APIClient
import syaiful.kirom.pengaduanbanjaranyar.utilities.Constants

class LoginActivityPresenter(v : LoginActivityContracts.LoginActivityView?) : LoginActivityContracts.LoginActivityPresenter {

    private var view : LoginActivityContracts.LoginActivityView? = v
    private var apiService = APIClient.APIService()

    override fun login(email : String, password: String, context : Context) {
        val request = apiService.login(email, password)
        view?.showLoading()
        request.enqueue(object : Callback<WrappedResponse<User>>{
            override fun onResponse(
                call: Call<WrappedResponse<User>>,
                response: Response<WrappedResponse<User>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null && body?.status == 200){
                        Constants.setToken(context, body?.data.token!!)
                        view?.showToast("Selamat datan ${body.data.name}")
                        view?.successLogin()
                    }else{
                        view?.showToast("Gagal Login, cek email atau password anda")
                    }
                    view?.hideLoading()
                }else{
                    view?.showToast("Ada sebuah kesalahan, silahkan mencoba lagi lain waktu")
                    view?.hideLoading()
                }
            }

            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
                view?.hideLoading()
            }

        })

    }

    override fun destroy() {
        view =  null
    }
}