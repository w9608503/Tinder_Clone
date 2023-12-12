package com.thecode.tinderclone.Firebase
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.thecode.tinderclone.Firebase.LoginActivity
import com.thecode.tinderclone.R
import com.thecode.tinderclone.activities.MainActivity
import com.thecode.tinderclone.entities.User

class SignUpActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var passwordEditTextC: EditText
    private lateinit var userName: EditText
    private lateinit var mAddress: EditText
    private lateinit var image: ImageView
    private lateinit var signUpButton: Button
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var storageReference: StorageReference
    private var imageUri: Uri? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        passwordEditTextC = findViewById(R.id.editConfirmPassword)
        userName = findViewById(R.id.userName)
        mAddress = findViewById(R.id.address)
        image = findViewById(R.id.profile)
        signUpButton = findViewById(R.id.signUpButton)

        image.setOnClickListener {
            // Function to pick an image from the gallery
            pickImage()
        }
        signUpButton.setOnClickListener {
            // Function to pick an image from the gallery
uploadImage()
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    // Handle the result of image picker
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            image.setImageURI(imageUri)
//            uploadImage()
        }
    }

    private fun uploadImage() {
        imageUri?.let {
            val imageRef = storageReference.child("profile_images/${System.currentTimeMillis()}.jpg")

            val uploadTask: UploadTask = imageRef.putFile(it)

            uploadTask.addOnSuccessListener {
                // Image uploaded successfully, get the download URL
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()

                    // Now you have the image URL, proceed with user registration
                    registerUser(imageUrl)
                }
            }.addOnFailureListener {
                Toast.makeText(this@SignUpActivity, "Image upload failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(imageUrl: String) {
        val email = emailEditText.text.toString().trim { it <= ' ' }
        val password = passwordEditText.text.toString().trim { it <= ' ' }
        val cpassword = passwordEditTextC.text.toString().trim { it <= ' ' }
        val name = userName.text.toString().trim { it <= ' ' }
        val address = mAddress.text.toString().trim { it <= ' ' }

        if (email.isEmpty() || password.isEmpty() || cpassword.isEmpty() || name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this@SignUpActivity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else if (password != cpassword) {
            Toast.makeText(this@SignUpActivity, "Passwords do not match", Toast.LENGTH_SHORT).show()
        } else {
            firebaseAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@SignUpActivity) { task ->
                    if (task.isSuccessful) {
                        // Sign up success, update UI or navigate to the next activity
                        Toast.makeText(this@SignUpActivity, "Sign up successful!", Toast.LENGTH_SHORT).show()

                        // Save user data to the Realtime Database
                        val userId = firebaseAuth?.currentUser?.uid
                        userId?.let {
                            val databaseReference = firebaseDatabase.reference.child("users").child(it)
                            val user = User(name, email, address, imageUrl)
                            databaseReference.setValue(user)
                        }

                        finish() // Finish the sign-up activity and go back to the previous activity
                        val i = Intent(this@SignUpActivity, MainActivity::class.java)
                        startActivity(i)
                    } else {
                        // If sign up fails, display a message to the user.
                        Toast.makeText(this@SignUpActivity, "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    fun Login(view: View?) {
        val i = Intent(this@SignUpActivity, LoginActivity::class.java)
        startActivity(i)
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
