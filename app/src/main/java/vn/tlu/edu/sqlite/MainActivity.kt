package vn.tlu.edu.sqlite

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var edtName: EditText
    private lateinit var edtPhone: EditText
    private lateinit var txtResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo Database Helper
        databaseHelper = DatabaseHelper(this)

        // Ánh xạ View
        edtName = findViewById(R.id.edtName)
        edtPhone = findViewById(R.id.edphone)
        txtResult = findViewById(R.id.txtResult)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnShow = findViewById<Button>(R.id.btnShow)

        // Xử lý sự kiện khi nhấn nút "Thêm"
        btnAdd.setOnClickListener {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val success = databaseHelper.addContact(name, phone)
                showToast(if (success) "Thêm thành công" else "Lỗi khi thêm")
            } else {
                showToast("Vui lòng nhập đầy đủ thông tin")
            }
        }

        // Xử lý sự kiện khi nhấn nút "Sửa"
        btnUpdate.setOnClickListener {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val success = databaseHelper.updateContact(name, phone)
                showToast(if (success) "Sửa thành công" else "Không tìm thấy tên")
            } else {
                showToast("Vui lòng nhập tên và số điện thoại mới")
            }
        }

        // Xử lý sự kiện khi nhấn nút "Xóa"
        btnDelete.setOnClickListener {
            val name = edtName.text.toString()

            if (name.isNotEmpty()) {
                val success = databaseHelper.deleteContact(name)
                showToast(if (success) "Xóa thành công" else "Không tìm thấy tên")
            } else {
                showToast("Vui lòng nhập tên để xóa")
            }
        }

        // Xử lý sự kiện khi nhấn nút "Hiển thị"
        btnShow.setOnClickListener {
            val contacts = databaseHelper.getAllContacts()
            txtResult.text = contacts.joinToString("\n")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
