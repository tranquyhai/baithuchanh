package vn.tlu.edu.sharepreference

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var edtUsername: EditText
    private var textPassword: EditText? = null
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button
    private lateinit var btnShow: Button
    private lateinit var preferenceHelper: PreferenceHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Ánh xạ UI
        edtUsername = findViewById(R.id.edtUsername)
        textPassword = findViewById(R.id.textPassword)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        btnShow = findViewById(R.id.btnShow)

        // Khởi tạo PreferenceHelper
        preferenceHelper = PreferenceHelper(this)

        // Sự kiện bấm nút "Lưu"
        btnSave.setOnClickListener {
            val edtUsername = findViewById<EditText>(R.id.edtUsername)
            val textPassword = findViewById<EditText>(R.id.textPassword)

            val username = edtUsername.text.toString()
            val password = textPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                preferenceHelper.saveUser(username, password)
                Toast.makeText(this, "Đã lưu!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            }

        }

        // Sự kiện bấm nút "Xóa"
        btnClear.setOnClickListener {
            preferenceHelper.clearUser()
            Toast.makeText(this, "Đã xóa dữ liệu!", Toast.LENGTH_SHORT).show()
        }

        // Sự kiện bấm nút "Hiển thị"
        btnShow.setOnClickListener {
            val (username, password) = preferenceHelper.getUser()
            if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                Toast.makeText(this, "Tên: $username\nMật khẩu: $password", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Chưa có dữ liệu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
