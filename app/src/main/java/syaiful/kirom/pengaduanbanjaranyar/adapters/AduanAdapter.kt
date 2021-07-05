package syaiful.kirom.pengaduanbanjaranyar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.list_item_aduan.view.*
import syaiful.kirom.pengaduanbanjaranyar.R
import syaiful.kirom.pengaduanbanjaranyar.models.Aduan

class AduanAdapter(private var aduan: List<Aduan>, private var context: Context) : RecyclerView.Adapter<AduanAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(aduan : Aduan, context: Context){
            if(aduan.complaint_image != null){
                itemView.ivImageContent.load(aduan.complaint_image)
            }else{
                itemView.ivImageContent.load(R.drawable.no_image)
            }
            itemView.tvComplaintContent.text = aduan.complaint_content
            itemView.tvTanggal.text = aduan.created_at
            itemView.tvPenanggungJawab.text = aduan.user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_aduan, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(aduan[position], context)

    override fun getItemCount() = aduan.size
}