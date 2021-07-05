package syaiful.kirom.pengaduanbanjaranyar.webservices

import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import syaiful.kirom.pengaduanbanjaranyar.models.Aduan
import syaiful.kirom.pengaduanbanjaranyar.models.Category
import syaiful.kirom.pengaduanbanjaranyar.models.User
import syaiful.kirom.pengaduanbanjaranyar.responses.WrappedListResponse
import syaiful.kirom.pengaduanbanjaranyar.responses.WrappedResponse

interface APIService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<WrappedResponse<User>>

    @GET("complaint/approved")
    fun getApprovedComplaint(
        @Header("Authorization") token : String
    ) : Call<WrappedListResponse<Aduan>>

    @GET("complaint/waiting")
    fun getWaitingComplaint(
        @Header("Authorization") token : String
    ) : Call<WrappedListResponse<Aduan>>

    @GET("complaint/decline")
    fun getDeclineComplaint(
        @Header("Authorization") token : String
    ) : Call<WrappedListResponse<Aduan>>

    @GET("complaint-category")
    fun getCategory(
        @Header("Authorization") token : String
    ) : Call<WrappedListResponse<Category>>

    @Multipart
    @POST("complaint")
    fun postComplaint(
        @Header("Authorization") token : String,
        @Part("complaint_category_id") complaint_category_id : RequestBody,
        @Part("complaint_content") complaint_content : RequestBody,
        @Part complaint_image_id: MultipartBody.Part

    ) : Call<WrappedResponse<Aduan>>
}