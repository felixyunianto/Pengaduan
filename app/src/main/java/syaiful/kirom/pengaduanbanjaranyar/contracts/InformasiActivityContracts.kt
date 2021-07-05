package syaiful.kirom.pengaduanbanjaranyar.contracts

import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import syaiful.kirom.pengaduanbanjaranyar.models.Aduan
import syaiful.kirom.pengaduanbanjaranyar.models.Category

interface InformasiActivityContracts {
    interface InformasiActivityView {
        fun attachAduanToRecycler(aduan : List<Aduan>)
        fun showLoading()
        fun hideLoading()
        fun showDataEmpty()
        fun hideDataEmpty()
        fun showToast(message : String)
    }

    interface InformasiActivityPresenter {
        fun allDataWaiting(token : String)
        fun allDataApproved(token : String)
        fun allDataDecline(token : String)
        fun destroy()
    }

    interface InformasiActivityCreateView {
        fun attachToSpinner(category: List<Category>)
        fun showToast(message : String)
        fun successPost();
    }

    interface InformasiActivityCreatePresenter {
        fun postComplaint(token: String, complaint_category_id: RequestBody, complaint_content: RequestBody, complaint_image: MultipartBody.Part)
        fun getCategory(token : String)
        fun destroy()
    }
}