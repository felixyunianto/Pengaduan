package syaiful.kirom.pengaduanbanjaranyar.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import syaiful.kirom.pengaduanbanjaranyar.contracts.InformasiActivityContracts
import syaiful.kirom.pengaduanbanjaranyar.models.Aduan
import syaiful.kirom.pengaduanbanjaranyar.responses.WrappedListResponse
import syaiful.kirom.pengaduanbanjaranyar.utilities.APIClient

class InformasiActivityPresenter(v: InformasiActivityContracts.InformasiActivityView?) : InformasiActivityContracts.InformasiActivityPresenter {

    private var view : InformasiActivityContracts.InformasiActivityView? = v
    private var apiService = APIClient.APIService()

    override fun allDataWaiting(token: String) {
        val request = apiService.getWaitingComplaint("Bearer " + token)
        request.enqueue(object : Callback<WrappedListResponse<Aduan>>{
            override fun onResponse(
                call: Call<WrappedListResponse<Aduan>>,
                response: Response<WrappedListResponse<Aduan>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == 200) {
                        view?.attachAduanToRecycler(body.data)
                        println("DATA WAITING : ${body.data}")
                    } else {
                        view?.showToast("ada yang tidak beres")
                    }
                }
            }

            override fun onFailure(call: Call<WrappedListResponse<Aduan>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.showToast("Cannot connect to server")
            }

        })

    }

    override fun allDataApproved(token: String) {
        val request = apiService.getApprovedComplaint("Bearer " + token)
        request.enqueue(object : Callback<WrappedListResponse<Aduan>>{
            override fun onResponse(
                call: Call<WrappedListResponse<Aduan>>,
                response: Response<WrappedListResponse<Aduan>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == 200) {
                        view?.attachAduanToRecycler(body.data)
                        println("DATA APPROVED : ${body.data}")
                    } else {
                        view?.showToast("ada yang tidak beres")
                    }
                }
            }

            override fun onFailure(call: Call<WrappedListResponse<Aduan>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.showToast("Cannot connect to server")
            }

        })
    }

    override fun allDataDecline(token: String) {
        val request = apiService.getDeclineComplaint("Bearer " + token)
        request.enqueue(object : Callback<WrappedListResponse<Aduan>>{
            override fun onResponse(
                call: Call<WrappedListResponse<Aduan>>,
                response: Response<WrappedListResponse<Aduan>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == 200) {
                        view?.attachAduanToRecycler(body.data)
                        println("DATA DECLINE : ${body.data}")
                    } else {
                        view?.showToast("ada yang tidak beres")
                    }
                }
            }

            override fun onFailure(call: Call<WrappedListResponse<Aduan>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.showToast("Cannot connect to server")
            }

        })
    }

//    override fun getAduanTerima(token: String) {
//        val request = apiService.getAduanTerima("Bearer " + token)
//        view?.hideDataEmpty()
//        view?.showLoading()
//        request.enqueue(object : Callback<WrappedListResponse<Aduan>>{
//            override fun onResponse(
//                call: Call<WrappedListResponse<Aduan>>,
//                response: Response<WrappedListResponse<Aduan>>
//            ) {
//                println("RESPONSE " + response)
//                if(response.isSuccessful){
//                    val body = response.body()
//
//                    if(body != null){
//                        if(body.data.size == 0){
//                            view?.showDataEmpty()
//                        }else{
//                            view?.attachAduanToRecycler(body.data)
//                        }
//                        view?.hideLoading()
//                    }
//                }else{
//                    view?.showToast("Terjadi kesalahan")
//                    view?.hideLoading()
//                }
//            }
//
//            override fun onFailure(call: Call<WrappedListResponse<Aduan>>, t: Throwable) {
//                view?.showToast("Tidak bisa koneksi ke server")
//                println(t.message)
//                view?.hideLoading()
//            }
//
//        })
//    }

    override fun destroy() {
        view = null
    }


}