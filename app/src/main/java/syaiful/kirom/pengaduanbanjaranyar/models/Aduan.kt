package syaiful.kirom.pengaduanbanjaranyar.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Aduan (
    @SerializedName("complaint_category") var complaint_category : String,
    @SerializedName("complaint_content") var complaint_content : String,
    @SerializedName("user") var user : String,
    @SerializedName("status") var status : String,
    @SerializedName("complaint_image") var complaint_image : String? = null,
    @SerializedName("created_at") var created_at : String,
    @SerializedName("updated_at") var updated_at : String
) : Parcelable