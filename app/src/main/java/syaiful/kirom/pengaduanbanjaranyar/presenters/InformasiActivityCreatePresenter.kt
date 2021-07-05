package syaiful.kirom.pengaduanbanjaranyar.presenters

import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import syaiful.kirom.pengaduanbanjaranyar.contracts.InformasiActivityContracts
import syaiful.kirom.pengaduanbanjaranyar.models.Aduan
import syaiful.kirom.pengaduanbanjaranyar.models.Category
import syaiful.kirom.pengaduanbanjaranyar.responses.WrappedListResponse
import syaiful.kirom.pengaduanbanjaranyar.responses.WrappedResponse
import syaiful.kirom.pengaduanbanjaranyar.utilities.APIClient

class InformasiActivityCreatePresenter(v : InformasiActivityContracts.InformasiActivityCreateView?) : InformasiActivityContracts.InformasiActivityCreatePresenter {
    private var view : InformasiActivityContracts.InformasiActivityCreateView? = v
    private var apiService = APIClient.APIService()
    override fun postComplaint(
        token: String,
        complaint_category_id: RequestBody,
        complaint_content: RequestBody,
        complaint_image: MultipartBody.Part
    ) {
        val request = apiService.postComplaint(token, complaint_category_id, complaint_content, complaint_image)
        println("TOKEN " + token)
        println("ID " + complaint_category_id)
        println("CONTENT " + complaint_content)
        println("IMAGE " + complaint_image)
        println("REQUEST " + request)
        request.enqueue(object : Callback<WrappedResponse<Aduan>>{
            override fun onResponse(
                call: Call<WrappedResponse<Aduan>>,
                response: Response<WrappedResponse<Aduan>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        view?.showToast(body.message)
                        view?.successPost()
                    }
                }
            }

            override fun onFailure(call: Call<WrappedResponse<Aduan>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
                println(t.message)
                t.printStackTrace()
            }

        })
    }

    override fun getCategory(token: String) {
        val request = apiService.getCategory("Bearer "+ token)
        request.enqueue(object : Callback<WrappedListResponse<Category>>{
            override fun onResponse(
                call: Call<WrappedListResponse<Category>>,
                response: Response<WrappedListResponse<Category>>
            ) {
                println("RESPONSE " + response)
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        view?.attachToSpinner(body.data)

                    }
                }else{
                    view?.showToast("Terjadi kesalahan")
                }
            }

            override fun onFailure(call: Call<WrappedListResponse<Category>>, t: Throwable) {
                view?.showToast("Tidak bisa koneksi ke server")
                println(t.message)
            }

        })
    }

    override fun destroy() {
        view = null
    }


}