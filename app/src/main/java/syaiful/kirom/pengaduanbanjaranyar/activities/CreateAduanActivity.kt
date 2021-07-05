package syaiful.kirom.pengaduanbanjaranyar.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_create_aduan.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import syaiful.kirom.pengaduanbanjaranyar.contracts.InformasiActivityContracts
import syaiful.kirom.pengaduanbanjaranyar.databinding.ActivityCreateAduanBinding
import syaiful.kirom.pengaduanbanjaranyar.models.Category
import syaiful.kirom.pengaduanbanjaranyar.presenters.InformasiActivityCreatePresenter
import syaiful.kirom.pengaduanbanjaranyar.utilities.Constants
import java.io.File

@Suppress("DEPRECATION")
class CreateAduanActivity : AppCompatActivity(), InformasiActivityContracts.InformasiActivityCreateView,
    AdapterView.OnItemSelectedListener {

    private var presenter : InformasiActivityContracts.InformasiActivityCreatePresenter? = null
    private lateinit var binding : ActivityCreateAduanBinding
    private lateinit var imageUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = InformasiActivityCreatePresenter(this)
        binding = ActivityCreateAduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setOnClickListener {
            openGalery()
        }
        doSave()
        toolbarAction()
    }

    private fun getData() {
        val token = Constants.getToken(this)
        presenter?.getCategory(token)
    }

    private fun doSave() {
        binding.btnSave.setOnClickListener {
            val token = Constants.getToken(this)
            val selectedObject = binding.spinnerCategory.selectedItem as Category
            val complaintContent : RequestBody = RequestBody.create(
                MultipartBody.FORM, binding.etComplaintContent.text.toString()
            );
            val complaintCategory : RequestBody = RequestBody.create(MultipartBody.FORM, selectedObject.id);

            val originalFile  = File(imageUri.path)

            val imagePart : RequestBody = RequestBody.create(
                MediaType.parse(contentResolver.getType(imageUri)),
                originalFile
            )

            val image : MultipartBody.Part = MultipartBody.Part.createFormData(
                "complaint_image",
                originalFile.name,
                imagePart
            )

            presenter?.postComplaint(token, complaintCategory, complaintContent, image)
        }
    }

    private fun openGalery(){
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            imageUri = data!!.data!!
            binding.imageView.setImageURI(imageUri)
        }
    }

    private fun toolbarAction(){
        val action = supportActionBar
        action!!.title = "Create Aduan"
        action.setDisplayHomeAsUpEnabled(true)
    }

    override fun attachToSpinner(category: List<Category>) {
        var spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, category)
        spinnerCategory.apply {
            adapter = spinnerAdapter
        }

        spinnerCategory.onItemSelectedListener = this
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun successPost() {
        val intent = Intent(this@CreateAduanActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedObject = parent?.selectedItem as Category
        Toast.makeText(this@CreateAduanActivity, "ID ${selectedObject.id}", Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}

